package org.zs.phm3.exception;

public class PhmException extends Exception {
    private static final long serialVersionUID = 1L;

    private PhmErrorCode errorCode;

    public PhmException() {
        super();
    }

    public PhmException(PhmErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public PhmException(String message, PhmErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public PhmException(String message, Throwable cause, PhmErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public PhmException(Throwable cause, PhmErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public PhmErrorCode getErrorCode() {
        return errorCode;
    }
}

