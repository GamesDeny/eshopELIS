package com.elismarket.eshop.customExceptions;

public class OrdineException extends RuntimeException {
    public enum Phrases {
        MISSING_PARAMETERS,
        ORDER_NOT_FOUND,
        ORDER_ALREADY_EXISTS
    }

    public OrdineException() {
        super("Missing parameters");
    }

    public OrdineException(String message) {
        super(message);
    }
}
