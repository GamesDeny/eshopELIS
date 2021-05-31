package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtenteHelper {

    @Autowired
    FeedbackCrud feedbackCrud;

    @Autowired
    PropostaCrud propostaCrud;

    @Autowired
    PagamentoCrud pagamentoCrud;

    @Autowired
    ProdottoCrud prodottoCrud;

    @Autowired
    UtenteCrud utenteCrud;

    public Utente findById(Long utente_id) {
        return utenteCrud.findById(utente_id).orElseThrow(() -> new UtenteException("Cannot find User"));
    }
}
