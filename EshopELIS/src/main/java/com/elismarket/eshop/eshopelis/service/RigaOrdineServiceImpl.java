package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.RigaOrdineException;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/*
 *
 * Service class for CRUD operations and control of Order
 *
 */

@Service
public class RigaOrdineServiceImpl implements RigaOrdineService {

    @Autowired
    private RigaOrdineCrud rigaOrdineCrud;

    public RigaOrdineDTO addRigaOrdine(RigaOrdineDTO rigaOrdineDTO) {
        try {
            rigaOrdineCrud.save(RigaOrdine.of(rigaOrdineDTO));
        } catch (Exception e) {
            throw new RigaOrdineException("Cannot find RigaOrdine for given element");
        }
        return rigaOrdineCrud.findById(rigaOrdineDTO.id).isPresent() ? RigaOrdine.to(rigaOrdineCrud.findById(rigaOrdineDTO.id).get()) : null;
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

    public List<RigaOrdineDTO> getAll() {
        List<RigaOrdineDTO> result = new ArrayList<>();

        rigaOrdineCrud.findAll().forEach(rigaOrdine -> result.add(RigaOrdine.to(rigaOrdine)));

        return result;
    }

    public RigaOrdineDTO getById(Long id) {
        if (!rigaOrdineCrud.findById(id).isPresent())
            throw new RigaOrdineException("Not found");
        return RigaOrdine.to(rigaOrdineCrud.findById(id).get());
    }

    public List<RigaOrdineDTO> getByQuantita(Integer quantita) {
        List<RigaOrdineDTO> result = new ArrayList<>();

        rigaOrdineCrud.findAllByQuantitaProdottoGreaterThanEqual(quantita).forEach(rigaOrdine -> result.add(RigaOrdine.to(rigaOrdine)));

        return result;
    }

}
