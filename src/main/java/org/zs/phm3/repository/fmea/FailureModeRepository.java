package org.zs.phm3.repository.fmea;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.models.fmea.FailureMode;

import java.util.Set;

@Repository

public interface FailureModeRepository extends CrudRepository<FailureMode, Integer> {
    Iterable<FailureMode> findAllByNameContains(String searchedString);

    //    Iterable<FailureMode> findAllByNameIsLike
    Iterable<FailureMode> findByCategoryTypes_Id(Integer categoryTypesIdSet);
}
