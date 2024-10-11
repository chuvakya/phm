package org.zs.phm3.service.maintenance.part;


import org.zs.phm3.models.maintenance.part.PartStock;

import java.util.List;

public interface PartStockService {

    PartStock save(PartStock partStock);
    void deleteAllByPartId(Long partId);

}
