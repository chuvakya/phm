package org.zs.phm3.service.ptl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.models.ptl.BIT;
import org.zs.phm3.models.ptl.ModelPTL;
import org.zs.phm3.models.unit.UnitEntity;
import org.zs.phm3.repository.ptl.BITRepository;
import org.zs.phm3.repository.unit.UnitRepository;
import org.zs.phm3.service.ts.TsKvAttributeService;
import org.zs.phm3.service.unit.UnitService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementing BITService class
 * @author Pavel Chuvak
 */
@Service
public class BITServiceImpl implements BITService {

    /** BIT repository */
    @Autowired
    private BITRepository bitRepository;

    /** Ts kv attribute service */
    @Autowired
    private TsKvAttributeService tsKvAttributeService;

    /** Unit repository */
    @Autowired
    private UnitRepository unitRepository;

    /**
     * Saving BIT
     * @param bit bit
     * @return bit
     */
    @Override
    public BIT save(BIT bit) {
        return bitRepository.save(bit);
    }

    /**
     * Getting json string by offset and limit
     * @param offset offset
     * @param limit limit
     * @return json string
     */
    @Override
    public String getAllByOffsetAndLimit(Integer offset, Integer limit) {
        List<List<Object>> lists = bitRepository.getAllByOffsetAndLimit(offset, limit);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errorCode", list.get(0));
            jsonObject.put("name", list.get(1));
            jsonObject.put("revision", list.get(7));
            jsonObject.put("modifiedBy", list.get(2));
            jsonObject.put("updateTime", list.get(3));
            jsonObject.put("modelName", list.get(4));
            jsonObject.put("manufacturerName", list.get(5));
            jsonObject.put("id", list.get(6));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Deleting by bit ID
     * @param bitId bit ID
     */
    @Override
    public void deleteByIdSQL(Long bitId) {
        bitRepository.deleteByIdSQL(bitId);
    }

    /**
     * Getting bit by bit ID
     * @param bitId bit ID
     * @return bit
     */
    @Override
    public BIT getById(Long bitId) {
        return bitRepository.findById(bitId).get();
    }

    /**
     * Getting count bit's
     * @return count bit's
     */
    @Override
    public Long getCount() {
        return bitRepository.getCount();
    }

    /**
     * Getting map from list
     * @param list list
     * @return map
     */
    @Override
    public List<Map<String, String>> getMapFromList(List<String> list) {
        List<Map<String, String>> listMap = new ArrayList<>();
        for (String s : list) {
            Map<String, String> map = new HashMap<>();
            map.put("text", s);
            map.put("val", s);
            listMap.add(map);
        }
        return listMap;
    }

    /**
     * Getting list error codes by model ID
     * @param modelId model ID
     * @return list error codes
     */
    @Override
    public List<String> getErrorCodesByModelId(Long modelId) {
        return bitRepository.getErrorCodesByModelId(modelId);
    }

    /**
     * Getting json string by bit ID
     * @param bitId bit ID
     * @return json string
     */
    @Override
    public String getSaveBITById(Long bitId) {
        List<List<Object>> lists = bitRepository.getSaveBITById(bitId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", lists.get(0).get(0));
        jsonObject.put("name", lists.get(0).get(1));
        jsonObject.put("cause", lists.get(0).get(2));
        jsonObject.put("description", lists.get(0).get(3));
        jsonObject.put("errorCode", lists.get(0).get(4));
        jsonObject.put("priority", lists.get(0).get(5));
        jsonObject.put("severity", lists.get(0).get(6));
        jsonObject.put("troubleshooting", lists.get(0).get(7));
        jsonObject.put("modelId", lists.get(0).get(8));
        jsonObject.put("manufacturerId", lists.get(0).get(9));
        jsonObject.put("unitTypeId", lists.get(0).get(10));
        return jsonObject.toJSONString();
    }

    /**
     * Getting json string bit codes by ts kv attribute ID
     * @param tsKvAttributeId ts kv attribute ID
     * @return json string bit codes
     */
    @Override
    public String getBITCodesByTsKvAttributeId(Long tsKvAttributeId) {
        Long modelPTLId = unitRepository.getModelPTLIdByUnitId(
                tsKvAttributeService.getById(tsKvAttributeId).getUnitId());
        JSONArray jsonArray = new JSONArray();
        List<List<Object>> lists = bitRepository.getAllIdAndNameAndCodeByModelPTLId(modelPTLId);
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("val", list.get(0));
            jsonObject.put("text", list.get(2) + " - " + list.get(1));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
