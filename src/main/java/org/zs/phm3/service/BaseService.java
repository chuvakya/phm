package org.zs.phm3.service;

import org.zs.phm3.auditlog.ActionStatus;
import org.zs.phm3.auditlog.ActionType;
import org.zs.phm3.auditlog.AuditLogEntity;
import org.zs.phm3.auditlog.AuditLogService;
import org.zs.phm3.exception.DataValidationException;
import org.zs.phm3.exception.IncorrectParameterException;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public abstract class BaseService {
    @Autowired
    AuditLogService auditLogService;

    protected void logAction(String entityId, String userId, String userName, ActionType actionType,
                             ActionStatus actionStatus, String actionData, String actionErrorDetails) {
        AuditLogEntity auditLogEntity = new AuditLogEntity(entityId, userId, userName, actionType,
                actionStatus, actionData, actionErrorDetails);
        auditLogService.logAction(auditLogEntity);
    }

    protected PhmException handleException(Exception exception) {
        String cause = "";
        if (exception.getCause() != null) {
            cause = exception.getCause().getClass().getCanonicalName();
        }

        if (exception instanceof PhmException) {
            return (PhmException) exception;
        } else if (exception instanceof IllegalArgumentException || exception instanceof IncorrectParameterException
                || exception instanceof DataValidationException || cause.contains("IncorrectParameterException")) {
            return new PhmException(exception.getMessage(), PhmErrorCode.BAD_REQUEST_PARAMS);
        } else if (exception instanceof IOException) {
            return new PhmException("IO exception: " + exception.getMessage(), PhmErrorCode.GENERAL);
//        } else if (exception instanceof MessagingException) {
//            return new PhmException("Unable to send mail: " + exception.getMessage(), PhmErrorCode.GENERAL);
        } else {
            return new PhmException(exception.getMessage(), PhmErrorCode.GENERAL);
        }
    }

//        return handleException(exception, true);
//        return (PhmException) exception;

//        return (PhmException) exception;
//        throw new NotFoundException();
//        return (CustomGlobalExceptionHandler) exception;
    }

