package com.elismarket.eshop.customExceptions;

public class ProdottoException extends RuntimeException {
    public ProdottoException() {
        super("Missing parameters");
    }

    public ProdottoException(String message) {
        super(message);
    }

    public enum Phrases {
        MISSING_PARAMETERS,
        PRODUCT_NOT_FOUND,
        PRODUCT_ALREADY_EXISTS
    }
}
