package org.zs.phm3.service.ml;

import org.zs.phm3.models.ml.DataSchema;
import org.zs.phm3.models.ml.DataSchemaRegistry;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.repository.ml.DataSchemaRegistryRepository;
import org.zs.phm3.repository.ml.DataSchemaRepository;
import org.zs.phm3.repository.ml.DataSchemaTsKvAttributeRepository;
import org.zs.phm3.repository.ts.TsKvRepository;
import org.zs.phm3.service.UomService;
import org.zs.phm3.service.ts.TsKvAttributeService;
import org.zs.phm3.service.unit.UnitService;
import org.zs.phm3.util.mapping.PhmUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementing DataSchemaService class
 * @author Pavel Chuvak
 */
@Service
public class DataSchemaServiceImpl implements DataSchemaService {

    /** Data schema repository */
    @Autowired
    private DataSchemaRepository dataSchemaRepository;

    /** Data schema registry repository */
    @Autowired
    private DataSchemaRegistryRepository dataSchemaRegistryRepository;

    /** Unit service */
    @Autowired
    private UnitService unitService;

    /** Uom service */
    @Autowired
    private UomService uomService;

    /** Ts kv attribute service */
    @Autowired
    private TsKvAttributeService tsKvAttributeService;

    /** Data schema ts kv attribute repository */
    @Autowired
    private DataSchemaTsKvAttributeRepository dataSchemaTsKvAttributeRepository;

    /** Ts kv repository */
    @Autowired
    private TsKvRepository tsKvRepository;

    /**
     * Getting data schema by data schema ID
     * @param id data schema ID
     * @return data schema
     */
    @Override
    public DataSchema getById(Long id) {
        return dataSchemaRepository.findById(id).get();
    }

    /**
     * Saving data schema
     * @param dataSchema data schema
     * @return data schema
     */
    @Override
    public DataSchema save(DataSchema dataSchema) {
        return dataSchemaRepository.save(dataSchema);
    }

    /**
     * Getting list all data schemas by project ID
     * @param projectId project ID
     * @return list data schemas
     */
    @Override
    public List<DataSchema> getAllDataSchemasByProject(Integer projectId) {
        return dataSchemaRepository.getAllByProjectId(projectId);
    }

    /**
     * Deleting data schema by data schema ID
     * @param dataSchemaId data schema ID
     */
    @Override
    public void deleteById(Long dataSchemaId) {
        dataSchemaRepository.deleteById(dataSchemaId);
    }

    /**
     * Getting json string data schema preview by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string data schema preview
     */
    @Override
    public String getDataSchemaPreview(Long dataSchemaId) {
        JSONArray jsonArray = new JSONArray();
        for (TsKvAttribute tsKvAttribute : dataSchemaTsKvAttributeRepository.getAllAttributesByDataSchemaId(dataSchemaId)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("attrName", tsKvAttribute.getName());
            jsonObject.put("unitName", unitService.getUnitById(tsKvAttribute.getUnitId()).getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting json string all id and name by unit ID
     * @param unitId unit ID
     * @return json string all id and name
     */
    @Override
    public String getAllIdAndNameByUnitId(String unitId) {
        List<List<Object>> dataSchemas = dataSchemaRepository.getAllIdAndNameByUnitId(unitId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> dataSchema : dataSchemas) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("dataSchemaId", dataSchema.get(0));
            jsonObject.put("dataSchemaName", dataSchema.get(1));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting full period for data schema by ts kv attribute ID
     * @param attributeId ts kv attribute ID
     * @return full period for data schema
     */
    @Override
    public String getFullPeriodForDataSchema(Long attributeId) {
        TsKvAttribute tsKvAttribute = tsKvAttributeService.getById(attributeId);
        TsKvEntity first = tsKvRepository.getFirstByDeviceIdAndKey(tsKvAttribute.getDeviceId(), tsKvAttribute.getAttributeKey());
        TsKvEntity last = tsKvRepository.getLastByDeviceIdAndKey(tsKvAttribute.getDeviceId(), tsKvAttribute.getAttributeKey());
        JSONObject jsonObject = new JSONObject();
        if (first == null) {
            jsonObject.put("timeFrom", null);
        } else {
            jsonObject.put("timeFrom", first.getId().getTs());
        }
        if (last == null) {
            jsonObject.put("timeTo", null);
        } else {
            jsonObject.put("timeTo", last.getId().getTs());
        }
        return jsonObject.toJSONString();
    }

    /**
     * Getting max period for data schema
     * @param dataSchema data schema
     * @return json string
     */
    @Override
    public String getMaxPeriodForDataSchema(DataSchema dataSchema) {
        List<Long> from = new ArrayList<>();
        List<Long> to = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        for (TsKvAttribute tsKvAttribute : dataSchemaTsKvAttributeRepository.getAllAttributesByDataSchemaId(dataSchema.getId())) {
            TsKvEntity tsKvEntityFrom = tsKvRepository.getFirstByDeviceIdAndKey(tsKvAttribute.getDeviceId(),
                    tsKvAttribute.getAttributeKey());
            if (tsKvEntityFrom == null) {
                jsonObject.put("timeFrom", null);
                jsonObject.put("timeTo", null);
                return jsonObject.toJSONString();
            }
            TsKvEntity tsKvEntityTo = tsKvRepository.getLastByDeviceIdAndKey(tsKvAttribute.getDeviceId(),
                    tsKvAttribute.getAttributeKey());
            from.add(tsKvEntityFrom.getId().getTs());
            to.add(tsKvEntityTo.getId().getTs());
        }
        Long maxFrom = Collections.max(from);
        Long minTo = Collections.min(to);
        if (maxFrom <= minTo) {
            jsonObject.put("timeFrom", maxFrom);
            jsonObject.put("timeTo", minTo);
        } else {
            jsonObject.put("timeFrom", null);
            jsonObject.put("timeTo", null);
        }
        return jsonObject.toJSONString();
    }

    /**
     * Getting dataset preview by data schema ID and count values
     * @param dataSchemaId data schema ID
     * @param n count values
     * @return json string
     */
    @Override
    public String getDatasetPreview(Long dataSchemaId, Long n) {
        List<List<TsKvEntity>> lists = new ArrayList<>();
        List<UnitEntity> units = new ArrayList<>();
        List<String> columnNames = new ArrayList<>();
        List<TsKvAttribute> tsKvAttributes = new ArrayList<>();
        for (TsKvAttribute tsKvAttribute : dataSchemaTsKvAttributeRepository.getAllAttributesByDataSchemaId(dataSchemaId)) {
            UnitEntity unitEntity = unitService.getUnitById(tsKvAttribute.getUnitId());
            List<TsKvEntity> tsKvEntities = tsKvRepository.getLastByDeviceIdAndKeyAndN(tsKvAttribute.getDeviceId(),
                    tsKvAttribute.getAttributeKey(), n);
            lists.add(tsKvEntities);
            columnNames.add(tsKvAttribute.getName());
            units.add(unitEntity);
            tsKvAttributes.add(tsKvAttribute);
        }
        JSONArray arrayValues = new JSONArray();
        for (int y = 0; y < n; y++) {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < columnNames.size() + 1; i++) {
                JSONObject jsonObject = new JSONObject();
                if (i == columnNames.size()) {
                    jsonObject.put("text", "Timestamp");
                    jsonObject.put("value", lists.get(columnNames.size() - 1).get(y).getId().getTs());
                    jsonObject.put("column", "Timestamp");
                } else {
                    jsonObject.put("text", units.get(i).getName() + " - " + columnNames.get(i));
                    jsonObject.put("column", tsKvAttributes.get(i).getId().toString());
                    switch (tsKvAttributes.get(i).getDataType()) {
                        case BOOLEAN -> jsonObject.put("value", lists.get(i).get(y).getBooleanValue());
                        case DOUBLE -> jsonObject.put("value", uomService.convert(tsKvAttributes.get(i).getUomInput(),
                                tsKvAttributes.get(i).getUomOutput(), lists.get(i).get(y).getDoubleValue()));
                        case STRING -> jsonObject.put("value", lists.get(i).get(y).getStrValue());
                        case LONG -> jsonObject.put("value", uomService.convert(tsKvAttributes.get(i).getUomInput(),
                                tsKvAttributes.get(i).getUomOutput(), lists.get(i).get(y).getLongValue().doubleValue()));
                    }
                }
                jsonArray.add(jsonObject);
            }
            arrayValues.add(jsonArray);
        }
        return arrayValues.toJSONString();
    }

    /**
     * Getting count data schemas by project ID
     * @param projectId project ID
     * @return count
     */
    @Override
    public Integer getCountByProjectId(Integer projectId) {
        return dataSchemaRepository.getCountByProjectId(projectId);
    }

    /**
     * Getting all by offset and limit and project ID
     * @param offset offset
     * @param limit limit
     * @param projectId project ID
     * @return json string
     */
    @Override
    public String getByOffsetAndLimitAndProjectId(Integer offset, Integer limit, Integer projectId) {
        List<List<Object>> lists = dataSchemaRepository.getByOffsetAndLimitAndProjectId(offset, limit, projectId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("dataSchemaId", list.get(0));
            jsonObject.put("dataSchemaName", list.get(1));
            jsonObject.put("createdTime", list.get(2));
            jsonObject.put("modifiedBy", list.get(3));
            jsonObject.put("unitName", list.get(4));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Deleting data schema by data schame ID
     * @param dataSchemaId data schema ID
     */
    @Override
    public void deleteByIdSQL(Long dataSchemaId) {
        dataSchemaRepository.deleteByIdSQL(dataSchemaId);
    }

    /**
     * Getting save data schema by data schema ID
     * @param dataSchemaId data schema ID
     * @return json string
     */
    @Override
    public String getSaveDataSchemaById(Long dataSchemaId) {
        List<List<Object>> lists = dataSchemaRepository.getById(dataSchemaId);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArrayAttr = new JSONArray();
        jsonObject.put("dataSchemaName", lists.get(0).get(2));
        jsonObject.put("dataSchemaId", lists.get(0).get(0));
        jsonObject.put("unitId", PhmUtil.toLongUUID((lists.get(0).get(1).toString())));
        for (List<Object> tsKvAttribute :
                dataSchemaTsKvAttributeRepository.getAllAttributeIdAndNameAndUnitNameByDataSchemaId(dataSchemaId)) {
            JSONObject object = new JSONObject();
            object.put("unitName", tsKvAttribute.get(2));
            object.put("attrName", tsKvAttribute.get(1));
            object.put("attrId", tsKvAttribute.get(0));
            jsonArrayAttr.add(object);
        }
        jsonObject.put("attributes", jsonArrayAttr);
        jsonObject.put("bitAttributeId", lists.get(0).get(3));
        jsonObject.put("bitErrorCodeId", lists.get(0).get(4));
        return jsonObject.toJSONString();
    }

    /**
     * Deleting dataset param by data schema ID
     * @param dataSchemaId data schema ID
     */
    @Override
    public void deleteDatasetParamByDataSchemaId(Long dataSchemaId) {
        dataSchemaRepository.deleteDatasetParamByDataSchemaId(dataSchemaId);
    }

    /**
     * Getting name for new data schema by project ID
     * @param projectId project ID
     * @return name
     */
    @Override
    public String getNameForNewDataSchema(Integer projectId) {
        Long count = dataSchemaRegistryRepository.getSizeByProjectId(projectId) + 1;
        DataSchemaRegistry dataSchemaRegistry = new DataSchemaRegistry("Data Schema " + count.toString(), projectId);
        dataSchemaRegistryRepository.save(dataSchemaRegistry);
        return dataSchemaRegistry.getName();
    }

    /**
     * Getting all data schemas by data schema name and project ID
     * @param name data schema name
     * @param projectId project ID
     * @return all data schemas
     */
    @Override
    public List<DataSchema> getAllByNameAndProjectId(String name, Integer projectId) {
        return dataSchemaRepository.getAllByNameAndProjectId(name, projectId);
    }
}
