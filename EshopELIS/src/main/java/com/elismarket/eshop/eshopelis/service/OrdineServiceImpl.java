package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.exception.OrdineException;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
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
public class OrdineServiceImpl {

    @Autowired
    private OrdineCrud ordineCrud;

    public List<Ordine> getAll() {
        return ordineCrud.findAll();
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

    public Boolean updateOrdine(OrdineDTO ordineDTO) {
        Ordine u = Ordine.of(ordineDTO);
        try {
            ordineCrud.save(u);
            return true;
        } catch (Exception e) {
//            throw new OrdineException("Aggiornamento non riuscito, ricontrolla i dati inviati!");
        }
        return false;
    }

    public void removeOrdine(Long id) {
        try {
            ordineCrud.deleteById(id);
        } catch (Exception e) {
            throw new OrdineException("Cannot find Ordine for provided item");
        }
    }

    public Ordine getById(Long id) {
        if (!ordineCrud.findById(id).isPresent())
            throw new OrdineException("Not found");
        return ordineCrud.findById(id).get();
    }
}
