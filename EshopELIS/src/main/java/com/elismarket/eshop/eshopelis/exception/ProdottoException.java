package com.elismarket.eshop.eshopelis.exception;

/**
 * Custom exception for class {@link com.elismarket.eshop.eshopelis.model.Prodotto Prodotto}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public class ProdottoException extends RuntimeException {

    /**
     * throws the exception with the param passed from {@link ExceptionPhrases}
     *
     * @param message with the custom exception
     */
    public ProdottoException(String message) {
        super(message);
    }

}
