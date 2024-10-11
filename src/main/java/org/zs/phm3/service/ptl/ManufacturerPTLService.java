package org.zs.phm3.service.ptl;

import org.zs.phm3.models.ptl.ManufacturerPTL;

import java.util.List;

/**
 * Interface ManufacturerPTLService
 * @author Pavel Chuvak
 */
public interface ManufacturerPTLService {

    /**
     * Deleting by manufacturer ID
     * @param manufacturerId manufacturer ID
     */
    void deleteByIdSQL(Long manufacturerId);

    /**
     * Saving manufacturer PTL
     * @param manufacturerPTL manufacturer PTL
     * @return manufacturer PTL
     */
    ManufacturerPTL save(ManufacturerPTL manufacturerPTL);

    /**
     * Getting list manufacturers PTL
     * @return list manufacturers PTL
     */
    List<ManufacturerPTL> getAll();

    /**
     * Getting manufacturer PTL by manufacturer PTL ID
     * @param manufacturerId manufacturer PTL ID
     * @return
     */
    ManufacturerPTL getById(Long manufacturerId);

    /**
     * Getting json string by offset and limit
     * @param offset offset
     * @param limit limit
     * @return json string
     */
    String getAllByOffsetAndLimit(Integer offset, Integer limit);

    /**
     * Getting count manufacturers PTL
     * @return count manufacturers PTL
     */
    Long getCount();

    /**
     * Getting list names by manufacturer IDs
     * @param manufacturerIds manufacturer IDs
     * @return list names
     */
    List<String> getAllNamesByManufacturerIds(List<Long> manufacturerIds);

    /**
     * Getting count manufacturers PTL by manufacturer PTL name
     * @param name manufacturer PTL name
     * @return count manufacturers PTL
     */
    Long getCountByName(String name);
}
