package org.zs.phm3.auditlog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zs.phm3.auditlog.AuditLogEntity;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AuditLogRepository extends CrudRepository<AuditLogEntity, Integer> {
    Iterable<AuditLogEntity> findByEntityId(String entityId);
}

