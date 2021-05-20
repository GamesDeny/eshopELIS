package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.exception.OrdineException;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/*
 *
 * Service class for CRUD operations and control of Order
 *
 */

@Service
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    private OrdineCrud ordineCrud;

    public List<OrdineDTO> getAll() {
        List<OrdineDTO> result = new ArrayList<>();

        ordineCrud.findAll().forEach(ordine -> result.add(Ordine.to(ordine)));

        return result;
    }

    public List<OrdineDTO> getEvaso(Boolean evaso) {
        List<OrdineDTO> result = new ArrayList<>();

        ordineCrud.findAllByEvaso(evaso).forEach(ordine -> result.add(Ordine.to(ordine)));

        return result;
    }

    public List<OrdineDTO> getDataPrima(LocalDate dataEvasione) {
        List<OrdineDTO> result = new ArrayList<>();

        ordineCrud.findAllByDataEvasioneBefore(dataEvasione).forEach(ordine -> result.add(Ordine.to(ordine)));

        return result;
    }

    public List<OrdineDTO> getDataTra(LocalDate dataEvasione1, LocalDate dataEvasione2) {
        List<OrdineDTO> result = new ArrayList<>();

        ordineCrud.findAllByDataEvasioneBetween(dataEvasione1, dataEvasione2).forEach(ordine -> result.add(Ordine.to(ordine)));

        return result;
    }

    public List<OrdineDTO> getDataDopo(LocalDate dataEvasione) {
        List<OrdineDTO> result = new ArrayList<>();

        ordineCrud.findAllByDataEvasioneAfter(dataEvasione).forEach(ordine -> result.add(Ordine.to(ordine)));

        return result;
    }

    public void saveOrdine(OrdineDTO ordineDTO) {
        ordineCrud.save(ordineDTO);
    }

    public Boolean updateOrdine(OrdineDTO ordineDTO) {
        try {
            ordineCrud.save(Ordine.of(ordineDTO));
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

    public OrdineDTO getById(Long id) {
        if (!ordineCrud.findById(id).isPresent())
            throw new OrdineException("Not found");
        return Ordine.to(ordineCrud.findById(id).get());
    }
}
