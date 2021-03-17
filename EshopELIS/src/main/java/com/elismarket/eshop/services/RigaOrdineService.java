package com.elismarket.eshop.services;

import com.elismarket.eshop.crudrepos.RigaOrdineCrud;
import com.elismarket.eshop.interfaces.RigaOrdine;
import com.elismarket.eshop.model.RigaOrdineImpl;
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

    public List<RigaOrdineImpl> getAll() {
        return (List<RigaOrdineImpl>) rigaOrdineCrud.findAll();
    }

    public List<RigaOrdine> getByQuantita(Integer quantita) {
        return rigaOrdineCrud.findAllByQuantitaProdottoGreaterThanEqual(quantita);
    }
}
