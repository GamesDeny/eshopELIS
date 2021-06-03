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
     * Exception for a list is empty
     */
    LIST_IS_EMPTY,


    /**
     * Exception for when a class cannot be retrieved
     */
    CANNOT_FIND_ELEMENT,


    /**
     * Exception for when there are duplicates in {@link Utente Utente}
     *
     * @see UtenteServiceImpl#duplicateChecker(UtenteDTO) duplicateChecker
     */
    DUPLICATE,


    /**
     * Exception for when password of a {@link Utente Utente} is not following some rules
     *
     * @see Checkers#mailChecker(String) mailChecker
     * @see Checkers#passwordChecker(String) passwordChecker
     */
    MAIL_OR_PASSWORD_INCONSISTENT,


    /**
     * Exception for when password of a {@link Utente Utente} is not following {@link Checkers#mailChecker(String) mailChecker} rules
     *
     * @see Checkers#mailChecker(String) mailChecker
     */
    INVALID_MAIL,


    /**
     * Exception for when password of a {@link Utente Utente} is not following {@link Checkers#passwordChecker(String) passwordChecker} rules
     *
     * @see Checkers#utenteFieldsChecker(UtenteDTO) utenteFieldsChecker
     */
    INVALID_PASSWORD,


    /**
     * Exception for when birthDate for a {@link Utente Utente} is missing
     *
     * @see UtenteServiceImpl#duplicateChecker(UtenteDTO) duplicateChecker
     */
    DATE_NOT_VALID,

    /**
     * Exception for when sigla of a {@link Utente Utente} is wrong
     *
     * @see Checkers#utenteFieldsChecker(UtenteDTO) utenteFieldsChecker
     */
    INCONSISTENT_SIGLA,

    /**
     * Exception for when quantita of a {@link Prodotto Prodotto} is Unsufficient when placing an order
     *
     * @see ProdottoHelper
     */
    UNSUFFICIENT_QUANTITA,

    /**
     * Exception for when Utente is too young (< 16)
     *
     * @see Checkers#birthDateChecker(LocalDate) birthDateChecker
     */
    TOO_YOUNG,

    /**
     * Exception for when Utente is too old (> 90)
     *
     * @see Checkers#birthDateChecker(LocalDate) birthDateChecker
     */
    TOO_OLD
}
