package com.elismarket.eshop.businesslogic.services;

import com.elismarket.eshop.businesslogic.crudrepos.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {

    @Autowired
    UtenteCrud utenteCrud;
}
