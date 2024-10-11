package org.zs.phm3.repository.maintenance.scheduled;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.zs.phm3.models.maintenance.scheduled.Trigger;

@Repository
@Transactional
public interface TriggerRepository extends CrudRepository<Trigger, Long> {
}
