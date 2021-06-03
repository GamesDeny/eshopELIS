package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;

/**
 * Helper class for {@link Utente Utente} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Component
public class UtenteHelper {

    /**
     * @see UtenteCrud
     */
    @Autowired
    UtenteCrud utenteCrud;

    /**
     * @see FeedbackHelper
     */
    @Autowired
    FeedbackHelper feedbackHelper;

    /**
     * @see PropostaHelper
     */
    @Autowired
    PropostaHelper propostaHelper;

    /**
     * @see PagamentoHelper
     */
    @Autowired
    PagamentoHelper pagamentoHelper;

    /**
     * @see ProdottoHelper
     */
    @Autowired
    ProdottoHelper prodottoHelper;

    /**
     * method to search an user from the repository
     *
     * @param utente_id id of the user that needs to be searched
     * @return Utente found in the repository
     * @throws UtenteException with {@link com.elismarket.eshop.eshopelis.exception.ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} phrase
     */
    public Utente findById(Long utente_id) {
        return utenteCrud.findById(utente_id).orElseThrow(() -> new UtenteException(CANNOT_FIND_ELEMENT.name()));
    }

}
