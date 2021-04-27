package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.cruderepos.RigaOrdineCrud;
import com.elismarket.eshop.model.entities.RigaOrdineImpl;
import com.elismarket.eshop.model.interfaces.RigaOrdine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
 *
 * Service class for CRUD operations and control of Order
 *
 */

@Service
public class RigaOrdineService {

    @Autowired
    RigaOrdineCrud rigaOrdineCrud;

    public Iterable<RigaOrdineImpl> getAll() {
        return rigaOrdineCrud.findAll();
    }

    public List<RigaOrdine> getByQuantita(Integer quantita) {
        return rigaOrdineCrud.findAllByQuantitaProdottoGreaterThanEqual(quantita);
    }
}
