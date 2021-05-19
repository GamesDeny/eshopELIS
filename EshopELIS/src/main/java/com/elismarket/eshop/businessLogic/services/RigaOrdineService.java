package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.cruderepos.RigaOrdineCrud;
import com.elismarket.eshop.customExceptions.RigaOrdineException;
import com.elismarket.eshop.model.dto.RigaOrdineDTO;
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
    private RigaOrdineCrud rigaOrdineCrud;

    public Iterable<RigaOrdineImpl> getAll() {
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
        rigaOrdineCrud.save(RigaOrdineImpl.of(rigaOrdineDTO));
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
