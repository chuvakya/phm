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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.ml.MlAlgorithmType;
import org.zs.phm3.models.ml.MlModel;
import org.zs.phm3.models.ml.MlServiceResult;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.service.UomService;
import org.zs.phm3.service.ml.DataSchemaTsKvAttributeService;
import org.zs.phm3.service.ml.MlModelService;
import org.zs.phm3.service.ml.MlServiceResultService;
import org.zs.phm3.service.ts.TsKvService;
import org.zs.phm3.util.parquet.csv.PhmCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduledMlService {

    @Autowired
    private MlModelService mlModelService;

    @Autowired
    private MlServiceResultService mlServiceResultService;

    @Autowired
    private TsKvService tsKvService;

    @Autowired
    private DataSchemaTsKvAttributeService dataSchemaTsKvAttributeService;

    @Autowired
    private UomService uomService;

    @Value("${ml-service.dataset-folder}")
    private String datasetFolder;

    @Value("${ml-service.ml-rest-service}")
    private String mlRestService;

    @Transactional
    @Scheduled(fixedRateString = "${ml-service.autoscan.fixed.rate}", initialDelayString = "${ml-service.autoscan.initial.delay}")
    public void task() {
        List<MlModel> mlModels = mlModelService.getAllRunningModelsForScheduler();
        for (MlModel mlModel : mlModels) {
            if (mlModel.getModelId() == null) {
                continue;
            }
            List<TsKvAttribute> datasetAttributes = dataSchemaTsKvAttributeService.getAllAttributesByDataSchemaId(
                    mlModel.getDataSchema().getId());
            TsKvAttribute firstTsKvAttribute;
            if (mlModel.getModelSchema().getDataset().getDataSchema().getBitAttribute() != null &&
                    datasetAttributes.get(0).getId() == mlModel.getModelSchema().getDataset().getDataSchema().getBitAttribute().getId()) {
                firstTsKvAttribute = datasetAttributes.get(1);
            } else {
                firstTsKvAttribute = datasetAttributes.get(0);
            }

            List<TsKvEntity> tsKvs;
            Long startTime = mlModel.getLastScan() + 1;
            Long endTime = System.currentTimeMillis();
            Integer windowLength = 1000000;
            if (mlModel.getModelSchema().getWindowLength() != null) {
                windowLength = mlModel.getModelSchema().getWindowLength();
            }
            tsKvs = tsKvService.getAllFromTimeKeyAndDeviceIdLimit(startTime, endTime,
                    firstTsKvAttribute.getDeviceId(), firstTsKvAttribute.getAttributeKey(), windowLength);

            if (tsKvs.size() == 0 || (mlModel.getModelSchema().getWindowLength() != null &&
                    mlModel.getModelSchema().getWindowLength() > tsKvs.size())) {
                continue;
            }

            List<String> keys = new ArrayList<>();
            List<List<Object>> lists = new ArrayList<>();
            for (TsKvAttribute kvAttribute : datasetAttributes) {

                if (mlModel.getModelSchema().getDataset().getDataSchema().getBitAttribute() != null &&
                    mlModel.getModelSchema().getDataset().getDataSchema().getBitAttribute().getId() == kvAttribute.getId()) {
                    continue;
                }

                List<TsKvEntity> tsKvEntities = null;
                if (firstTsKvAttribute.getId() == kvAttribute.getId()) {
                    tsKvEntities = tsKvs;
                } else {
                    tsKvEntities = tsKvService.getAllFromTimeKeyAndDeviceIdLimit(startTime, endTime,
                            kvAttribute.getDeviceId(), kvAttribute.getAttributeKey(), windowLength);
                }
                List<Object> valuesList = new ArrayList<>();

                for (TsKvEntity tsKvEntity : tsKvEntities) {
                    switch (kvAttribute.getDataType()) {
                        case DOUBLE -> valuesList.add(uomService.convert(kvAttribute.getUomInput(), kvAttribute.getUomOutput(),
                                tsKvEntity.getDoubleValue()));
                        case LONG -> valuesList.add(uomService.convert(kvAttribute.getUomInput(), kvAttribute.getUomOutput(),
                                Double.parseDouble(tsKvEntity.getLongValue().toString())));
                        case STRING -> valuesList.add(tsKvEntity.getStrValue());
                        case BOOLEAN -> valuesList.add(tsKvEntity.getBooleanValue());
                    }
                }
                lists.add(valuesList);
                keys.add(kvAttribute.getId().toString());
            }

            List<List<String>> values = new ArrayList<>();
            for (int i = 0; i < lists.get(0).size(); i++) {
                List<String> list = new ArrayList<>();
                for (int y = 0; y < lists.size(); y++) {
                    list.add(lists.get(y).get(i).toString());
                }
                values.add(list);
            }

            String pathCsvFile = datasetFolder + "/temp.csv";
            PhmCSV phmCSV = new PhmCSV(pathCsvFile);
            phmCSV.writeFrom(keys, values);

            HttpPost post = new HttpPost(mlRestService + "/predict");
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.addPart("full_path", new StringBody("/dataset/temp.csv", ContentType.TEXT_PLAIN));
            if (mlModel.getModelSchema().getWindowLength() != null) {
                entityBuilder.addPart("window_length",
                        new StringBody(mlModel.getModelSchema().getWindowLength().toString(), ContentType.TEXT_PLAIN));
            }
            entityBuilder.addPart("model_id", new StringBody(mlModel.getModelId().toString(),
                    ContentType.TEXT_PLAIN));
            post.setEntity(entityBuilder.build());
            HttpClient client = HttpClientBuilder.create().build();
            try {
                HttpResponse response = client.execute(post);
                String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseString);
                mlModel.setLastScan(tsKvs.get(tsKvs.size() - 1).getId().getTs());
                mlModelService.save(mlModel);

                switch(MlAlgorithmType.getTaskNumber(mlModel.getMlAlgorithm())) {
                    case 1 -> {

                        if (mlModel.getMlAlgorithm().toString().equals("SIX_SIGMA") ||
                                mlModel.getMlAlgorithm().toString().equals("THREE_SIGMA")) {
                            JSONArray jsonArray = (JSONArray) jsonObject.get("prediction");
                            for (int i = 0; i < jsonArray.size(); i++) {
                                if ((long) jsonArray.get(i) == 1) {
                                    mlServiceResultService.save(new MlServiceResult(
                                            (long) tsKvs.get(i).getId().getTs(),
                                            "1", mlModel.getUnitId(), mlModel));
                                }
                            }
                        } else {
                            if ((double) jsonObject.get("prediction") == 1.0) {
                                mlServiceResultService.save(new MlServiceResult(
                                        tsKvs.get(tsKvs.size() - 1).getId().getTs(),
                                        "1", mlModel.getUnitId(), mlModel));
                            }
                        }
                    }
                    case 2,3 -> {
                        mlServiceResultService.save(new MlServiceResult(
                                tsKvs.get(tsKvs.size() - 1).getId().getTs(),
                                jsonObject.get("prediction").toString(), mlModel.getUnitId(), mlModel));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}



