package org.zs.phm3.repository.maintenance;

import org.zs.phm3.models.maintenance.RegisterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.unit.UnitEntity;

@Repository
//@Transactional

public interface RegisterRepository extends CrudRepository<RegisterEntity, Integer> {


    RegisterEntity findTopByUnitOrderByPlannedDateOperationStartDesc(UnitEntity unit);

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
