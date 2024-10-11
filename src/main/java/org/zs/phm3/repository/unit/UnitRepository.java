package org.zs.phm3.repository.unit;

import org.zs.phm3.models.unit.UnitEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UnitRepository extends CrudRepository<UnitEntity, String> {

    List<UnitEntity> findAllByProjectId(Integer projectId);

    // new short data for select 16.07.2020
    @Query(value = "SELECT id, name, description FROM UNITS WHERE PROJECT_ID=?1", nativeQuery = true)
    List<String[]> findByProjectShortSQL(Integer projectId);

/*    // new short data for select 16.01.2021
    @Query(value = "SELECT u.id, u.name, u.description, CAST(t.picture_id AS varchar(10)) FROM units u " +
            "LEFT JOIN model_ptl m ON u.model=m.id " +
            "LEFT JOIN unit_type_ptl t ON t.id=m.unit_type_ptl_id " +
            "WHERE project_id = ?1", nativeQuery = true)
    List<String[]> findByProjectShortSQL(Integer projectId);*/

    Optional<UnitEntity> findByProjectAndId(Integer projectId, String id);

    @Query(value = "SELECT * FROM UNITS WHERE PROJECT_ID=?1 AND ID=?2", nativeQuery = true)
    Optional<UnitEntity> findByProjectAndIdSQL(Integer projectId, String id);

    @Modifying
    @Query(value = "DELETE FROM UNITS WHERE ID=?1", nativeQuery = true)
    void deleteByIdSQL(String id);

    @Query(value = "SELECT count(*)>0 FROM units WHERE id=?1", nativeQuery = true)
    Boolean isPresent(String unitId);

    @Query(value = "SELECT id FROM Units WHERE parent_id is null and project_id=?1", nativeQuery = true)
    UnitEntity getRootUnitForProject(Integer projectId);

    @Query(value = "SELECT id FROM units WHERE for_project is TRUE and project_id=?1", nativeQuery = true)
    String getServiceUnitForProject(Integer projectId);

    @Query(value = "SELECT id FROM units", nativeQuery = true)
    List<String> getUnitIds();

    @Query(value = "SELECT id, parent_id FROM units", nativeQuery = true)
    List<String[]> getUnitIdParentIds();

    @Modifying
    @Query(value = "update UNITS set PARENT_ID =:parentId where ID = :unitId",
            nativeQuery = true)
    void assignParent(@Param("unitId") String unitId, @Param("parentId") String parentId);

    @Query(value = "SELECT id FROM units WHERE parent_id = ?1", nativeQuery = true)
    List<String> findChilds(String unitId);

    @Query("SELECT modelPtl.id FROM UnitEntity WHERE id = ?1")
    Long getModelPTLIdByUnitId(String unitId);

    @Query(value = "SELECT id FROM UnitEntity WHERE parent.id = ?1")
    List<String> getAllIdByParentId(String parentId);

    @Query(value = "SELECT u.id, u.name, u.parent_id, t.picture_id FROM units u " +
            "LEFT JOIN model_ptl m ON u.model=m.id " +
            "LEFT JOIN unit_type_ptl t ON t.id=m.unit_type_ptl_id " +
            "WHERE project_id = ?1", nativeQuery = true)
    List<List<Object>> getAllUnitIdsAndNameAndParentIdSQL(Integer projectId);

    @Query(value = "SELECT id, parent.id FROM UnitEntity")
    List<List<Object>> getAllUnitIdAndParentId();

    @Query(value = "SELECT COUNT(*) FROM units WHERE parent_id = ?1", nativeQuery = true)
    Long getCountChildByParentId(String parentId);

}