package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return utenteCrud.findById(utente_id).orElseThrow(() -> new UtenteException("Cannot find User"));
    }
}
