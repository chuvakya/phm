package org.zs.phm3.repository.unit;

import org.zs.phm3.models.unit.UnitType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UnitTypeRepository extends CrudRepository<UnitType, Integer> {
}
