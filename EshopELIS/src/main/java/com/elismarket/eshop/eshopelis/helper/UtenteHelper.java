package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;

@Component
public class UtenteHelper {

    @Autowired
    UtenteCrud utenteCrud;

    @Autowired
    FeedbackHelper feedbackHelper;

    @Autowired
    PropostaHelper propostaHelper;

    @Autowired
    PagamentoHelper pagamentoHelper;

    @Autowired
    ProdottoHelper prodottoHelper;


    public Utente findById(Long utente_id) {
        return utenteCrud.findById(utente_id).orElseThrow(() -> new UtenteException(CANNOT_FIND_ELEMENT.name()));
    }

    public Feedback linkFeedbackToUser(Long utente_id, Feedback f) {
        Utente u = utenteCrud.findById(utente_id).orElseThrow(() -> new UtenteException(CANNOT_FIND_ELEMENT.name()));
        f.setUtente(u);
        return f;
    }
}
