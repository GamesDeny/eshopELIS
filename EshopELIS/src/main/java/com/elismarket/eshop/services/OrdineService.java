package com.elismarket.eshop.services;

import com.elismarket.eshop.crudrepos.OrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 *
 * Service class for CRUD operations and control of Order
 *
 */

@Service
public class OrdineService {

    @Autowired
    OrdineCrud ordineCrud;
}
