package com.elismarket.eshop.eshopelis.exception;

/**
 * Custom exception for class {@link com.elismarket.eshop.eshopelis.model.Proposta proposta}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public class PropostaException extends RuntimeException {

    /**
     * throws the exception with the param passed from {@link ExceptionPhrases}
     *
     * @param message with the custom exception
     */
    public PropostaException(String message) {
        super(message);
    }

}
