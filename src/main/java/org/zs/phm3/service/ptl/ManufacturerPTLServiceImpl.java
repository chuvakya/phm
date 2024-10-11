package org.zs.phm3.service.ptl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.zs.phm3.models.ptl.ManufacturerPTL;
import org.zs.phm3.repository.ptl.ManufacturerPTLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementing ManufacturerPTLService class
 * @author Pavel Chuvak
 */
@Service
public class ManufacturerPTLServiceImpl implements ManufacturerPTLService {

    /** Manufacturer PTL repository */
    @Autowired
    private ManufacturerPTLRepository manufacturerPTLRepository;

    /**
     * Deleting by manufacturer ID
     * @param manufacturerId manufacturer ID
     */
    @Override
    public void deleteByIdSQL(Long manufacturerId) {
        manufacturerPTLRepository.deleteByIdSQL(manufacturerId);
    }

    /**
     * Saving manufacturer PTL
     * @param manufacturerPTL manufacturer PTL
     * @return manufacturer PTL
     */
    @Override
    public ManufacturerPTL save(ManufacturerPTL manufacturerPTL) {
        return manufacturerPTLRepository.save(manufacturerPTL);
    }

    /**
     * Getting list manufacturers PTL
     * @return list manufacturers PTL
     */
    @Override
    public List<ManufacturerPTL> getAll() {
        return (List<ManufacturerPTL>) manufacturerPTLRepository.findAll();
    }

    /**
     * Getting manufacturer PTL by manufacturer PTL ID
     * @param manufacturerId manufacturer PTL ID
     * @return
     */
    @Override
    public ManufacturerPTL getById(Long manufacturerId) {
        return manufacturerPTLRepository.findById(manufacturerId).get();
    }

    /**
     * Getting json string by offset and limit
     * @param offset offset
     * @param limit limit
     * @return json string
     */
    @Override
    public String getAllByOffsetAndLimit(Integer offset, Integer limit) {
        List<List<Object>> lists = manufacturerPTLRepository.getAllByOffsetAndLimit(offset, limit);
        JSONArray jsonArray = new JSONArray();
        for (List<Object> list : lists) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", list.get(0));
            jsonObject.put("createTime", list.get(1));
            jsonObject.put("name", list.get(2));
            jsonObject.put("modifiedBy", list.get(3));
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    /**
     * Getting count manufacturers PTL
     * @return count manufacturers PTL
     */
    @Override
    public Long getCount() {
        return manufacturerPTLRepository.getCount();
    }

    /**
     * Getting list names by manufacturer IDs
     * @param manufacturerIds manufacturer IDs
     * @return list names
     */
    @Override
    public List<String> getAllNamesByManufacturerIds(List<Long> manufacturerIds) {
        return manufacturerPTLRepository.getAllNamesByManufacturerIds(manufacturerIds);
    }

    /**
     * Getting count manufacturers PTL by manufacturer PTL name
     * @param name manufacturer PTL name
     * @return count manufacturers PTL
     */
    @Override
    public Long getCountByName(String name) {
        return manufacturerPTLRepository.getCountByName(name);
    }
}
