package org.zs.phm3.service.calculation;

import org.zs.phm3.models.calculation.CalculationAttrType;
import org.zs.phm3.models.calculation.CalculationAttribute;
import org.zs.phm3.models.ml.MlAlgorithmType;
import org.zs.phm3.models.ml.MlServiceResult;
import org.zs.phm3.repository.calculation.CalculationAttrRepository;
import org.zs.phm3.repository.calculation.CalculationAttrTypeRepository;
import org.zs.phm3.repository.ml.MlServiceResultRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class implementing CalculationAttrService
 * @author Pavel CHuvak
 */
@Service
public class CalculationAttrServiceImpl implements CalculationAttrService {

    /** Calculation attribute repository */
    @Autowired
    private CalculationAttrRepository calculationAttrRepository;

    /** Calculation attribute type repository */
    @Autowired
    private CalculationAttrTypeRepository calculationAttrTypeRepository;

    /** Ml service result repository */
    @Autowired
    private MlServiceResultRepository mlServiceResultRepository;

    /**
     * Getting all calculation attribute
     * @return all calculation attribute
     */
    @Override
    public List<CalculationAttribute> getAll() {
        return (List<CalculationAttribute>) calculationAttrRepository.findAll();
    }

    /**
     * Saving calculation attribute
     * @param calculationAttribute calculation attribute
     * @return calculation attribute
     */
    @Override
    public CalculationAttribute save(CalculationAttribute calculationAttribute) {
        return calculationAttrRepository.save(calculationAttribute);
    }

    /**
     * Deliting calculation attribute by ID
     * @param id ID
     */
    @Override
    public void deleteById(Integer id) {
        calculationAttrRepository.deleteById(id);
    }

    /**
     * Getting by calculation attribute by ID
     * @param id ID
     * @return calculation attribute
     */
    @Override
    public CalculationAttribute getById(Integer id) {
        return calculationAttrRepository.findById(id).get();
    }

    /**
     * Getting json string by unit ID
     * @param unitId unit ID
     * @return json string
     */
    @Override
    public String getAllByUnit(String unitId) {
        List<CalculationAttrType> calculationAttrTypes = (List<CalculationAttrType>) calculationAttrTypeRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        for (CalculationAttrType calculationAttrType : calculationAttrTypes) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", calculationAttrType.getId());
            jsonObject.put("name", calculationAttrType.getName());
            jsonObject.put("hasChild", true);
            for (CalculationAttribute calculationAttribute: calculationAttrRepository.getAllByUnitIdAndAttrType_Id(unitId,
                    calculationAttrType.getId())) {
                JSONObject jsonAttr = new JSONObject();
                if (calculationAttrType.getName().equals("ML Services")) {
                    String attrName = MlAlgorithmType.getType(calculationAttribute.getMlModel().getMlAlgorithm()) + "." +
                            calculationAttribute.getMlModel().getName();
                    jsonAttr.put("name", attrName);
                    MlServiceResult mlServiceResult = mlServiceResultRepository.getLastServiceResultForUnitAndMlModel(
                            calculationAttribute.getMlModel().getId(), unitId);
                    if (mlServiceResult == null) {
                        jsonAttr.put("value", "");
                        jsonAttr.put("lastUpdate", "");
                    } else {
                        jsonAttr.put("value", mlServiceResult.getResult());
                        jsonAttr.put("lastUpdate", mlServiceResult.getTime());
                    }
                    jsonAttr.put("pid", calculationAttrType.getId());
                    jsonAttr.put("attrId", calculationAttribute.getId());
                    jsonArray.add(jsonAttr);
                } else if (calculationAttrType.getName().equals("Reliability Service")) {
                    jsonAttr.put("name", calculationAttribute.getName());
                    jsonAttr.put("value", calculationAttribute.getValue());
                    jsonAttr.put("lastUpdate", calculationAttribute.getTime());
                    jsonAttr.put("pid", calculationAttrType.getId());
                    jsonArray.add(jsonAttr);
                }
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
