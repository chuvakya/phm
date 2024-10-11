package org.zs.phm3.service.maintenance.supplier;

import org.zs.phm3.models.maintenance.supplier.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier save(Supplier supplier);
    void deleteByIdSQL(Long supplierId);
    Supplier getById(Long supplierId);
    String getByIdJSON(Long supplierId);
    String getListForTableByProjectId(Integer projectId);
    String getListForTableByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);
    void updateIsManufacturerPTLByName(Boolean isManufacturerPLT, List<String> names);
    void updateName(String newName, String oldName);
    Long getCountByProjectId(Integer projectId);
}
