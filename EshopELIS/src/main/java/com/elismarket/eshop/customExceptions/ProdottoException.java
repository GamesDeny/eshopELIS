package com.elismarket.eshop.customExceptions;

public class ProdottoException extends RuntimeException {
    public enum Phrases {
        MISSING_PARAMETERS,
        PRODUCT_NOT_FOUND,
        PRODUCT_ALREADY_EXISTS
    }

    public ProdottoException() {
        super("Missing parameters");
    }

    public ProdottoException(String message) {
        super(message);
    }
}
