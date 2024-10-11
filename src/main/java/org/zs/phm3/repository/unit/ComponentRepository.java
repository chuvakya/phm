package org.zs.phm3.repository.unit;

import org.zs.phm3.models.ComponentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends CrudRepository<ComponentEntity, String> {

}
