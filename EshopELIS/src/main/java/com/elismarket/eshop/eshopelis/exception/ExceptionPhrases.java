package com.elismarket.eshop.eshopelis.exception;

import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.service.UtenteServiceImpl;
import com.elismarket.eshop.eshopelis.utility.Checkers;

import java.time.LocalDate;

/**
 * Enum for the needed exception phrases
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public enum ExceptionPhrases {
    /**
     * Exception for when are missing needed parameters for an item
     */
    MISSING_PARAMETERS,


    /**
     * Exception for when a class cannot be retrieved
     */
    CANNOT_FIND_ELEMENT,


    /**
     * Exception for when there are duplicates in {@link Utente Utente}
     */
    DUPLICATE,


    /**
     * Exception for when password of a {@link Utente Utente} is not following some rules
     *
     * @see Checkers#mailChecker(String)
     * @see Checkers#passwordChecker(String)
     */
    MAIL_OR_PASSWORD_INCONSISTENT,


    /**
     * Exception for when password of a {@link Utente Utente} is not following {@link Checkers#mailChecker(String) mailChecker} rules
     *
     * @see Checkers#mailChecker(String)
     */
    INVALID_MAIL,


    /**
     * Exception for when password of a {@link Utente Utente} is not following {@link Checkers#passwordChecker(String) passwordChecker} rules
     *
     * @see Checkers#utenteFieldsChecker(UtenteDTO)
     */
    INVALID_PASSWORD,


    /**
     * Exception for when birthDate for a {@link Utente Utente} is missing
     */
    DATE_NOT_VALID,

    /**
     * Exception for when sigla of a {@link Utente Utente} is wrong
     *
     * @see Checkers#utenteFieldsChecker(UtenteDTO)
     */
    INCONSISTENT_SIGLA,

    /**
     * Exception for when quantita of a {@link Prodotto Prodotto} is Insufficient when placing an order
     *
     * @see ProdottoHelper
     */
    INSUFFICIENT_QUANTITA,

    /**
     * Exception for when Utente is too young (less than 16)
     *
     * @see Checkers#birthDateChecker(LocalDate)
     */
    TOO_YOUNG,

    /**
     * Exception for when Utente is too old (greater than 90)
     *
     * @see Checkers#birthDateChecker(LocalDate)
     */
    TOO_OLD,

    /**
     * Exception for when something went wrong with logout
     *
     * @see UtenteServiceImpl#getLogout(Long)
     */
    LOGOUT_ERROR
}
