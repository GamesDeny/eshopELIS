package com.elismarket.eshop.eshopelis.exception;

public class FeedbackException extends RuntimeException {
    public FeedbackException() {
        super("Missing parameters");
    }

    public FeedbackException(String message) {
        super(message);
    }

    public enum Phrases {
        MISSING_PARAMETERS,
        PAYMENT_NOT_FOUND,
        PAYMENT_ALREADY_EXISTS,
        PAYMENT_GONE_WRONG
    }
}
