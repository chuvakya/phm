
package org.zs.phm3.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PhmErrorResponseHandler {
    @Autowired
    private ObjectMapper mapper;

    public void handlePhmException(PhmException phmException, HttpServletResponse response) throws IOException {

        PhmErrorCode errorCode = phmException.getErrorCode();
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
            case BAD_REQUEST_PARAMS:
                status = HttpStatus.BAD_REQUEST;
                break;
            case GENERAL:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            case CONNECTION_ERROR:
                status = HttpStatus.SERVICE_UNAVAILABLE;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
        }

/*        GENERAL(2),
                AUTHENTICATION(10),
                JWT_TOKEN_EXPIRED(11),
                CREDENTIALS_EXPIRED(15),
                PERMISSION_DENIED(20),
                INVALID_ARGUMENTS(30),
                BAD_REQUEST_PARAMS(31),
                ITEM_NOT_FOUND(32),
                TOO_MANY_REQUESTS(33),
                TOO_MANY_UPDATES(34),
                CONNECTION_ERROR(35),//S.Zeniuk
                DUPLICATED_DATA(36),
                OTHER(40);*/

/*
        NO_CONTENT(204, "No Content"),
        NOT_ACCEPTABLE(406, "Not Acceptable"),
        METHOD_FAILURE
        DESTINATION_LOCKED(421, "Destination Locked"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    LOCKED(423, "Locked"),
    FAILED_DEPENDENCY(424, "Failed Dependency"),
    TOO_EARLY(425, "Too Early"),
        */
        response.setStatus(status.value());
        mapper.writeValue(response.getWriter(), PhmErrorResponse.of(phmException.getMessage(), errorCode, status));
    }

    public void handle(Exception exception, HttpServletResponse response) {
//        log.debug("Processing exception {}", exception.getMessage(), exception);
        if (!response.isCommitted()) {
            try {
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                if (exception instanceof PhmException) {
                    handlePhmException((PhmException) exception, response);
//                } else if (exception instanceof TbRateLimitsException) {
//                    handleRateLimitException(response, (TbRateLimitsException) exception);
//                } else if (exception instanceof AccessDeniedException) {
//                    handleAccessDeniedException(response);
//                } else if (exception instanceof AuthenticationException) {
//                    handleAuthenticationException((AuthenticationException) exception, response);
                } else {
                    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    mapper.writeValue(response.getWriter(), PhmErrorResponse.of(exception.getMessage(),
                            PhmErrorCode.GENERAL, HttpStatus.INTERNAL_SERVER_ERROR));
                }
            } catch (IOException e) {
//                log.error("Can't handle exception", e);
            }
        }
    }
}
