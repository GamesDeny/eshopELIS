package com.elismarket.eshop.eshopelis.exception;

public class PagamentoException extends RuntimeException {
    public PagamentoException() {
        super("Missing parameters");
    }

    public PagamentoException(String message) {
        super(message);
    }

    public enum Phrases {
        MISSING_PARAMETERS,
        PAYMENT_NOT_FOUND,
        PAYMENT_ALREADY_EXISTS,
        PAYMENT_GONE_WRONG
    }

}
