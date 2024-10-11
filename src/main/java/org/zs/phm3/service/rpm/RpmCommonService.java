package org.zs.phm3.service.rpm;

import org.zs.phm3.exception.PhmException;
import org.zs.phm3.models.rpm.ProductCalc;
import org.zs.phm3.models.rpm.RpmDtoIntput;
import org.zs.phm3.models.rpm.RpmDtoOutput;
import org.zs.phm3.models.rpm.TotalCalc;

import java.util.List;

public interface RpmCommonService {

    RpmDtoOutput getData(String unitId);
    void saveDataAll(List<ProductCalc> productCalcList);
    RpmDtoOutput saveData(RpmDtoIntput rpmDtoIntput) throws PhmException;
    List<TotalCalc> saveTotalCalcManually(TotalCalc totalCalcData);
    List<String []> getAllUnitChildsPtlData(String parentId);
    void testFeign();
}
