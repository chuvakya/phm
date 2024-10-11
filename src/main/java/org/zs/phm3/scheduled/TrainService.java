package org.zs.phm3.scheduled;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.calculation.CalculationAttrType;
import org.zs.phm3.models.calculation.CalculationAttribute;
import org.zs.phm3.models.ml.*;
import org.zs.phm3.repository.calculation.CalculationAttrRepository;
import org.zs.phm3.repository.calculation.CalculationAttrTypeRepository;
import org.zs.phm3.service.ml.MlJobService;
import org.zs.phm3.service.ml.MlModelService;
import org.zs.phm3.service.ml.ModelSchemaService;
import org.zs.phm3.service.unit.UnitService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Service
public class TrainService {

    @Value("${ml-service.dataset-folder}")
    private String datasetFolder;

    @Value("${ml-service.ml-rest-service}")
    private String mlRestService;

    @Autowired
    private MlModelService mlModelService;

    @Autowired
    private CalculationAttrRepository calculationAttrRepository;

    @Autowired
    private CalculationAttrTypeRepository calculationAttrTypeRepository;

    @Autowired
    private MlJobService mlJobService;

    @Autowired
    private ModelSchemaService modelSchemaService;

    @Autowired
    private UnitService unitService;

    @Scheduled(fixedRateString = "5000")
    public void startJob() {
        if (!MlJobQueued.getMlJobQueue().isEmpty()) {
            MlJob mlJob = MlJobQueued.getMlJobQueue().getFirst();
            Long startTime = System.currentTimeMillis();
            try {
                if (mlJob.getState().equals("QUEUED")) {
                    mlJob.setState("RUNNING");
                }
                mlJobService.save(mlJob);
                train(mlJob);
                MlJobQueued.getMlJobQueue().pollFirst();
            } catch (Exception ex) {
                MlJobQueued.getMlJobQueue().pollFirst();
                mlJob.setState("FAILED");
                mlJob.setElapsedTime(System.currentTimeMillis() - startTime);
                mlJob.setResult(ex.getMessage());
                mlJobService.save(mlJob);
            }
        }
    }

    public void train(MlJob mlJob) throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        Long startTime = System.currentTimeMillis();
        // фичи
        HttpPost post = new HttpPost(mlRestService + "/featured");
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addPart("full_path", new StringBody("/dataset/" +
                mlJob.getModelSchema().getDataset().getPath(), ContentType.TEXT_PLAIN));
        if (mlJob.getModelSchema().getDataset().getDataSchema().getBitAttribute() != null &&
                !mlJob.getModelSchema().getMlAlgorithm().toString().equals("SIX_SIGMA") &&
                !mlJob.getModelSchema().getMlAlgorithm().toString().equals("THREE_SIGMA")) {
            entityBuilder.addPart("BIT_column",
                    new StringBody(mlJob.getModelSchema().getDataset().getDataSchema().getBitAttribute().getId().toString(),
                            ContentType.TEXT_PLAIN));
            entityBuilder.addPart("error_code", new StringBody(mlJob.getModelSchema().getDataset()
                    .getDataSchema().getBitErrorCode().getErrorCode(), ContentType.TEXT_PLAIN));
        }
        entityBuilder.addPart("timestamp_column", new StringBody("timestamp", ContentType.TEXT_PLAIN));
        entityBuilder.addPart("task",
                new StringBody(MlAlgorithmType.getTaskNumber(mlJob.getModelSchema().getMlAlgorithm()).toString(),
                        ContentType.TEXT_PLAIN));
        if (mlJob.getModelSchema().getMlAlgorithm().toString().equals("SIX_SIGMA") ||
                mlJob.getModelSchema().getMlAlgorithm().toString().equals("THREE_SIGMA")) {
            entityBuilder.addPart("sigma", new StringBody("true", ContentType.TEXT_PLAIN));
            if (mlJob.getModelSchema().getDataset().getDataSchema().getBitAttribute() != null) {
                entityBuilder.addPart("BIT_column",
                        new StringBody(mlJob.getModelSchema().getDataset().getDataSchema().getBitAttribute().getId().toString(),
                                ContentType.TEXT_PLAIN));
            }
        } else {
            entityBuilder.addPart("window_length", new StringBody(mlJob.getModelSchema().getWindowLength().toString(),
                    ContentType.TEXT_PLAIN));
        }
        post.setEntity(entityBuilder.build());
        HttpResponse responseFile = client.execute(post);

        // скачка фичей
        HttpEntity entityFile = responseFile.getEntity();
        InputStream is = entityFile.getContent();
        String fileName = System.currentTimeMillis() + "_featured.csv";
        FileOutputStream fos = new FileOutputStream(datasetFolder + "/" + fileName);
        int inByte;
        while((inByte = is.read()) != -1)
            fos.write(inByte);
        is.close();
        fos.close();
        mlJob.getModelSchema().setFeaturedFilePath(fileName);
        modelSchemaService.save(mlJob.getModelSchema());

        // обучение
        HttpPost trainPost = new HttpPost(mlRestService + "/train");
        MultipartEntityBuilder entityBuilderPost = MultipartEntityBuilder.create();

        entityBuilderPost.addPart("full_path", new StringBody("/dataset/" + fileName, ContentType.TEXT_PLAIN));
        entityBuilderPost.addPart("model_name",
                new StringBody(MlAlgorithmType.getModelNameFromMlAlgorithm(mlJob.getModelSchema().getMlAlgorithm()),
                        ContentType.TEXT_PLAIN));
        entityBuilderPost.addPart("task",
                new StringBody(MlAlgorithmType.getTaskNumber(mlJob.getModelSchema().getMlAlgorithm()).toString(),
                        ContentType.TEXT_PLAIN));
        trainPost.setEntity(entityBuilderPost.build());
        HttpResponse response = client.execute(trainPost);
        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseString);

        // save complite model
        MlModel mlModel = new MlModel(mlJob.getName(), mlJob.getModelSchema().getMlAlgorithm(),
                mlJob.getModelSchema().getDataset().getDataSchema(), (Long) jsonObject.get("id_model"),
                mlJob.getModelSchema());

        mlModel.setMlType(MlAlgorithmType.getTypeFromAlgorithm(mlJob.getModelSchema().getMlAlgorithm()));
        mlModel.setProjectId(mlJob.getProjectId());
        mlModel.setUnitId(mlJob.getModelSchema().getDataset().getDataSchema().getUnitId());
        mlModel.setModifiedBy(mlJob.getModifiedBy());
        mlModel.setMlServiceState("");

        JSONObject metricsObject = (JSONObject) jsonObject.get("metrics");
        if (metricsObject != null) {
            for (Object o : metricsObject.keySet()) {
                mlModel.getMlModelMetrics().add(new MlModelMetric((String) o, (Double) metricsObject.get(o), mlModel));
            }
        }

        CalculationAttrType calculationAttrType = calculationAttrTypeRepository.getByName("ML Services");
        CalculationAttribute calculationAttribute = new CalculationAttribute(mlModel.getUnitId(), null, null,
                mlModel, null, calculationAttrType);

        mlModel.setLastScan(mlJob.getModelSchema().getDataset().getTimeTo());
        mlModel.setTimeToDataset(mlJob.getModelSchema().getDataset().getTimeTo());
        mlModelService.save(mlModel);
        calculationAttrRepository.save(calculationAttribute);

        mlJob.setElapsedTime(System.currentTimeMillis() - startTime);
        mlJob.setState("FINISHED");
        mlJobService.save(mlJob);
    }
}
