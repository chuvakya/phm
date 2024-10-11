package org.zs.phm3.service.ts;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.ts.TsKvAttribute;
import org.zs.phm3.models.ts.TsKvAttributeType;
import org.zs.phm3.models.ts.TsKvEntity;
import org.zs.phm3.models.unit.UnitAttributeKey;
import org.zs.phm3.repository.ts.TsKvAttributeRepository;
import org.zs.phm3.repository.ts.TsKvRepository;
import org.zs.phm3.service.UomService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TsKvAttributeServiceImpl implements TsKvAttributeService {

    @Autowired
    private TsKvAttributeRepository tsKvAttributeRepository;

    @Autowired
    private TsKvRepository tsKvRepository;

    @Autowired
    private TsKvAttributeTypeService tsKvAttributeTypeService;

    @Autowired
    private UomService uomService;

    @Override
    public TsKvAttribute save(TsKvAttribute tsAttributeForSave) {
        return tsKvAttributeRepository.save(tsAttributeForSave);
    }

    @Override
    public List<TsKvAttribute> saveAll(List<TsKvAttribute> tsKvListForSave) {
        return (List<TsKvAttribute>) tsKvAttributeRepository.saveAll(tsKvListForSave);
    }

    @Override
    public TsKvAttribute findById(UnitAttributeKey tsAttribId) throws PhmException {
        /*return tsKvAttributeRepository.findById(tsAttribId).orElseThrow(
                () -> (new PhmException("TsKvAttribute with id " + tsAttribId +
                        " not found", PhmErrorCode.ITEM_NOT_FOUND))
        );*/ return null;
    }

    @Override
    public List<TsKvAttribute> getAllTsKvAttributes() {
        return (List<TsKvAttribute>) tsKvAttributeRepository.findAll();
    }

/*    @Override
    public List<TsKvAttribute> getAllTsKvAttributesByUnit(String unitId) {
        return (List<TsKvAttribute>) tsKvAttributeRepository.findAllByUnitId(unitId);
    }*/

    @Override
    public void deleteByIdSQL(Long attributeId) {
        tsKvAttributeRepository.deleteByIdSQL(attributeId);
    }

    @Override
    public void fillingDataFromTsKv() {
        /*
        ArrayList<TsKvAttribute> tsAttributesList= new ArrayList<>();
        ArrayList<String> unitIdsList= (ArrayList<String>) unitRepository.getUnitIds();
        unitIdsList.forEach(id->{
            List<String> unitIdAttributesList= tsKvRepository.getAllAttrKeysForUnitId(id);
            unitIdAttributesList.forEach(key->{
                TsKvAttribute tsAttribute=new TsKvAttribute(new UnitAttributeKey(id, key),key);
                tsAttribute.setUomInput(mappingUomForAttribute(key));
                tsAttribute.setUomOutput(mappingUomForAttribute(key));
                tsAttributesList.add(tsAttribute);
//                save(tsAttribute);
            });
        });
        saveAll(tsAttributesList);
        */
    }

    @Override
    public String getAllForUnit(String unitId) {
        List<List<Object>> lists = tsKvAttributeRepository.getAllForUnit(unitId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", list.get(0));
            jsonObject.put("name", list.get(1));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public List<String> getAllDeviceIdByUnit(String unitId) {
        List<List<Object>> lists = tsKvAttributeRepository.getAllDeviceIdByUnit(unitId);
        List<String> strings = new ArrayList<>();
        for (List<Object> list : lists) {
            strings.add((String) list.get(0));
        }
        return strings;
    }

    @Override
    public String getAllForUnitForUI(String unitId)  {
        List<TsKvAttributeType> tsKvAttributeTypes = tsKvAttributeTypeService.getAllAttributeTypeByUnitId(unitId);
        JSONArray jsonArray = new JSONArray();
        for (TsKvAttributeType tsKvAttributeType : tsKvAttributeTypes) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", tsKvAttributeType.getId());
            jsonObject.put("name", tsKvAttributeType.getName());
            jsonObject.put("hasChild", true);
            jsonArray.add(jsonObject);
            for (TsKvAttribute tsKvAttribute : tsKvAttributeRepository.getAllByUnitIdAndTsKvAttributeType_Id(unitId,
                    tsKvAttributeType.getId())) {
                JSONObject json = new JSONObject();
                TsKvEntity tsKvEntity = tsKvRepository.getLastByDeviceIdAndKey(tsKvAttribute.getDeviceId(),
                        tsKvAttribute.getAttributeKey());
                String uomSymbol = "";
                if (tsKvAttribute.getUomOutput() != null) {
                    uomSymbol = tsKvAttribute.getUomOutput().getSymbol();
                }
                if (tsKvEntity != null) {
                    switch (tsKvAttribute.getDataType()) {
                        case BOOLEAN -> json.put("value", tsKvEntity.getBooleanValue().toString());
                        case STRING -> json.put("value", tsKvEntity.getStrValue());
                        case LONG -> json.put("value", uomService.convert(tsKvAttribute.getUomInput(),
                                tsKvAttribute.getUomOutput(),
                                Double.parseDouble(tsKvEntity.getLongValue().toString())).toString() +
                                " " + uomSymbol);
                        case DOUBLE -> json.put("value", uomService.convert(tsKvAttribute.getUomInput(),
                                tsKvAttribute.getUomOutput(), tsKvEntity.getDoubleValue()).toString() + " " + uomSymbol);
                    }
                    json.put("lastUpdate", tsKvEntity.getId().getTs());

                } else {
                    json.put("value", "");
                    json.put("lastUpdate", "");

                }
                json.put("name", tsKvAttribute.getName());
                json.put("isTable", tsKvAttribute.getTable());
                json.put("id", tsKvAttribute.getId());
                json.put("pid", tsKvAttributeType.getId());
                jsonArray.add(json);
            }
        }
        return jsonArray.toJSONString();
    }

    @Override
    public String getAllForDataSchema(String unitId) {
        List<TsKvAttributeType> tsKvAttributeTypes = tsKvAttributeTypeService.getAllAttributeTypeByUnitId(unitId);
        JSONArray jsonArray = new JSONArray();
        for (TsKvAttributeType tsKvAttributeType : tsKvAttributeTypes) {
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArrayAttr = new JSONArray();
            for (TsKvAttribute tsKvAttribute : tsKvAttributeRepository.getAllByUnitIdAndTsKvAttributeType_Id(unitId,
                    tsKvAttributeType.getId())) {
                JSONObject json = new JSONObject();
                TsKvEntity tsKvEntity = tsKvRepository.getLastByDeviceIdAndKey(tsKvAttribute.getDeviceId(),
                        tsKvAttribute.getAttributeKey());
                json.put("id", tsKvAttribute.getId());
                json.put("name", tsKvAttribute.getName());
                jsonArrayAttr.add(json);
            }
            jsonObject.put("category", tsKvAttributeType.getName());
            jsonObject.put("attributes", jsonArrayAttr);
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public String getByIdForUI(Long attributeId) {
        List<List<Object>> tsKvAttribute = tsKvAttributeRepository.getByIdSQL(attributeId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", tsKvAttribute.get(0).get(0));
        jsonObject.put("name", tsKvAttribute.get(0).get(1));
        jsonObject.put("key", tsKvAttribute.get(0).get(2));
        jsonObject.put("dataType", tsKvAttribute.get(0).get(3));
        jsonObject.put("categoryName", tsKvAttribute.get(0).get(4));
        jsonObject.put("deviceId", tsKvAttribute.get(0).get(5));
        jsonObject.put("isTable", tsKvAttribute.get(0).get(6));
        jsonObject.put("uomInputId", tsKvAttribute.get(0).get(7));
        jsonObject.put("uomOutputId", tsKvAttribute.get(0).get(8));
        return jsonObject.toJSONString();
    }

    @Override
    public List<TsKvAttribute> getAllByDeviceIdAndUnitId(String deviceId, String unitId) {
        return tsKvAttributeRepository.getAllByDeviceIdAndUnitId(deviceId, unitId);
    }

    @Override
    public String getDataAttributeChart(TsKvAttribute tsKvAttribute, Long n) {
        if (!tsKvAttribute.getDataType().toString().equals("STRING") && !tsKvAttribute.getDataType().toString().equals("BOOLEAN")) {
            return getDataAttributeTable(tsKvAttribute, n);
        }
        JSONObject jsonObject = new JSONObject();
        List<TsKvEntity> tsKvEntities = tsKvRepository.getLastByDeviceIdAndKeyAndN(tsKvAttribute.getDeviceId(),
                tsKvAttribute.getAttributeKey(), n);
        JSONArray jsonArrayData = new JSONArray();
        JSONArray jsonArrayMap = new JSONArray();

        List<String> valuesString = new ArrayList<>();

        if (tsKvAttribute.getDataType().toString().equals("STRING")) {
            for (TsKvEntity tsKvEntity : tsKvEntities) {
                if (!valuesString.contains(tsKvEntity.getStrValue())) {
                    valuesString.add(tsKvEntity.getStrValue());
                }
            }
            int i = 0;
            for (String s : valuesString) {
                JSONObject objectMap = new JSONObject();
                objectMap.put("value", i++);
                objectMap.put("attrValue", s);
                jsonArrayMap.add(objectMap);
            }
        } else {
            JSONObject object1 = new JSONObject();
            JSONObject object2 = new JSONObject();
            object1.put("value", 0);
            object1.put("attrValue", false);

            object2.put("value", 1);
            object2.put("attrValue", true);
            jsonArrayMap.add(object1);
            jsonArrayMap.add(object2);
        }
        jsonObject.put("legend", jsonArrayMap);

        for (TsKvEntity tsKvEntity : tsKvEntities) {
            JSONObject jsonData = new JSONObject();
            if (tsKvAttribute.getDataType().toString().equals("STRING")) {
                int i = 0;
                for (String s : valuesString) {
                    if (s.equals(tsKvEntity.getStrValue())) {
                        jsonData.put("value", i);
                        break;
                    }
                    i++;
                }
            } else {
                jsonData.put("value", tsKvEntity.getBooleanValue() ? 1 : 0);
            }

            jsonData.put("timestamp", tsKvEntity.getId().getTs());
            jsonArrayData.add(jsonData);
        }
        jsonObject.put("data", jsonArrayData);
        return jsonObject.toJSONString();
    }

    @Override
    public String getDataAttributeTable(TsKvAttribute tsKvAttribute, Long n) {
        JSONObject jsonObject = new JSONObject();
        List<TsKvEntity> tsKvEntities = tsKvRepository.getLastByDeviceIdAndKeyAndN(tsKvAttribute.getDeviceId(),
                tsKvAttribute.getAttributeKey(), n);
        JSONArray jsonArrayData = new JSONArray();

        for (TsKvEntity tsKvEntity : tsKvEntities) {
            JSONObject jsonData = new JSONObject();
            switch (tsKvAttribute.getDataType()) {
                case BOOLEAN -> jsonData.put("value", tsKvEntity.getBooleanValue());
                case STRING -> jsonData.put("value", tsKvEntity.getStrValue());
                case LONG -> jsonData.put("value", uomService.convert(tsKvAttribute.getUomInput(),
                        tsKvAttribute.getUomOutput(), Double.parseDouble(tsKvEntity.getLongValue().toString())));
                case DOUBLE -> jsonData.put("value", uomService.convert(tsKvAttribute.getUomInput(),
                        tsKvAttribute.getUomOutput(), tsKvEntity.getDoubleValue()));
            }
            jsonData.put("timestamp", tsKvEntity.getId().getTs());
            jsonArrayData.add(jsonData);
        }
        jsonObject.put("data", jsonArrayData);
        return jsonObject.toJSONString();
    }

    @Override
    public TsKvAttribute saveIfNotExist(TsKvAttribute tsKvAttribute) {
        TsKvAttribute tsKvAttr = tsKvAttributeRepository.findById(tsKvAttribute.getId()).get();
        if (tsKvAttr == null) {
            return tsKvAttributeRepository.save(tsKvAttribute);
        }
        return null;
    }

    @Override
    public String getAllNameAndKeyAndSymbolByUnitId(String unitId) {
        List<List<Object>> tsKvAttributes =
                tsKvAttributeRepository.getAllIdAndNameAndDataTypeAndUomOutputSymbolByUnitId(unitId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> tsKvAttribute : tsKvAttributes) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", tsKvAttribute.get(1));
            jsonObject.put("id", tsKvAttribute.get(0));
            jsonObject.put("symbol", tsKvAttribute.get(3));
            jsonObject.put("dataType", tsKvAttribute.get(2));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    public TsKvAttribute getById(Long attributeId) {
        return tsKvAttributeRepository.findById(attributeId).get();
    }

    @Override
    public Long getCountByNameAndUnitId(String name, String unitId) {
        return tsKvAttributeRepository.getCountByNameAndUnitId(name, unitId);
    }


}
