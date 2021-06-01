package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.RigaOrdineException;
import com.elismarket.eshop.eshopelis.helper.OrdineHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import com.elismarket.eshop.eshopelis.utility.Checkers;
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
    RigaOrdineCrud rigaOrdineCrud;

    @Autowired
    OrdineHelper ordineHelper;

    @Autowired
    ProdottoHelper prodottoHelper;


    @Override
    public RigaOrdineDTO addRigaOrdine(RigaOrdineDTO rigaOrdineDTO) {
        Checkers.rigaOrdineFieldsChecker(rigaOrdineDTO);
        return RigaOrdine.to(rigaOrdineCrud.saveAndFlush(RigaOrdine.of(rigaOrdineDTO)));
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

        Checkers.rigaOrdineFieldsChecker(rigaOrdineDTO);

        RigaOrdine save = RigaOrdine.of(rigaOrdineDTO);
        save.setOrdine(Objects.isNull(rigaOrdineDTO.ordine_id) ? r.getOrdine() : ordineHelper.findById(rigaOrdineDTO.ordine_id));
        save.setProdotto(Objects.isNull(rigaOrdineDTO.prodotto_id) ? r.getProdotto() : prodottoHelper.findById(rigaOrdineDTO.prodotto_id));
        rigaOrdineCrud.saveAndFlush(save);

        return RigaOrdine.to(rigaOrdineCrud.findById(id).orElseThrow(() -> new RigaOrdineException("Cannot find RigaOrdine")));
    }

    @Override
    public Boolean removeRigaOrdine(Long id) {
        if (!rigaOrdineCrud.existsById(id))
            throw new RigaOrdineException("Not Found");

        rigaOrdineCrud.deleteById(id);
        return !rigaOrdineCrud.existsById(id);
    }

    @Override
    public List<RigaOrdineDTO> getAll() {
        if (rigaOrdineCrud.findAll().isEmpty())
            throw new RigaOrdineException("List is empty");

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
        if (rigaOrdineCrud.findAll().isEmpty())
            throw new RigaOrdineException("List is empty");

        List<RigaOrdineDTO> result = new ArrayList<>();
        rigaOrdineCrud.findAllByQuantitaProdottoGreaterThanEqual(quantita).forEach(rigaOrdine -> result.add(RigaOrdine.to(rigaOrdine)));
        return result;
    }

}
