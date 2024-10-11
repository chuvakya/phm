package org.zs.phm3.exception;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PhmErrorCode {
    GENERAL(2),
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
//    DUPLICATED_DATA(36),
    OTHER(40);
    private int errorCode;

    PhmErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public int getErrorCode() {
        return errorCode;
    }

}
