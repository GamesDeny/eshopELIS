package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.RigaOrdineException;
import com.elismarket.eshop.eshopelis.helper.OrdineHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.RigaOrdineService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;
import static java.util.Objects.isNull;

/**
 * {@link RigaOrdine RigaOrdine} service class for interaction between DB and relative Controller
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Service
public class RigaOrdineServiceImpl implements RigaOrdineService {

    /**
     * @see RigaOrdineCrud
     */
    @Autowired
    RigaOrdineCrud rigaOrdineCrud;

    /**
     * @see OrdineHelper
     */
    @Autowired
    OrdineHelper ordineHelper;

    /**
     * @see ProdottoHelper
     */
    @Autowired
    ProdottoHelper prodottoHelper;

    /**
     * Adds a new RigaOrdine
     *
     * @param rigaOrdineDTO {@link RigaOrdineDTO RigaOrdineDTO} with the fields
     * @return added RigaOrdine
     */
    @Override
    public RigaOrdineDTO addRigaOrdine(RigaOrdineDTO rigaOrdineDTO) {
        Checkers.rigaOrdineFieldsChecker(rigaOrdineDTO);
        prodottoHelper.decreaseQuantita(rigaOrdineDTO.prodotto_id, rigaOrdineDTO.quantitaProdotto);
        RigaOrdine r = RigaOrdine.of(rigaOrdineDTO);
        r.setProdotto(prodottoHelper.findById(rigaOrdineDTO.prodotto_id));
        return RigaOrdine.to(rigaOrdineCrud.saveAndFlush(r));
    }

    /**
     * Updates RigaOrdine related to the id with the updated fields in the RigaOrdineDTO
     *
     * @param id            of the {@link RigaOrdine RigaOrdine} to update
     * @param rigaOrdineDTO {@link RigaOrdineDTO RigaOrdineDTO} with updated fields
     * @return updated RigaOrdine
     * @throws RigaOrdineException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     */
    @Override
    public RigaOrdineDTO updateRigaOrdine(Long id, RigaOrdineDTO rigaOrdineDTO) {
        if (isNull(id) || isNull(rigaOrdineDTO))
            throw new RigaOrdineException(MISSING_PARAMETERS.name());

        if (!rigaOrdineCrud.existsById(id))
            throw new RigaOrdineException(CANNOT_FIND_ELEMENT.name());

        RigaOrdine r = rigaOrdineCrud.findById(id).orElseThrow(() -> new RigaOrdineException(CANNOT_FIND_ELEMENT.name()));

        rigaOrdineDTO.id = id;
        rigaOrdineDTO.prezzoTotale = isNull(rigaOrdineDTO.prezzoTotale) ? r.getPrezzoTotale() : rigaOrdineDTO.prezzoTotale;
        rigaOrdineDTO.quantitaProdotto = isNull(rigaOrdineDTO.quantitaProdotto) ? r.getQuantitaProdotto() : rigaOrdineDTO.quantitaProdotto;
        rigaOrdineDTO.scontoApplicato = isNull(rigaOrdineDTO.scontoApplicato) ? r.getScontoApplicato() : rigaOrdineDTO.scontoApplicato;

        Checkers.rigaOrdineFieldsChecker(rigaOrdineDTO);

        RigaOrdine save = RigaOrdine.of(rigaOrdineDTO);
        save.setOrdine(isNull(rigaOrdineDTO.ordine_id) ? r.getOrdine() : ordineHelper.findById(rigaOrdineDTO.ordine_id));
        save.setProdotto(isNull(rigaOrdineDTO.prodotto_id) ? r.getProdotto() : prodottoHelper.findById(rigaOrdineDTO.prodotto_id));

        return RigaOrdine.to(rigaOrdineCrud.saveAndFlush(save));
    }

    /**
     * Remove the relative RigaOrdine
     *
     * @param id of the {@link RigaOrdine RigaOrdine} to remove
     * @return HTTP 200 if deleted successfully, else 500
     * @throws RigaOrdineException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws RigaOrdineException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public Boolean removeRigaOrdine(Long id) {
        if (isNull(id))
            throw new RigaOrdineException(MISSING_PARAMETERS.name());

        if (!rigaOrdineCrud.existsById(id))
            throw new RigaOrdineException(CANNOT_FIND_ELEMENT.name());

        rigaOrdineCrud.deleteById(id);
        return rigaOrdineCrud.existsById(id);
    }

    /**
     * Retrieves all RigaOrdine
     *
     * @return List {@link RigaOrdineDTO RigaOrdineDTO}
     */
    @Override
    public List<RigaOrdineDTO> getAll() {

        List<RigaOrdineDTO> result = new ArrayList<>();
        rigaOrdineCrud.findAll().forEach(rigaOrdine -> result.add(RigaOrdine.to(rigaOrdine)));
        return result;
    }

    /**
     * Retrieves RigaOrdine for provided id
     *
     * @param id for the {@link RigaOrdine RigaOrdine}
     * @return List {@link RigaOrdineDTO RigaOrdineDTO}
     * @throws RigaOrdineException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws RigaOrdineException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public RigaOrdineDTO getById(Long id) {
        if (isNull(id))
            throw new RigaOrdineException(MISSING_PARAMETERS.name());

        if (!rigaOrdineCrud.existsById(id))
            throw new RigaOrdineException(CANNOT_FIND_ELEMENT.name());
        return RigaOrdine.to(rigaOrdineCrud.findById(id).orElseThrow(() -> new RigaOrdineException(CANNOT_FIND_ELEMENT.name())));
    }

}
