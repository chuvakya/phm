package org.zs.phm3.repository.maintenance.supplier;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.supplier.Supplier;

import java.util.List;

@Transactional
@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    @Modifying
    @Query(value = "DELETE FROM supplier WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long supplierId);

    @Query(value = "SELECT id, name, currency, isManufacturer, isSupplier, isTenant, notes, isManufacturerPTL " +
            "FROM Supplier " +
            "WHERE id = ?1")
    List<List<Object>> getAllById(Long supplierId);


    @Query(value = "SELECT s.id, s.is_manufacturer, s.is_supplier, s.is_tenant, s.name " +
            "FROM supplier s " +
            "WHERE project_id = ?1 ORDER BY s.name", nativeQuery = true)
    List<List<Object>> getListForTableByProjectId(Integer projectId);

    @Query(value = "SELECT s.id, s.is_manufacturer, s.is_supplier, s.is_tenant, s.name " +
            "FROM supplier s " +
            "WHERE project_id = ?1 ORDER BY s.name ASC OFFSET ?2 LIMIT ?3", nativeQuery = true)
    List<List<Object>> getListForTableByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);

    @Modifying
    @Query(value = "UPDATE supplier SET is_manufacturerptl = ?1 WHERE name IN ?2 AND is_manufacturerptl = true", nativeQuery = true)
    void updateIsManufacturerPTLByName(Boolean isManufacturerPLT, List<String> names);

    @Modifying
    @Query(value = "UPDATE supplier SET name = ?1 WHERE name = ?2 AND is_manufacturerptl = true", nativeQuery = true)
    void updateName(String newName, String oldName);

    @Query(value = "SELECT COUNT(*) FROM supplier WHERE project_id = ?1", nativeQuery = true)
    Long getCountByProjectId(Integer projectId);
}
