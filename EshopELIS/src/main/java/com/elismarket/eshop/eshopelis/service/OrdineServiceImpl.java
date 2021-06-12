package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.OrdineException;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.helper.PagamentoHelper;
import com.elismarket.eshop.eshopelis.helper.RigaOrdineHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.OrdineService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;

/**
 * {@link Ordine Ordine} service class for interaction between DB and relative Controller
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Service
public class OrdineServiceImpl implements OrdineService {

    /**
     * @see OrdineCrud
     */
    @Autowired
    OrdineCrud ordineCrud;

    /**
     * @see PagamentoHelper
     */
    @Autowired
    PagamentoHelper pagamentoHelper;

    /**
     * @see RigaOrdineHelper
     */
    @Autowired
    RigaOrdineHelper rigaOrdineHelper;

    /**
     * @see UtenteHelper
     */
    @Autowired
    UtenteHelper utenteHelper;

    /**
     * Adds an Ordine
     *
     * @param userId      id of the {@link Utente Utente} that placed order
     * @param pagamentoId id of the {@link Pagamento Pagamento} that
     * @param righe       ids of the {@link RigaOrdine RigaOrdine} that are connected to the order
     * @return added Ordine
     */
    @Override
    public OrdineDTO saveOrdine(Long userId, Long pagamentoId, List<RigaOrdineDTO> righe) {
        Ordine o = new Ordine();
        List<Long> righeId = new ArrayList<>();
        righe.forEach(rigaOrdineDTO -> righeId.add(rigaOrdineDTO.id));

        o.setEvaso(false);
        o.setPagamento(pagamentoHelper.findById(pagamentoId));
        o.setUtente(utenteHelper.findById(userId));

        return Ordine.to(ordineCrud.saveAndFlush(o));
    }

    /**
     * Updates Ordine with the relative id with the info provided in the DTO
     *
     * @param ordineId  of the {@link Ordine Ordine} to
     * @param ordineDTO {@link OrdineDTO OrdineDTO} with information to update
     * @return DTO of updated item
     * @throws OrdineException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     */
    @Override
    public OrdineDTO updateOrdine(Long ordineId, OrdineDTO ordineDTO) {
        if (!ordineCrud.existsById(ordineId))
            throw new OrdineException(CANNOT_FIND_ELEMENT.name());

        Ordine o = ordineCrud.findById(ordineId).orElseThrow(() -> new OrdineException(CANNOT_FIND_ELEMENT.name()));

        ordineDTO.id = ordineId;
        ordineDTO.evaso = Objects.isNull(ordineDTO.evaso) ? o.getEvaso() : ordineDTO.evaso;
        ordineDTO.dataEvasione = Objects.isNull(ordineDTO.dataEvasione) ? o.getDataEvasione() : ordineDTO.dataEvasione;

        Ordine save = Ordine.of(ordineDTO);
        save.setPagamento(Objects.isNull(ordineDTO.pagamento_id) ? o.getPagamento() : pagamentoHelper.findById(ordineDTO.pagamento_id));
        ordineCrud.saveAndFlush(save);

        return Ordine.to(ordineCrud.findById(ordineId).orElseThrow(() -> new OrdineException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Delete of Ordine with relative id
     *
     * @param id of the {@link Ordine Ordine} to
     * @return HTTP status 200 if item is deleted otherwise 500
     * @throws OrdineException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public Boolean removeOrdine(Long id) {
        if (Objects.isNull(id))
            throw new OrdineException(MISSING_PARAMETERS.name());

        if (!ordineCrud.existsById(id))
            throw new OrdineException(CANNOT_FIND_ELEMENT.name());

        ordineCrud.deleteById(id);
        return !ordineCrud.existsById(id);
    }

    /**
     * Returns all Ordine in the DB
     *
     * @return List of {@link OrdineDTO OrdineDTO}
     */
    @Override
    public List<OrdineDTO> getAll() {

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAll().forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    /**
     * Returns Ordine for provided id
     *
     * @return {@link OrdineDTO OrdineDTO}
     * @throws OrdineException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws OrdineException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public OrdineDTO getById(Long id) {
        if (Objects.isNull(id))
            throw new OrdineException(MISSING_PARAMETERS.name());

        if (!ordineCrud.existsById(id))
            throw new OrdineException(CANNOT_FIND_ELEMENT.name());
        return Ordine.to(ordineCrud.findById(id).orElseThrow(() -> new OrdineException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Returns all orders for an Utente
     *
     * @param userId id of the {@link Utente Utente}
     * @return List of {@link OrdineDTO OrdineDTO}
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public List<OrdineDTO> getAllByUtente(Long userId) {
        if (Objects.isNull(userId))
            throw new UtenteException(MISSING_PARAMETERS.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByUtente(utenteHelper.findById(userId)).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    /**
     * Returns all ordini evasi depending on variable
     *
     * @param evaso value of evaso
     * @return List of {@link OrdineDTO OrdineDTO} evasi or not
     * @throws OrdineException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public List<OrdineDTO> getEvaso(Boolean evaso) {
        if (Objects.isNull(evaso))
            throw new OrdineException(MISSING_PARAMETERS.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByEvaso(evaso).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    /**
     * Returns all Ordine where evaso = true, before a data
     *
     * @param dataEvasione dataEvasione of all Ordine
     * @return List {@link OrdineDTO OrdineDTO}
     * @throws OrdineException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @see Ordine#getDataEvasione()
     */
    @Override
    public List<OrdineDTO> getDataPrima(LocalDate dataEvasione) {
        if (Objects.isNull(dataEvasione))
            throw new OrdineException(MISSING_PARAMETERS.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByDataEvasioneBefore(dataEvasione).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    /**
     * Returns all Ordine where evaso = true, between two values
     *
     * @param dataInizio starting data
     * @param dataFine   final data
     * @return List {@link OrdineDTO OrdineDTO}
     * @throws OrdineException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public List<OrdineDTO> getDataTra(LocalDate dataInizio, LocalDate dataFine) {
        if (Objects.isNull(dataInizio) || Objects.isNull(dataFine))
            throw new OrdineException(MISSING_PARAMETERS.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByDataEvasioneBetween(dataInizio, dataFine).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    /**
     * Returns all Ordine where evaso = true, after a data
     *
     * @param dataEvasione dataEvasione of all Ordine
     * @return List {@link OrdineDTO OrdineDTO}
     * @throws OrdineException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @see Ordine#getDataEvasione()
     */
    @Override
    public List<OrdineDTO> getDataDopo(LocalDate dataEvasione) {
        if (Objects.isNull(dataEvasione))
            throw new OrdineException(MISSING_PARAMETERS.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByDataEvasioneAfter(dataEvasione).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    /**
     * Adds a RigaOrdine to the relative Ordine
     *
     * @param ordineId      id of {@link Ordine Ordine}
     * @param rigaOrdineDTO {@link RigaOrdine RigaOrdine} to add
     * @return the inserted data
     * @throws OrdineException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @see Checkers#rigaOrdineFieldsChecker(RigaOrdineDTO)
     */
    @Override
    public RigaOrdine addRigaOrdineToOrdine(Long ordineId, RigaOrdineDTO rigaOrdineDTO) {
        if (Objects.isNull(ordineId))
            throw new OrdineException(MISSING_PARAMETERS.name());
        Checkers.rigaOrdineFieldsChecker(rigaOrdineDTO);

        if (!ordineCrud.existsById(ordineId))
            throw new OrdineException(CANNOT_FIND_ELEMENT.name());

        return rigaOrdineHelper.addRigaOrdineToOrdine(ordineId, rigaOrdineDTO);
    }
}
