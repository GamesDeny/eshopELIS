package com.elismarket.eshop.customExceptions;

public class UtenteException extends RuntimeException {

    public enum Phrases {
        MISSING_PARAMETERS,
        USER_NOT_FOUND,
        USER_ALREADY_EXISTS
    }

    public UtenteException() {
        super("Missing parameters");
    }

    public UtenteException(String message) {
        super(message);
    }
}
