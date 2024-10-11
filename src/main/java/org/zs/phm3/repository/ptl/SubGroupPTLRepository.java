package org.zs.phm3.repository.ptl;

import org.zs.phm3.models.ptl.SubGroupPTL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SubGroupPTLRepository extends CrudRepository<SubGroupPTL, Integer> {

    List<SubGroupPTL> getAllByUnitTypePTL_Id(Integer unitTypeId);
}
