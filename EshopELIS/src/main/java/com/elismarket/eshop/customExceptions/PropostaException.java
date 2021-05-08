package com.elismarket.eshop.customExceptions;

public class PropostaException extends RuntimeException {

    public PropostaException() {
        super("Missing parameters");
    }

    public PropostaException(String message) {
        super(message);
    }

    public enum Phrases {
        MISSING_PARAMETERS,
        PROPOSTA_ALREADY_EXISTS
    }
}
