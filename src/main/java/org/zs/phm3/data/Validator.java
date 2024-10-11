package org.zs.phm3.data;

import org.zs.phm3.exception.IncorrectParameterException;

public class Validator {
    public static <T extends Number> void validateId(T id, String errorMessage) {
//        if (id == null || id.isEmpty()) {
        if (id == null) {
            throw new IncorrectParameterException(errorMessage);
        }
    }
}
