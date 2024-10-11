package org.zs.phm3.scheduled;

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
import org.springframework.stereotype.Component;
import org.zs.phm3.models.ml.MlModel;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.repository.ml.MlModelRepository;
import org.zs.phm3.repository.ts.TsKvRepository;
import org.zs.phm3.service.UomService;
import org.zs.phm3.service.ml.DataSchemaTsKvAttributeService;
import org.zs.phm3.util.parquet.csv.PhmCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class LearningScheduler {

    @Autowired
    private MlModelRepository mlModelRepository;

    @Autowired
    private TsKvRepository tsKvRepository;

    @Autowired
    private UomService uomService;

    @Value("${ml-service.dataset-folder}")
    private String datasetFolder;

    @Value("${ml-service.ml-rest-service}")
    private String mlService;

    @Autowired
    private DataSchemaTsKvAttributeService dataSchemaTsKvAttributeService;

    //@Scheduled(fixedRateString = "5000")
    public void run() {
        Long nowTime = System.currentTimeMillis();
        List<MlModel> mlModels = mlModelRepository.getModelForLearningByCurrentTime(nowTime);
        for (MlModel mlModel : mlModels) {
            if (mlModel.getDataSchema().getBitErrorCode() != null) {
                Boolean isErrors = switch (mlModel.getDataSchema().getBitAttribute().getDataType()) {
                    case LONG -> tsKvRepository.getCountBitCodeLong(mlModel.getDataSchema().getBitAttribute().getDeviceId(),
                            mlModel.getDataSchema().getBitAttribute().getAttributeKey(),
                            Long.parseLong(mlModel.getDataSchema().getBitErrorCode().getErrorCode()),
                            mlModel.getTimeToDataset(), nowTime);
                    case DOUBLE -> tsKvRepository.getCountBitCodeDouble(mlModel.getDataSchema().getBitAttribute().getDeviceId(),
                            mlModel.getDataSchema().getBitAttribute().getAttributeKey(),
                            Double.parseDouble(mlModel.getDataSchema().getBitErrorCode().getErrorCode()),
                            mlModel.getTimeToDataset(), nowTime);
                    case BOOLEAN -> tsKvRepository.getCountBitCodeBoolean(mlModel.getDataSchema().getBitAttribute().getDeviceId(),
                            mlModel.getDataSchema().getBitAttribute().getAttributeKey(),
                            Boolean.parseBoolean(mlModel.getDataSchema().getBitErrorCode().getErrorCode()),
                            mlModel.getTimeToDataset(), nowTime);
                    case STRING -> tsKvRepository.getCountBitCodeString(mlModel.getDataSchema().getBitAttribute().getDeviceId(),
                            mlModel.getDataSchema().getBitAttribute().getAttributeKey(),
                            mlModel.getDataSchema().getBitErrorCode().getErrorCode(),
                            mlModel.getTimeToDataset(), nowTime);
                };
                if (!isErrors) continue;
            }
            String datasetPath = null;
            try {
                datasetPath = generateDataset(mlModel, nowTime);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                continue;
            }
            mlModelRepository.save(mlModel);
        }
    }

    private String generateDataset(MlModel mlModel, Long timeTo) throws Exception {
        List<List<String>> lists = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        PhmCSV csvFile = new PhmCSV();
        csvFile.setPath(datasetFolder + "/" + timeTo + "_learning.csv");

        boolean flag = false;
        List<String> timestamps = new ArrayList<>();

        for (TsKvAttribute tsKvAttribute : dataSchemaTsKvAttributeService.getAllAttributesByDataSchemaId(
                mlModel.getDataSchema().getId())) {
            List<TsKvEntity> tsKvEntities = tsKvRepository.getAllLessTimeToAndDeviceIdAndKey(timeTo,
                    tsKvAttribute.getDeviceId(), tsKvAttribute.getAttributeKey());
            List<String> valuesList = new ArrayList<>();

            for (TsKvEntity tsKvEntity : tsKvEntities) {
                switch (tsKvAttribute.getDataType()) {
                    case BOOLEAN -> valuesList.add(tsKvEntity.getBooleanValue().toString());
                    case STRING -> valuesList.add(tsKvEntity.getStrValue());
                    case LONG -> valuesList.add(uomService.convert(tsKvAttribute.getUomInput(), tsKvAttribute.getUomOutput(),
                            tsKvEntity.getLongValue().doubleValue()).toString());
                    case DOUBLE -> valuesList.add(uomService.convert(tsKvAttribute.getUomInput(), tsKvAttribute.getUomOutput(),
                            tsKvEntity.getDoubleValue()).toString());
                }
                if (!flag) {
                    timestamps.add("" + tsKvEntity.getId().getTs());
                }
            }
            flag = true;
            lists.add(valuesList);
            keys.add(tsKvAttribute.getId().toString());
        }
        lists.add(timestamps);
        keys.add("timestamp");

        if (lists.get(lists.size() - 1).isEmpty() ||
                Long.parseLong(lists.get(lists.size() - 1).get(lists.get(lists.size() - 1).size() - 1)) <=
                        mlModel.getTimeToDataset()) {
            throw new Exception("No new value!");
        }

        List<List<String>> values = new ArrayList<>();
        for (int i = 0; i < lists.get(0).size(); i++) {
            List<String> list = new ArrayList<>();
            for (int y = 0; y < lists.size(); y++) {
                list.add(lists.get(y).get(i));
            }
            values.add(list);
        }

        csvFile.writeFrom(keys, values);

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(mlService + "/validation");
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addPart("file", new FileBody(new File(csvFile.getPath())));
        entityBuilder.addPart("timestamp_column", new StringBody("timestamp", ContentType.TEXT_PLAIN));
        if (mlModel.getDataSchema().getBitAttribute() != null) {
            entityBuilder.addPart("BIT_column",
                    new StringBody(mlModel.getDataSchema().getBitAttribute().getId().toString(), ContentType.TEXT_PLAIN));
            entityBuilder.addPart("error_code", new StringBody(mlModel.getDataSchema().getBitErrorCode().getErrorCode(), ContentType.TEXT_PLAIN));
        }
        post.setEntity(entityBuilder.build());
        HttpResponse response = client.execute(post);
        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseString);
        if (!((Boolean) jsonObject.get("validation_status"))) {
            throw new Exception("Dataset is not valid!");
        }
        switch (mlModel.getMlType()) {
            case RUL -> {
                if (!(Boolean) jsonObject.get("RUL_prediction")) {
                    throw new Exception("Dataset is not valid!");
                }
            }
            case FAULT -> {
                if (!(Boolean) jsonObject.get("fault_detection")) {
                    throw new Exception("Dataset is not valid!");
                }
            }
            case ANOMALY -> {
                if (!(Boolean) jsonObject.get("anomaly_detection")) {
                    throw new Exception("Dataset is not valid!");
                }
            }
        }
        mlModel.setTimeToDataset(Long.parseLong(lists.get(lists.size() - 1).get(lists.get(lists.size() - 1).size() - 1)));
        return csvFile.getPath();
    }

}
