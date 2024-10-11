package org.zs.phm3.auditlog;

public interface AuditLogService {
    AuditLogEntity logAction(AuditLogEntity auditLogEntity);
    Iterable <AuditLogEntity> findByEntityId(String entityId);
}
