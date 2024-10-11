package org.zs.phm3.repository.maintenance.part;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.part.Part;

import java.util.List;

@Transactional
@Repository
public interface PartRepository extends CrudRepository<Part, Long> {

    @Query(value = "SELECT p.id, p.name AS partName, pc.name AS categoryName, p.total_stock, p.storage " +
            "FROM part p " +
            "JOIN part_category pc ON p.part_category_id = pc.id " +
            "WHERE p.project_id = ?1 ORDER BY p.name", nativeQuery = true)
    List<List<Object>> getAllByProjectId(Integer projectId);

    @Query(value = "SELECT p.id, p.name AS partName, pc.name AS categoryName, p.total_stock, p.storage " +
            "FROM part p " +
            "JOIN part_category pc ON p.part_category_id = pc.id " +
            "WHERE p.project_id = ?1 ORDER BY p.name ASC OFFSET ?2 LIMIT ?3", nativeQuery = true)
    List<List<Object>> getAllByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);

    @Query(value = "SELECT id, name, partCategory.id  FROM Part WHERE id = ?1")
    List<List<Object>> getIdAndNameAndCategoryIdByPartId(Long partId);

    @Query(value = "SELECT COUNT(*) FROM part WHERE project_id = ?1", nativeQuery = true)
    Long getCountByProjectId(Integer projectId);

    @Query(value = "SELECT p.id AS partId, p.name, ps.id AS partStockId, ps.storage, ps.aisle, " +
            "       ps.cell, ps.quantity, ps.min_quantity, ps.row FROM part p " +
            "JOIN part_stock ps ON p.id = ps.part_id " +
            "WHERE project_id = ?1 ORDER BY p.name ASC OFFSET ?2 LIMIT ?3", nativeQuery = true)
    List<List<Object>> getListForWorkOrderByProjectIdAndOffsetAndLimit(Integer projectId, Integer offset, Integer limit);
}
