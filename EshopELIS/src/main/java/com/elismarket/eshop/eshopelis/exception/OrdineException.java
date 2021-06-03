package com.elismarket.eshop.eshopelis.exception;

/**
 * Custom exception for class {@link com.elismarket.eshop.eshopelis.model.Ordine Ordine}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public class OrdineException extends RuntimeException {

    /**
     * throws the exception with the param passed from {@link ExceptionPhrases}
     *
     * @param message with the custom exception
     */
    public OrdineException(String message) {
        super(message);
    }

}
