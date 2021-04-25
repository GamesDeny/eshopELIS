package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.crudrepos.OrdineCrud;
import com.elismarket.eshop.model.dto.OrdineDTO;
import com.elismarket.eshop.model.entities.OrdineImpl;
import com.elismarket.eshop.model.interfaces.Ordine;
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

    public void saveOrdine(OrdineDTO ordineDTO) {
        ordineCrud.save(ordineDTO);
    }

    public void removeOrdine(Long id) {
        ordineCrud.deleteById(id);
    }
}
