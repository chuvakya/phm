package org.zs.phm3.exception;

import java.io.Serializable;

public class InvalidInputException extends Exception implements Serializable {
    private static final long serialVersionUID= 2005308554412710739L;

    public InvalidInputException() {
        super("Invalid Input");
    }
    public InvalidInputException(String message) {
        super(message);
    }
}
