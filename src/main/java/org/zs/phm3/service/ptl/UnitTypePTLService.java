package org.zs.phm3.service.ptl;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.ptl.UnitTypePTL;

import java.util.List;

/**
 * Interface UnitTypePTLService
 * @author Pavel Chuvak
 */
public interface UnitTypePTLService {

    /**
     * Saving unit type PTL
     * @param unitTypePTL unit type PTL
     * @return unit type PTL
     */
    UnitTypePTL save(UnitTypePTL unitTypePTL);

    /**
     * Deleting by unit type PTL ID
     * @param unitTypeId unit type PTL ID
     */
    void deleteSQL(Integer unitTypeId);

    /**
     * Getting list all unit types PTL where unit type PTL ID is null
     * @return list all unit types PTL
     */
    List<UnitTypePTL> getAllMainEntity();
    UnitTypePTL getById(Integer unitTypeId) throws PhmException;

    /**
     * Getting json string all id and name unit types PTL
     * @return json string
     */
    String getAllIdAndName();

    /**
     * Getting json string all unit types PTL
     * @return json string all unit types PTL
     */
    String getAllList();

    /**
     * Getting list ID and default type by unit type PTL IDs
     * @param unitTypePTLIds unit type PTL IDs
     * @return list ID and default type
     */
    List<List<Object>> getAllIdAndDefaultType(List<Integer> unitTypePTLIds);
}
