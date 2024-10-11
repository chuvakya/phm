package org.zs.phm3.service.maintenance.part;

import org.zs.phm3.models.maintenance.part.Part;
import org.zs.phm3.models.maintenance.part.PartStock;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PartService {

    Part save(Part part);
    void deleteByIdSQL(Long partId);
    Part getById(Long partId);
    String getListByProjectId(Integer projectId);
    String getListByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);
    String getByIdJSON(Long partId);
    void addPartStocksForPart(List<PartStock> partStocks, Long partId);
    Map getTotalStockAndStorages(List<PartStock> partStocks);
    Long getCountByProjectId(Integer projectId);
    String getListForWorkOrderByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);
}
