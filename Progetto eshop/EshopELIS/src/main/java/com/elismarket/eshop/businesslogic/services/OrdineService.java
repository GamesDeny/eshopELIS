package com.elismarket.eshop.businesslogic.services;

import com.elismarket.eshop.businesslogic.crudrepos.OrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdineService {

    @Autowired
    OrdineCrud ordineCrud;
}
