package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UtenteHelper {

    @Autowired
    private FeedbackCrud feedbackCrud;

    @Autowired
    private PropostaCrud propostaCrud;

    @Autowired
    private PagamentoCrud pagamentoCrud;

    @Autowired
    private ProdottoCrud prodottoCrud;

    @Autowired
    private UtenteCrud utenteCrud;

    public Utente findById(Long utente_id) {
        return utenteCrud.findById(utente_id).orElseThrow(() -> new UtenteException("Cannot find User"));
    }
}
