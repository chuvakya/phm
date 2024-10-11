package org.zs.phm3.service.ml;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.ml.Dataset;
import org.zs.phm3.models.ml.DatasetQueued;
import org.zs.phm3.models.ml.DatasetRegistry;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.repository.ml.DatasetRepository;
import org.zs.phm3.service.UomService;
import org.zs.phm3.service.ts.TsKvAttributeService;
import org.zs.phm3.service.ts.TsKvService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementing DatasetService class
 * @author Pavel Chuvak
 */
@Service
public class DatasetServiceImpl implements DatasetService {

    /** Dataset repository */
    @Autowired
    private DatasetRepository datasetRepository;

    /** Uom service */
    @Autowired
    private UomService uomService;

    /** Ts kv attribute service */
    @Autowired
    private TsKvAttributeService tsKvAttributeService;

    /** Ts kv service */
    @Autowired
    private TsKvService tsKvService;

    /** Dataset registry service */
    @Autowired
    private DatasetRegistryService datasetRegistryService;

    /** Data schema ts kv attribute service */
    @Autowired
    private DataSchemaTsKvAttributeService dataSchemaTsKvAttributeService;

    /** Dataset folder */
    @Value("${ml-service.dataset-folder}")
    private String datasetFolder;

    /** Ml service url */
    @Value("${ml-service.ml-rest-service}")
    private String mlService;

    private Long periodForDataset = 3600000L * 5;

    /**
     * Create datasets scheduler
     */
    @Scheduled(fixedRateString = "5000")
    private void runCreateDataset() {
        if (DatasetQueued.getDatasetJobs().size() > 0) {
            Dataset dataset = DatasetQueued.getDatasetJobs().getFirst();
            try {
                if (dataset.getState().equals("QUEUED")) {
                    dataset.setState("RUNNING");
                    datasetRepository.save(dataset);
                }
                createDataset(dataset);
                DatasetQueued.getDatasetJobs().pollFirst();
            } catch(Exception ex) {
                ex.printStackTrace();
                DatasetQueued.getDatasetJobs().pollFirst();
                dataset.setState("FAILED");
                dataset.setErrorMessage(ex.getMessage());
                datasetRepository.save(dataset);
            }
        }
    }

    /**
     * Saving dataset
     * @param dataset dataset
     * @return dataset
     */
    @Override
    public Dataset save(Dataset dataset) {
        return datasetRepository.save(dataset);
    }

    /**
     * Getting all datasets
     * @return all datasets
     */
    @Override
    public List<Dataset> getAll() {
        return (List<Dataset>) datasetRepository.findAll();
    }

    /**
     * Getting dataset by dataset ID
     * @param id dataset ID
     * @return dataset
     */
    @Override
    public Dataset getById(Long id) {
        return datasetRepository.findById(id).get();
    }

    /**
     * Deleting dataset by dataset ID
     * @param id dataset ID
     */
    @Override
    public void deleteById(Long id) {
        datasetRepository.deleteById(id);
    }

    /**
     * Getting all datasets by project ID
     * @param projectId project ID
     * @return all datasets
     */
    @Override
    public List<Dataset> getAllByProjectId(Integer projectId) {
        return datasetRepository.getAllByProjectId(projectId);
    }

    @Override
    public String viewDataset(Long datasetId, Long n) {
        /*
        JSONArray mainArray = new JSONArray();
        try {
            Dataset dataset = datasetRepository.findById(datasetId).get();
            File file = new File(dataset.getPath());
            ReversedLinesFileReader fileReader = new ReversedLinesFileReader(file, StandardCharsets.UTF_8);
            List<String> strings = new ArrayList<>();
            long counter = 0;
            String s;
            while((s = fileReader.readLine()) != null && counter < n)
            {
                strings.add(s);
                counter++;
            }
            fileReader.close();

            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String [] keys = {};
            String stringKeys = bufferedReader.readLine();
            if (stringKeys != null) {
                keys = stringKeys.split(",");
            }
            bufferedReader.close();

            List<Map<String, String>> columnsMap = new ArrayList<>();
            for (String key : keys) {
                Map<String, String> map = new HashMap<>();
                map.put("column", key);
                for (TsKvAttribute tsKvAttribute : dataSchemaTsKvAttributeService.getAllAttributesByDataSchemaId(
                        dataset.getDataSchema().getId())) {
                    if (tsKvAttribute.getAttributeKey().equals(key)) {
                        map.put("text", tsKvAttributeService.getFromUnitAndKey(tsKvAttribute.getUnitId(), key).getName());
                    }
                }
                columnsMap.add(map);
            }

            for (int y = strings.size() - 1; y >= 0; y--) {
                JSONArray stringJson = new JSONArray();
                String [] values = strings.get(y).split(",");
                boolean flag = true;
                for (int i = 0; i < columnsMap.size(); i++) {
                    JSONObject jsonObject = new JSONObject();
                    if (values[i].equals(columnsMap.get(i).get("column"))) {
                        flag = false;
                        break;
                    }
                    if ("timestamp".equals(columnsMap.get(i).get("column"))) {
                        jsonObject.put("text", "Timestamp");
                    } else {
                        jsonObject.put("text", columnsMap.get(i).get("text"));
                    }
                    jsonObject.put("column", columnsMap.get(i).get("column"));
                    jsonObject.put("value", values[i]);
                    stringJson.add(jsonObject);
                }
                if (flag) {
                    mainArray.add(stringJson);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mainArray.toJSONString();*/
        return "";
    }

    /**
     * Create and validation dataset
     * @param dataset dataset
     * @return dataset
     * @throws IOException
     * @throws ParseException
     */
    @Override
    public Dataset createDataset(Dataset dataset) throws IOException, ParseException {
        Long startTimeForElapsed = System.currentTimeMillis();
        String datasetFileName = System.currentTimeMillis() + ".csv";
        FileWriter fileWriter = new FileWriter(datasetFolder + "/" + datasetFileName,
                true);
        List<TsKvAttribute> tsKvAttributes = dataSchemaTsKvAttributeService.getAllAttributesByDataSchemaId(
                dataset.getDataSchema().getId());
        StringBuilder keys = new StringBuilder(tsKvAttributes.get(0).getId().toString());
        for (int i = 1; i < tsKvAttributes.size(); i++) {
            keys.append("," + tsKvAttributes.get(i).getId().toString());
        }
        fileWriter.append(keys.append(",timestamp"));

        Long startTime = dataset.getTimeFrom();
        Long endTime = 0L;
        boolean flag = false;
        boolean f = false;

        while (startTime < dataset.getTimeTo()) {

            if (!flag && startTime + periodForDataset <= dataset.getTimeTo()) {
                endTime = startTime + periodForDataset;
                flag = true;
            } else if (!flag && startTime + periodForDataset > dataset.getTimeTo()) {
                endTime = dataset.getTimeTo();
            } else if (flag && startTime + periodForDataset <= dataset.getTimeTo()) {
                endTime = startTime + periodForDataset;
            } else if (flag && startTime + periodForDataset >= dataset.getTimeTo()) {
                endTime = dataset.getTimeTo();
            }

            List<List<String>> lists = new ArrayList<>();
            List<String> timestamps = new ArrayList<>();
            for (TsKvAttribute tsKvAttribute : tsKvAttributes) {
                List<TsKvEntity> tsKvEntities = tsKvService.getAllFromTimeKeyAndDeviceId(startTime, endTime,
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
                    if (!f) {
                        timestamps.add("" + tsKvEntity.getId().getTs());
                    }
                }
                lists.add(valuesList);
                f = true;

            }
            lists.add(timestamps);

            startTime += periodForDataset + 1;

            f = false;
            for (int i = 0; i < lists.get(0).size(); i++) {
                List<String> lineValues = new ArrayList<>();
                for (int y = 0; y < lists.size(); y++) {
                    lineValues.add(lists.get(y).get(i));
                }
                fileWriter.append("\n" + String.join(",", lineValues));
            }
        }
        fileWriter.flush();
        fileWriter.close();
        dataset.setPath(datasetFileName);

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(mlService + "/validation");
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addPart("full_path", new StringBody("/dataset/" + datasetFileName, ContentType.TEXT_PLAIN));
        entityBuilder.addPart("timestamp_column", new StringBody("timestamp", ContentType.TEXT_PLAIN));
        if (dataset.getDataSchema().getBitAttribute() != null) {
            entityBuilder.addPart("BIT_column",
                    new StringBody(dataset.getDataSchema().getBitAttribute().getId().toString(), ContentType.TEXT_PLAIN));
            entityBuilder.addPart("error_code", new StringBody(dataset.getDataSchema().getBitErrorCode().getErrorCode(), ContentType.TEXT_PLAIN));
        }
        post.setEntity(entityBuilder.build());
        HttpResponse response = client.execute(post);
        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseString);
        if (!((Boolean) jsonObject.get("validation_status"))) {
            dataset.setState("FAILED");
            dataset.setErrorMessage((String) jsonObject.get("error_message"));
            dataset.setValid(false);
            dataset.setAnomaly(false);
            dataset.setFault(false);
            dataset.setRul(false);
        } else {
            dataset.setFault((Boolean) jsonObject.get("fault_detection"));
            dataset.setRul((Boolean) jsonObject.get("RUL_prediction"));
            dataset.setAnomaly((Boolean) jsonObject.get("anomaly_detection"));
            dataset.setValid(true);
            dataset.setState("FINISHED");
        }

        dataset.setCreateTime(System.currentTimeMillis());
        dataset.setElapsedTime(System.currentTimeMillis() - startTimeForElapsed);
        return datasetRepository.save(dataset);
    }

    /*
    @Override
    public Dataset createDataset(Dataset dataset) throws IOException, ParseException {
        Long startTime = System.currentTimeMillis();
        List<List<String>> lists = new ArrayList<>();
        List<String> keys = new ArrayList<>();
        PhmCSV csvFile = new PhmCSV();
        csvFile.setPath(datasetFolder + "/" + System.currentTimeMillis() + ".csv");

        boolean flag = false;
        List<String> timestamps = new ArrayList<>();

        for (TsKvAttribute tsKvAttribute : dataSchemaTsKvAttributeService.getAllAttributesByDataSchemaId(
                dataset.getDataSchema().getId())) {
            List<TsKvEntity> tsKvEntities = tsKvService.getAllFromTimeKeyAndDeviceId(dataset.getTimeFrom(), dataset.getTimeTo(),
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

        List<List<String>> values = new ArrayList<>();
        for (int i = 0; i < lists.get(0).size(); i++) {
            List<String> list = new ArrayList<>();
            for (int y = 0; y < lists.size(); y++) {
                list.add(lists.get(y).get(i));
            }
            values.add(list);
        }

        csvFile.writeFrom(keys, values);
        dataset.setPath(csvFile.getPath());

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(mlService + "/validation");
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addPart("file", new FileBody(new File(dataset.getPath())));
        entityBuilder.addPart("timestamp_column", new StringBody("timestamp", ContentType.TEXT_PLAIN));
        if (dataset.getDataSchema().getBitAttribute() != null) {
            entityBuilder.addPart("BIT_column",
                    new StringBody(dataset.getDataSchema().getBitAttribute().getId().toString(), ContentType.TEXT_PLAIN));
            entityBuilder.addPart("error_code", new StringBody(dataset.getDataSchema().getBitErrorCode().getErrorCode(), ContentType.TEXT_PLAIN));
        }
        post.setEntity(entityBuilder.build());
        HttpResponse response = client.execute(post);
        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        JSONObject jsonObject = (JSONObject) new JSONParser().parse(responseString);
        if (!((Boolean) jsonObject.get("validation_status"))) {
            dataset.setState("FAILED");
            dataset.setErrorMessage((String) jsonObject.get("error_message"));
            dataset.setValid(false);
            dataset.setAnomaly(false);
            dataset.setFault(false);
            dataset.setRul(false);
        } else {
            dataset.setFault((Boolean) jsonObject.get("fault_detection"));
            dataset.setRul((Boolean) jsonObject.get("RUL_prediction"));
            dataset.setAnomaly((Boolean) jsonObject.get("anomaly_detection"));
            dataset.setValid(true);
            dataset.setState("FINISHED");
        }
        dataset.setCreateTime(System.currentTimeMillis());
        dataset.setElapsedTime(System.currentTimeMillis() - startTime);
        return datasetRepository.save(dataset);
    }*/

    /**
     * Getting all datasets by name and project ID
     * @param name dataset name
     * @param projectId project ID
     * @return all datasets
     */
    @Override
    public List<Dataset> getAllByNameAndProjectId(String name, Integer projectId) {
        return datasetRepository.getAllByNameAndProjectId(name, projectId);
    }

    /**
     * Getting json string datasets by project ID
     * @param projectId project ID
     * @return project ID
     */
    public String getListByProjectId(Integer projectId) {
        List<List<Object>> lists = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        for (List<Object> objects : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("datasetId", objects.get(0));
            jsonObject.put("datasetName", objects.get(1));
            jsonObject.put("state", objects.get(2));
            jsonObject.put("unitName", objects.get(3));
            jsonObject.put("dataSchemaName", objects.get(4));
            jsonObject.put("modifiedBy", objects.get(5));
            jsonObject.put("createdTime", objects.get(6));
            jsonObject.put("elapsedTime", objects.get(7));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting count datasets by project ID
     * @param projectId project ID
     * @return count datasets
     */
    @Override
    public Integer getCountByProjectId(Integer projectId) {
        return datasetRepository.getCountByProjectId(projectId);
    }

    /**
     * Getting json string datasets by limit and
     * @param offset
     * @param limit
     * @param projectId
     * @return json string datasets
     */
    @Override
    public String getByOffsetAndLimitAndProjectId(Integer offset, Integer limit, Integer projectId) {
        List<List<Object>> lists = datasetRepository.getByOffsetAndLimitAndProjectId(offset, limit, projectId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("datasetId", list.get(0));
            jsonObject.put("datasetName", list.get(1));
            jsonObject.put("state", list.get(2));
            jsonObject.put("unitName", list.get(3));
            jsonObject.put("dataSchemaName", list.get(4));
            jsonObject.put("modifiedBy", list.get(5));
            jsonObject.put("createTime", list.get(6));
            jsonObject.put("elapsedTime", list.get(7));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Deleting dataset by dataset ID
     * @param datasetId dataset ID
     */
    @Override
    public void deleteByIdSQL(Long datasetId) {
        datasetRepository.deleteByIdSQL(datasetId);
    }

    /**
     * Getting name for new dataset by project ID
     * @param projectId project ID
     * @return name faot new dataset
     */
    @Override
    public String getNameForNewDataset(Integer projectId) {
        Long count = datasetRegistryService.getSizeByProjectId(projectId) + 1;
        DatasetRegistry datasetRegistry = new DatasetRegistry("Dataset " + count.toString(), projectId);
        datasetRegistryService.save(datasetRegistry);
        return datasetRegistry.getName();
    }

    /**
     * Getting json string by lists
     * @param lists lists
     * @return json string
     */
    private String getJSON(List<List<Object>> lists) {
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("val", list.get(0));
            jsonObject.put("text", list.get(1));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting json string ID and name datasets by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string
     */
    @Override
    public String getAllIdAndNameByDataSchemaId(Long dataSchemaId) {
        List<List<Object>> lists = datasetRepository.getAllIdAndNameByDataSchemaId(dataSchemaId);
        return getJSON(lists);
    }

    /**
     * Getting json string all ID and name datasets anomaly type by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string all ID and name datasets anomaly type
     */
    @Override
    public String getAllIdAndNameByDataSchemaIdAnomaly(Long dataSchemaId) {
        return getJSON(datasetRepository.getAllIdAndNameByDataSchemaIdAnomaly(dataSchemaId));
    }

    /**
     * Getting json string all ID and name datasets fault type by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string all ID and name datasets fault type
     */
    @Override
    public String getAllIdAndNameByDataSchemaIdFault(Long dataSchemaId) {
        return getJSON(datasetRepository.getAllIdAndNameByDataSchemaIdFault(dataSchemaId));
    }

    /**
     * Getting json string all ID and name datasets rul type by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string all ID and name datasets rul type
     */
    @Override
    public String getAllIdAndNameByDataSchemaIdRUL(Long dataSchemaId) {
        return getJSON(datasetRepository.getAllIdAndNameByDataSchemaIdRUL(dataSchemaId));
    }
}
