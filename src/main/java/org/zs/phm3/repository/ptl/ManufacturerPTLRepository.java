package org.zs.phm3.repository.ptl;

import org.zs.phm3.models.ptl.ManufacturerPTL;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ManufacturerPTLRepository extends CrudRepository<ManufacturerPTL, Long> {

    @Modifying
    @Query(value = "DELETE FROM manufacturer_ptl WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Long manufacturerId);

    @Query(value = "SELECT m.id, m.create_time, m.name AS mName, ue.name AS userName " +
            "FROM manufacturer_ptl m " +
            "JOIN user_entity ue ON m.modified_by_id = ue.id " +
            "ORDER BY m.name OFFSET ?1 LIMIT ?2", nativeQuery = true)
    List<List<Object>> getAllByOffsetAndLimit(Integer offset, Integer limit);

    @Query(value = "SELECT COUNT(*) FROM manufacturer_ptl", nativeQuery = true)
    Long getCount();

    @Query(value = "SELECT name FROM manufacturer_ptl WHERE id IN ?1", nativeQuery = true)
    List<String> getAllNamesByManufacturerIds(List<Long> manufacturerIds);

    @Query(value = "SELECT COUNT(*) FROM manufacturer_ptl WHERE name = ?1", nativeQuery = true)
    Long getCountByName(String name);
}
