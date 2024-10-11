package org.zs.phm3.repository.ts;

import org.zs.phm3.models.ts.TsKvAttributeType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TsKvAttributeTypeRepository extends CrudRepository<TsKvAttributeType, Integer> {

    @Query(value = "FROM TsKvAttributeType ts WHERE (ts.projectId = ?1 AND ts.name = ?2) " +
            "OR (ts.name = ?2 AND ts.projectId IS NULL)")
    List<TsKvAttributeType> getAllByProjectIdAndName(Integer projectId, String name);

    @Query(value = "SELECT * FROM ts_kv_attribute_type WHERE project_id = ?1 OR project_id IS NULL", nativeQuery = true)
    List<TsKvAttributeType> getAllByProjectId(Integer projectId);

    @Query(value = "FROM TsKvAttributeType t WHERE t.projectId = ?1")
    List<TsKvAttributeType> getAllUsersTypesByProjectId(Integer projectId);

    @Query(value = "SELECT DISTINCT ts.tsKvAttributeType FROM TsKvAttribute ts WHERE ts.unitId = ?1")
    List<TsKvAttributeType> getAllAttributeTypeByUnitId(String unitId);

}
