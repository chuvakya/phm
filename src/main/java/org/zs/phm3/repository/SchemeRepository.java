package org.zs.phm3.repository;

import org.zs.phm3.models.SchemeEntity;
import org.zs.phm3.models.SchemeEntityId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface SchemeRepository extends CrudRepository<SchemeEntity, SchemeEntityId> {

    @Query(value = "SELECT * FROM schemes "+
            "WHERE unit_id=?1", nativeQuery = true)
    List<SchemeEntity> getAllByUnitId(String unitId);

    @Query(value = "SELECT * FROM schemes "+
            "WHERE project_id=?1", nativeQuery = true)
    List<SchemeEntity> getAllByProjectId(Integer projectId);

    @Query(value = "SELECT body FROM schemes WHERE unit_id IN "+
            "(SELECT id FROM Units WHERE parent_id is null and project_id=?1)",
            nativeQuery = true)
    List<String> getSchemeForProject(Integer projectId);

    @Modifying
    @Query(value = "DELETE FROM schemes WHERE unit_id=?1", nativeQuery = true)
    void deleteByUnit(String unitId);

}
