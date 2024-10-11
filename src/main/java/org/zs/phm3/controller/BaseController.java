package org.zs.phm3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.zs.phm3.auditlog.ActionStatus;
import org.zs.phm3.auditlog.ActionType;
import org.zs.phm3.auditlog.AuditLogEntity;
import org.zs.phm3.auditlog.AuditLogService;
import org.zs.phm3.exception.PhmErrorCode;
import org.zs.phm3.exception.PhmErrorResponseHandler;
import org.zs.phm3.exception.PhmException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseController {
    @Autowired
    AuditLogService auditLogService;
    @Autowired
    private PhmErrorResponseHandler errorResponseHandler;
    @Autowired
    private ObjectMapper mapper;

    void checkParameter(String name, String param) throws PhmException {
        if (StringUtils.isEmpty(param)) {
            throw new PhmException("Parameter '" + name + "' can't be empty!", PhmErrorCode.BAD_REQUEST_PARAMS);
        }
    }

    void checkIntParameter(String name, Integer param) throws PhmException {
        if (param == null) {
            throw new PhmException("Parameter '" + name + "' can't be null!", PhmErrorCode.BAD_REQUEST_PARAMS);
        }
    }

    protected void logAction(String entityId, String userId, String userName, ActionType actionType,
                             ActionStatus actionStatus, String actionData, String actionErrorDetails) {
        AuditLogEntity auditLogEntity = new AuditLogEntity(entityId, userId, userName, actionType,
                actionStatus, actionData, actionErrorDetails);
        auditLogService.logAction(auditLogEntity);
    }

    PhmException handleException(Exception exception) {

        if (exception instanceof PhmException) {
            return (PhmException) exception;
        } else {
            return new PhmException(exception.getMessage(), PhmErrorCode.GENERAL);
        }
    }

/*    @ExceptionHandler({PhmException.class})
    public void handlePhmException(PhmException phmEx, HttpServletResponse response) throws IOException {
        PhmErrorCode errorCode = phmEx.getErrorCode();
        HttpStatus status;

        switch (errorCode) {
            case AUTHENTICATION:
                status = HttpStatus.UNAUTHORIZED;
                break;
            case PERMISSION_DENIED:
                status = HttpStatus.FORBIDDEN;
                break;
            case INVALID_ARGUMENTS:
                status = HttpStatus.BAD_REQUEST;
                break;
            case ITEM_NOT_FOUND:
                status = HttpStatus.NOT_FOUND;
                break;
            case BAD_REQUEST_PARAMS, TOO_MANY_REQUESTS:
                status = HttpStatus.BAD_REQUEST;
                break;
            case GENERAL:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }

        response.setStatus(status.value());
        mapper.writeValue(response.getWriter(), PhmErrorResponse.of(phmEx.getMessage(), errorCode, status));

    }*/

    @ExceptionHandler({Exception.class})
    public void handleException(Exception ex, HttpServletResponse response) throws IOException {

        errorResponseHandler.handle(ex, response);

    }
}


