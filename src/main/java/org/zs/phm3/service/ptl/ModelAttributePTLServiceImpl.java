package org.zs.phm3.service.ptl;

import org.zs.phm3.models.ptl.ModelAttrTypePTL;
import org.zs.phm3.models.ptl.ModelAttributePTL;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.repository.ptl.ModelAttributePTLRepository;

import java.util.List;

/**
 * Implementing ModelAttributePTLService class
 * @author Pavel Chuvak
 */
@Service
public class ModelAttributePTLServiceImpl implements ModelAttributePTLService {

    /** Model attribute PTL repository */
    @Autowired
    private ModelAttributePTLRepository modelAttributePTLRepository;

    /**
     * Saving model attribute PTL
     * @param modelAttributePTL model attribute PTL
     * @return model attribute PTL
     */
    @Override
    public ModelAttributePTL save(ModelAttributePTL modelAttributePTL) {
        return modelAttributePTLRepository.save(modelAttributePTL);
    }

    /**
     * Deleting model attribute PTL by model attribute ID
     * @param modelAttributeId model attribute ID
     */
    @Override
    public void deleteByIdSQL(Long modelAttributeId) {
        modelAttributePTLRepository.deleteByIdSQL(modelAttributeId);
    }

    /**
     * Getting list all model attributes PTL
     * @return list all model attributes PTL
     */
    @Override
    public List<ModelAttributePTL> getAll() {
        return (List<ModelAttributePTL>) modelAttributePTLRepository.findAll();
    }

    /**
     * Getting model attribute PTL by model attribute PTL ID
     * @param modelAttributeId model attribute PTL ID
     * @return model attribute PTL
     */
    @Override
    public ModelAttributePTL getById(Long modelAttributeId) {
        return modelAttributePTLRepository.findById(modelAttributeId).get();
    }

    /**
     * Getting json string by model PTL ID
     * @param modelId model PTL ID
     * @return json string
     */
    @Override
    public String getAllByModelId(Long modelId) {
        List<ModelAttrTypePTL> types = modelAttributePTLRepository.getAllAttrTypeNamesByModelId(modelId);
        JSONArray jsonArray = new JSONArray();
        for (ModelAttrTypePTL type : types) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", type.getName());
            jsonObject.put("id", type.getId());
            jsonObject.put("hasChild", true);
            jsonArray.add(jsonObject);

            for (List<Object> modelAttributePTL :
                    modelAttributePTLRepository.getAllByModelPTL_IdAndModelAttrTypePTL_Id(modelId, type.getId())) {
                JSONObject object = new JSONObject();
                object.put("name", modelAttributePTL.get(2));
                object.put("id", modelAttributePTL.get(0));
                if (modelAttributePTL.get(5) == null) {
                    object.put("value", modelAttributePTL.get(3));
                } else {
                    object.put("value", modelAttributePTL.get(3) + " " + modelAttributePTL.get(5));
                }
                object.put("modifiedBy", modelAttributePTL.get(4));
                object.put("modifiedTime", modelAttributePTL.get(1));
                object.put("pid", type.getId());
                jsonArray.add(object);
            }
        }
        return jsonArray.toJSONString();
    }

}
