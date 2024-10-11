package org.zs.phm3.auditlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditLogServiceImpl implements AuditLogService {

    @Autowired
    AuditLogRepository auditLogRepository;

    @Override
    public AuditLogEntity logAction(AuditLogEntity auditLogEntity) {
        return auditLogRepository.save(auditLogEntity);
    }

    @Override
    public Iterable<AuditLogEntity> findByEntityId(String entityId) {
        return auditLogRepository.findByEntityId(entityId);
    }
}

