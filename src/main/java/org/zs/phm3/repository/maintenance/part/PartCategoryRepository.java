package org.zs.phm3.repository.maintenance.part;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.part.PartCategory;

import java.util.List;

@Transactional
@Repository
public interface PartCategoryRepository extends CrudRepository<PartCategory, Integer> {

    @Query(value = "SELECT id, name FROM PartCategory WHERE projectId = ?1")
    List<List<Object>> getIdAndNameByProjectId(Integer projectId);

    @Modifying
    @Query(value = "DELETE FROM part_category WHERE id = ?1", nativeQuery = true)
    void deleteByIdSQL(Integer partCategoryId);

    @Query(value = "SELECT COUNT(*) FROM part_category WHERE name = ?1", nativeQuery = true)
    Long getCountByName(String name);

}
