package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.RigaOrdineException;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/*
 *
 * Service class for CRUD operations and control of Order
 *
 */

@Service
public class RigaOrdineServiceImpl {

    @Autowired
    private RigaOrdineCrud rigaOrdineCrud;

    public Iterable<RigaOrdine> getAll() {
        return rigaOrdineCrud.findAll();
    }

    public RigaOrdine getById(Long id) {
        if (!rigaOrdineCrud.findById(id).isPresent())
            throw new RigaOrdineException("Not found");
        return rigaOrdineCrud.findById(id).get();
    }

    public List<RigaOrdine> getByQuantita(Integer quantita) {
        return rigaOrdineCrud.findAllByQuantitaProdottoGreaterThanEqual(quantita);
    }

    public Boolean addRigaOrdine(RigaOrdineDTO rigaOrdineDTO) {
        rigaOrdineCrud.save(RigaOrdine.of(rigaOrdineDTO));
        return true;
    }

    public Boolean removeRigaOrdine(Long id) {
        try {
            rigaOrdineCrud.deleteById(id);
            return true;
        } catch (Exception e) {
//            throw new RigaOrdineException("Cannot find RigaOrdine for given element");
        }
        return false;
    }

}
