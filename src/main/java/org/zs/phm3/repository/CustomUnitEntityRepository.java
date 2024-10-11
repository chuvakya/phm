package org.zs.phm3.repository;

import org.zs.phm3.models.CustomUnitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUnitEntityRepository  extends CrudRepository<CustomUnitEntity, Integer> {
    }
