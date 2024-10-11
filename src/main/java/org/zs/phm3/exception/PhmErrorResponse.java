
package org.zs.phm3.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class PhmErrorResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Error code
    private final PhmErrorCode errorCode;

    private final Date timestamp;

    protected PhmErrorResponse(final String message, final PhmErrorCode errorCode, HttpStatus status) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = new Date();
    }

    public static PhmErrorResponse of(final String message, final PhmErrorCode errorCode, HttpStatus status) {
        return new PhmErrorResponse(message, errorCode, status);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public PhmErrorCode getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
