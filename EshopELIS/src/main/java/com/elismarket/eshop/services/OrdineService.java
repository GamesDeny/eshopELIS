package com.elismarket.eshop.services;

import com.elismarket.eshop.crudrepos.OrdineCrud;
import com.elismarket.eshop.interfaces.Ordine;
import com.elismarket.eshop.model.OrdineImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


/*
 *
 * Service class for CRUD operations and control of Order
 *
 */

@Service
public class OrdineService {

    @Autowired
    OrdineCrud ordineCrud;

    public List<OrdineImpl> getAll() {
        return (List<OrdineImpl>) ordineCrud.findAll();
    }

    public List<Ordine> getEvaso(Boolean evaso) {
        return ordineCrud.findAllByEvaso(evaso);
    }

    public List<Ordine> getDataPrima(LocalDate dataEvasione) {
        return ordineCrud.findAllByDataEvasioneBefore(dataEvasione);
    }

    public List<Ordine> getDataTra(LocalDate dataEvasione1, LocalDate dataEvasione2) {
        return ordineCrud.findAllByDataEvasioneBetween(dataEvasione1, dataEvasione2);
    }

    public List<Ordine> getDataDopo(LocalDate dataEvasione) {
        return ordineCrud.findAllByDataEvasioneAfter(dataEvasione);
    }
}
