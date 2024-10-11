package org.zs.phm3.service.ptl;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.ptl.ManufacturerPTL;
import org.zs.phm3.models.ptl.ModelPTL;
import org.zs.phm3.repository.ptl.ModelPTLRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.service.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementing ModelPTLService class
 * @author Pavel Chuvak
 */
@Service
public class ModelPTLServiceImpl implements ModelPTLService {

    /** Model PTL repository */
    @Autowired
    private ModelPTLRepository modelPTLRepository;

    /** User service */
    @Autowired
    private UserService userService;

    /** Unit type PTL service */
    @Autowired
    private UnitTypePTLService unitTypePTLService;

    /**
     * Saving model PTL
     * @param modelPTL model PTL
     * @return model PTL
     */
    @Override
    public ModelPTL save(ModelPTL modelPTL) {
        return modelPTLRepository.save(modelPTL);
    }

    /**
     * Deleting model PTL by model PTL ID
     * @param modelId model PTL ID
     */
    @Override
    public void deleteByIdSQL(Long modelId) {
        modelPTLRepository.deleteByIdSQL(modelId);
    }

    /**
     * Getting model PTL by model PTL ID
     * @param modelId model PTL ID
     * @return model PTL
     */
    @Override
    public ModelPTL getById(Long modelId) {
        return modelPTLRepository.findById(modelId).get();
    }

    /**
     * Getting json string by unit type PTL ID
     * @param unitTypeId unit type PTL ID
     * @return json string
     */
    @Override
    public String getAllManufacturersAndModelsByUnitTypeId(Integer unitTypeId) {
        List<ManufacturerPTL> manufacturerPTLS = modelPTLRepository.getAllManufacturerByUnitTypeId(unitTypeId);
        JSONArray jsonArray = new JSONArray();
        for (ManufacturerPTL manufacturerPTL : manufacturerPTLS) {
            JSONObject object = new JSONObject();
            object.put("manufacturerName", manufacturerPTL.getName());
            JSONArray array = new JSONArray();
            for (ModelPTL modelPTL : modelPTLRepository.getAllByManufacturerPTL_IdAndUnitTypePTL_Id(
                    manufacturerPTL.getId(), unitTypeId)) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("modelName", modelPTL.getName());
                jsonObject.put("modelId", modelPTL.getId());
                array.add(jsonObject);
            }
            object.put("models", array);
            jsonArray.add(object);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting json string by offset and limit
     * @param offset offset
     * @param limit limit
     * @return json string
     */
    @Override
    public String getListByOffsetAndLimit(Integer offset, Integer limit) {
        List<List<Object>> lists = modelPTLRepository.getListByOffsetAndLimit(offset, limit);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("modelId", list.get(0));
            jsonObject.put("modelName", list.get(1));
            jsonObject.put("manufacturerName", list.get(2));
            jsonObject.put("unitTypeName", list.get(3));
            jsonObject.put("modifiedBy", list.get(4));
            jsonObject.put("createTime", list.get(5));
            jsonObject.put("revision", list.get(6));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting list models PTL by manufacturer PTL ID and unit type PTL ID
     * @param manufacturerId manufacturer PTL
     * @param unitTypeId unit type PTL ID
     * @return list models PTL
     */
    @Override
    public List<ModelPTL> getAllByManufacturerIdAndUnitTypeId(Long manufacturerId, Integer unitTypeId) {
        return modelPTLRepository.getAllByManufacturerPTL_IdAndUnitTypePTL_Id(manufacturerId, unitTypeId);
    }

    /**
     * Getting json string by manufacturer PTL ID and unit type PTL ID
     * @param manufacturerId manufacturer PTL ID
     * @param unitTypeId unit type PTL ID
     * @return json string
     */
    @Override
    public String getAllIdAndNameByManufacturerIdAndUnitTypeId(Long manufacturerId, Integer unitTypeId) {
        List<List<Object>> lists = modelPTLRepository.getAllIdAndNameByManufacturerIdAndUnitTypeId(manufacturerId,
                unitTypeId);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("modelId", list.get(0));
            jsonObject.put("modeName", list.get(1));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting list manufacturers PTL by unit type PTL ID
     * @param unitTypeId unit type PTL ID
     * @return list manufacturers PTL
     */
    @Override
    public List<ManufacturerPTL> getAllManufacturersByUnitTypeId(Integer unitTypeId) {
        return modelPTLRepository.getAllManufacturersByUnitTypeId(unitTypeId);
    }

    @Override
    public ModelPTL getEmptyModelForUnitType(Integer unitTypeId) throws PhmException {
        ModelPTL emptyModel = modelPTLRepository.getEmptyModelForUnitType(unitTypeId);
        if (emptyModel == null) {
            ModelPTL newEmptyModel = modelPTLRepository.save(new ModelPTL("empty model", null,
                    unitTypePTLService.getById(unitTypeId), userService.getByLogin("Admin"), null, null));
            return newEmptyModel;
        }else{
            return emptyModel;
        }
    }

    /**
     * Getting count models PTL
     * @return count models PTL
     */
    @Override
    public Long getCount() {
        return modelPTLRepository.getCount();
    }

    /**
     * Getting json string by model PTL name
     * @param modelName model PTL name
     * @return json string
     */
    @Override
    public String getAllIdAndRevisionByModelName(String modelName) {
        List<List<Object>> lists = modelPTLRepository.getAllIdAndRevisionByModelName(modelName);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("val", list.get(0));
            jsonObject.put("text", list.get(1));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
