package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaHelper {
    @Autowired
    private PropostaCrud propostaCrud;

    @Autowired
    private UtenteCrud utenteCrud;
}
