package org.zs.phm3.service.ptl;

import org.zs.phm3.models.ptl.SubGroupPTL;
import org.zs.phm3.repository.ptl.SubGroupPTLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementing SubGroupPTLService class
 * @author Pavel Chuvak
 */
@Service
public class SubGroupPTLServiceImpl implements SubGroupPTLService {

    /** Sub group PTL repository */
    @Autowired
    private SubGroupPTLRepository subGroupPTLRepository;

    /**
     * Getting list sub groups PTL by unit type PTL ID
     * @param unitTypeId unit type PTL ID
     * @return list sub groups PTL
     */
    @Override
    public List<SubGroupPTL> getAllByUnitTypeId(Integer unitTypeId) {
        return subGroupPTLRepository.getAllByUnitTypePTL_Id(unitTypeId);
    }
}
