package org.zs.phm3.service.ptl;

import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.ptl.UnitTypePTL;
import org.zs.phm3.models.unit.UnitType;
import org.zs.phm3.repository.ptl.UnitTypePTLRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zs.phm3.repository.unit.UnitRepository;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementing UnitTypePTLService class
 * @author Pavel Chuvak
 */
@Service
public class UnitTypePTLServiceImpl implements UnitTypePTLService {

    /** Unit type PTL repository */
    @Autowired
    private UnitTypePTLRepository unitTypePTLRepository;

    /**
     * Saving unit type PTL
     * @param unitTypePTL unit type PTL
     * @return unit type PTL
     */
    @Override
    public UnitTypePTL save(UnitTypePTL unitTypePTL) {
        return unitTypePTLRepository.save(unitTypePTL);
    }

    /**
     * Deleting by unit type PTL ID
     * @param unitTypeId unit type PTL ID
     */
    @Override
    public void deleteSQL(Integer unitTypeId) {
        unitTypePTLRepository.deleteSQL(unitTypeId);
    }

    /**
     * Getting list all unit types PTL where unit type PTL ID is null
     * @return list all unit types PTL
     */
    @Override
    public List<UnitTypePTL> getAllMainEntity() {
        return unitTypePTLRepository.getAllByUnitTypePTL_IdIsNull();
    }

    @Override
    public UnitTypePTL getById(Integer unitTypeId) throws PhmException {
        return unitTypePTLRepository.findById(unitTypeId).orElseThrow(() ->
                new PhmException("No unitType found with id=" + unitTypeId, PhmErrorCode.BAD_REQUEST_PARAMS));
    }

    /**
     * Getting json string all id and name unit types PTL
     * @return json string
     */
    @Override
    public String getAllIdAndName() {
        JSONArray jsonArray = new JSONArray();
        List<List<Object>> lists = unitTypePTLRepository.getAllIdAndName();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", list.get(0));
            jsonObject.put("name", list.get(1));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting json string all unit types PTL
     * @return json string all unit types PTL
     */
    @Override
    public String getAllList() {
        List<List<Object>> allTypes = unitTypePTLRepository.getAllField();
        JSONArray jsonArray = new JSONArray();
        for (List<Object> unitTypePTL : allTypes) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", unitTypePTL.get(0));
            jsonObject.put("name", unitTypePTL.get(1));
            jsonObject.put("pictureId", unitTypePTL.get(2));
            jsonObject.put("isDefaultType", unitTypePTL.get(3));
            jsonObject.put("modifiedBy", unitTypePTL.get(4));
            jsonObject.put("modifiedTime", unitTypePTL.get(5));
            if (unitTypePTL.get(6) != null) {
                jsonObject.put("pid", unitTypePTL.get(6));
            }
            if (unitTypePTLRepository.getCountByParentId((Integer) unitTypePTL.get(0)) > 0) {
                jsonObject.put("hasChild", true);
            }
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting list ID and default type by unit type PTL IDs
     * @param unitTypePTLIds unit type PTL IDs
     * @return list ID and default type
     */
    @Override
    public List<List<Object>> getAllIdAndDefaultType(List<Integer> unitTypePTLIds) {
        return unitTypePTLRepository.getAllIdAndDefaultType(unitTypePTLIds);
    }


}
