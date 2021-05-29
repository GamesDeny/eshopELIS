package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.RigaOrdineException;
import com.elismarket.eshop.eshopelis.helper.OrdineHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/*
 *
 * Service class for CRUD operations and control of Order
 *
 */

@Service
public class RigaOrdineServiceImpl implements RigaOrdineService {

    @Autowired
    private OrdineHelper ordineHelper;

    @Autowired
    private ProdottoHelper prodottoHelper;

    @Autowired
    private RigaOrdineCrud rigaOrdineCrud;

    @Override
    public RigaOrdineDTO addRigaOrdine(RigaOrdineDTO rigaOrdineDTO) {
        try {
            rigaOrdineCrud.save(RigaOrdine.of(rigaOrdineDTO));
        } catch (Exception e) {
            throw new RigaOrdineException("Cannot find RigaOrdine for given element");
        }
        return rigaOrdineCrud.findById(rigaOrdineDTO.id).isPresent() ? RigaOrdine.to(rigaOrdineCrud.findById(rigaOrdineDTO.id).orElseThrow(() -> new RigaOrdineException("Cannot find RigaOrdine"))) : null;
    }

    @Override
    public RigaOrdineDTO updateRigaOrdine(Long id, RigaOrdineDTO rigaOrdineDTO) {
        if (!rigaOrdineCrud.existsById(id))
            throw new RigaOrdineException("Not Found");

        RigaOrdine r = rigaOrdineCrud.findById(id).orElseThrow(() -> new RigaOrdineException("Cannot find RigaOrdine"));

        rigaOrdineDTO.id = id;
        rigaOrdineDTO.prezzoTotale = Objects.isNull(rigaOrdineDTO.prezzoTotale) ? r.getPrezzoTotale() : rigaOrdineDTO.prezzoTotale;
        rigaOrdineDTO.quantitaProdotto = Objects.isNull(rigaOrdineDTO.quantitaProdotto) ? r.getQuantitaProdotto() : rigaOrdineDTO.quantitaProdotto;
        rigaOrdineDTO.scontoApplicato = Objects.isNull(rigaOrdineDTO.scontoApplicato) ? r.getScontoApplicato() : rigaOrdineDTO.scontoApplicato;

        return RigaOrdine.to(rigaOrdineCrud.findById(id).orElseThrow(() -> new RigaOrdineException("Cannot find RigaOrdine")));
    }

    @Override
    public Boolean removeRigaOrdine(Long id) {
        try {
            rigaOrdineCrud.deleteById(id);
            return true;
        } catch (Exception e) {
//            throw new RigaOrdineException("Cannot find RigaOrdine for given element");
        }
        return false;
    }

    @Override
    public List<RigaOrdineDTO> getAll() {
        List<RigaOrdineDTO> result = new ArrayList<>();

        rigaOrdineCrud.findAll().forEach(rigaOrdine -> result.add(RigaOrdine.to(rigaOrdine)));

        return result;
    }

    @Override
    public RigaOrdineDTO getById(Long id) {
        if (!rigaOrdineCrud.findById(id).isPresent())
            throw new RigaOrdineException("Not found");
        return RigaOrdine.to(rigaOrdineCrud.findById(id).orElseThrow(() -> new RigaOrdineException("Cannot find RigaOrdine")));
    }

    @Override
    public List<RigaOrdineDTO> getByQuantita(Integer quantita) {
        List<RigaOrdineDTO> result = new ArrayList<>();

        rigaOrdineCrud.findAllByQuantitaProdottoGreaterThanEqual(quantita).forEach(rigaOrdine -> result.add(RigaOrdine.to(rigaOrdine)));

        return result;
    }

}
