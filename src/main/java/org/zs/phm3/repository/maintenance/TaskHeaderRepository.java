package org.zs.phm3.repository.maintenance;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.maintenance.RegisterEntity;
import org.zs.phm3.models.maintenance.TaskHeader;
import org.zs.phm3.models.unit.UnitEntity;

@Repository
//@Transactional

public interface TaskHeaderRepository extends CrudRepository<TaskHeader, Integer> {

    @Query(value = "SELECT t.id, t.comment, t.date_closing, t.status, t.date_created, t.type_final_id, t.unit_id, t.user_id " +
            "FROM ms_taskheaders t " +
            "INNER JOIN " +
            "(SELECT unit_id, MAX(date_created) as last_date FROM ms_taskheaders " +
            "GROUP BY unit_id) d " +
            "ON d.unit_id = t.unit_id AND d.last_date = t.date_created " +
            "WHERE t.unit_id=?1 " +
            "ORDER BY t.unit_id", nativeQuery = true)
    TaskHeader findByUnitIdAndDateCreatedIsMax(String unitId);

    TaskHeader findByUnitId(String unitId);

//    RegisterEntity findTopByUnitOrderByPlannedDateOperationStartDesc(UnitEntity unit);

//    select id from table order by id desc limit 1
//    findFirstByOrderByCreatedTsByDesc

/*    @Query(value = "SELECT count(*)>0 FROM projects WHERE name=?1", nativeQuery = true)
    Boolean checkName(String nameChecked);

    planned_date_operation_start
            plannedDateOperationStart*/

//    public Long countByPlannedDateOperationStartAfterNowAndUnitIdEqual();

/*    @Query(value = "SELECT count(*)>0 FROM projects WHERE name=?1", nativeQuery = true)
    Boolean checkName(String nameChecked);

    @Query(value = "SELECT count(*)>0 FROM projects WHERE first_unit_id=?1", nativeQuery = true)
    Boolean isFirstProjectUnit(String unitId);

    List<ProjectEntity> findAllByinActiveIsFalse();

    @Modifying
    @Query(value =
            "UPDATE projects " +
                    "SET inactive = true " +
                    "WHERE id =?1", nativeQuery = true
    )
    void deactivateProjectsById(Integer unitId);

    List<ProjectEntity> findByinActiveIsTrue();*/
}
