package org.zs.phm3.service.ptl;

import org.zs.phm3.models.ptl.SubGroupPTL;

import java.util.List;

/**
 * Interface SubGroupPTLService
 * @author Pavel Chuvak
 */
public interface SubGroupPTLService {

    /**
     * Getting list sub groups PTL by unit type PTL ID
     * @param unitTypeId unit type PTL ID
     * @return list sub groups PTL
     */
    List<SubGroupPTL> getAllByUnitTypeId(Integer unitTypeId);

}
