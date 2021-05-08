package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.cruderepos.PropostaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    @Autowired
    private PropostaCrud propostaCrud;
}
