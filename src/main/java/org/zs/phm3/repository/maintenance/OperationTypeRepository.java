package org.zs.phm3.repository.maintenance;

import org.zs.phm3.models.maintenance.OperationTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Transactional

public interface OperationTypeRepository extends CrudRepository<OperationTypeEntity, Integer> {
}
