package org.zs.phm3.service.unit;

import org.zs.phm3.exception.PhmException;
import org.springframework.stereotype.Service;

@Service
public interface ComponentService {
    void multiplyComponent(String componentId, String parentUnitId, Integer number) throws PhmException;
    void multiplyUnit(String unitId, String parentUnitId, Integer number) throws PhmException;

}
