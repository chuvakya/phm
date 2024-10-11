package org.zs.phm3.service.unit;

import org.zs.phm3.dto.ptl.PtlData;
import org.zs.phm3.models.ptl.ModelPTL;

public interface UnitPtlService {
    PtlData getPtlData(String unitId);
}
