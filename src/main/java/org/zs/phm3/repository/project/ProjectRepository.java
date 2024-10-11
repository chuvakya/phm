package org.zs.phm3.repository.project;

import org.zs.phm3.models.ProjectEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Transactional
public interface ProjectRepository extends CrudRepository<ProjectEntity, Integer> {
    @Query(value = "SELECT count(*)>0 FROM projects WHERE name=?1", nativeQuery = true)
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

    List<ProjectEntity> findByinActiveIsTrue();
}
