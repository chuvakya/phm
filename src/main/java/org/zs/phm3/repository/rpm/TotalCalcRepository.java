package org.zs.phm3.repository.rpm;

import org.zs.phm3.models.rpm.CalcId;
import org.zs.phm3.models.rpm.TotalCalc;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TotalCalcRepository extends CrudRepository<TotalCalc, CalcId> {

    @Query(value = "SELECT * FROM pt_total_calc WHERE unit_id = ?1", nativeQuery = true)
    List<TotalCalc> findAllByUnitId(String uniId);

    List<TotalCalc> findAllByCalcId(String uniId);
}

