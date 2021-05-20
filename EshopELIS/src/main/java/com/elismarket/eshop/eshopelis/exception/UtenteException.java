package com.elismarket.eshop.eshopelis.exception;

public class UtenteException extends RuntimeException {

    public UtenteException() {
        super("Missing parameters");
    }

    public UtenteException(String message) {
        super(message);
    }

    public enum Phrases {
        MISSING_PARAMETERS,
        USER_NOT_FOUND,
        USER_ALREADY_EXISTS
    }
}
