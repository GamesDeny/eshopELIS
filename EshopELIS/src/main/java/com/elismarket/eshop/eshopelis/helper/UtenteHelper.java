package com.elismarket.eshop.eshopelis.helper;

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
}
