package com.elismarket.eshop.customExceptions;

public class RigaOrdineException extends RuntimeException {
    public RigaOrdineException() {
        super("Missing parameters");
    }

    public RigaOrdineException(String message) {
        super(message);
    }

    public enum Phrases {
        MISSING_PARAMETERS,
        ORDER_ROW_NOT_FOUND,
        ORDER_ROW_ALREADY_EXISTS
    }
}
