package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.PagamentoException;
import com.elismarket.eshop.eshopelis.helper.OrdineHelper;
import com.elismarket.eshop.eshopelis.helper.TipoMetodoHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.PagamentoService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.*;
import static java.util.Objects.isNull;

/**
 * {@link Pagamento Pagamento} service class for interaction between DB and relative Controller
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Service
public class PagamentoServiceImpl implements PagamentoService {

    /**
     * @see PagamentoCrud
     */
    @Autowired
    PagamentoCrud pagamentoCrud;

    /**
     * @see UtenteHelper
     */
    @Autowired
    UtenteHelper utenteHelper;

    /**
     * @see OrdineHelper
     */
    @Autowired
    OrdineHelper ordineHelper;

    /**
     * @see TipoMetodoHelper
     */
    @Autowired
    TipoMetodoHelper tipoMetodoHelper;

    /**
     * adds a new Pagamento
     *
     * @param pagamentoDTO {@link PagamentoDTO PagamentoDTO} of the Pagamento to add
     * @return DTO of created item
     * @throws PagamentoException with {@link ExceptionPhrases#INVALID_MAIL INVALID_MAIL} message
     */
    @Override
    @Transactional
    public PagamentoDTO addPagamento(PagamentoDTO pagamentoDTO) {
        Checkers.pagamentoFieldsChecker(pagamentoDTO);
        Pagamento p = Pagamento.of(pagamentoDTO);
        p.setTipoMetodo(tipoMetodoHelper.findById(pagamentoDTO.tipoMetodo_id));
        p.setUtente(utenteHelper.findById(pagamentoDTO.utente_id));

        if (!(isNull(pagamentoDTO.paypalMail) || Checkers.mailChecker(pagamentoDTO.paypalMail)))
            throw new PagamentoException(INVALID_MAIL.name());

        return Pagamento.to(pagamentoCrud.saveAndFlush(p));
    }

    /**
     * Updates an existing Pagamento
     *
     * @param pagamentoId  of the {@link Pagamento Pagamento} to update
     * @param pagamentoDTO {@link PagamentoDTO PagamentoDTO} of the Pagamento to update
     * @return DTO of updated item
     * @throws PagamentoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws PagamentoException with {@link ExceptionPhrases#INVALID_MAIL INVALID_MAIL} message
     */
    @Override
    @Transactional
    public PagamentoDTO updatePagamento(Long pagamentoId, PagamentoDTO pagamentoDTO) {
        if (isNull(pagamentoId) || isNull(pagamentoDTO))
            throw new PagamentoException(MISSING_PARAMETERS.name());

        if (!isNull(pagamentoDTO.paypalMail) && !Checkers.mailChecker(pagamentoDTO.paypalMail))
            throw new PagamentoException(INVALID_MAIL.name());

        Pagamento p = pagamentoCrud.findById(pagamentoId).orElseThrow(() -> new PagamentoException(CANNOT_FIND_ELEMENT.name()));

        p.setDescrizione(isNull(pagamentoDTO.descrizione) ? p.getDescrizione() : pagamentoDTO.descrizione);
        p.setContanti(isNull(pagamentoDTO.contanti) ? p.getContanti() : pagamentoDTO.contanti);
        p.setPaypalMail(isNull(pagamentoDTO.paypalMail) ? p.getPaypalMail() : pagamentoDTO.paypalMail);

        Checkers.pagamentoFieldsChecker(Pagamento.to(p));

        p.setUtente(utenteHelper.findById(pagamentoDTO.utente_id));
        p.setTipoMetodo(tipoMetodoHelper.findById(pagamentoDTO.tipoMetodo_id));

        return Pagamento.to(pagamentoCrud.saveAndFlush(p));
    }

    /**
     * deletes the Pagamento
     *
     * @param id of the {@link Pagamento Pagamento} to remove
     * @return HTTP status 200 if item is deleted otherwise 500
     * @throws PagamentoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws PagamentoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    @Transactional
    public Boolean removePagamento(Long id) {
        if (isNull(id))
            throw new PagamentoException(MISSING_PARAMETERS.name());

        if (!pagamentoCrud.existsById(id))
            throw new PagamentoException(CANNOT_FIND_ELEMENT.name());

        pagamentoCrud.deleteById(id);
        return pagamentoCrud.existsById(id);
    }

    /**
     * Returns all Pagamento
     *
     * @return List of {@link PagamentoDTO PagamentoDTO}
     */
    @Override
    public List<PagamentoDTO> getAll() {

        List<PagamentoDTO> result = new ArrayList<>();
        pagamentoCrud.findAll().forEach(pagamento -> result.add(Pagamento.to(pagamento)));
        return result;
    }

    /**
     * Returns Pagamento for provided id
     *
     * @return {@link PagamentoDTO PagamentoDTO}
     * @throws PagamentoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws PagamentoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public PagamentoDTO getById(Long id) {
        if (isNull(id))
            throw new PagamentoException(MISSING_PARAMETERS.name());

        if (!pagamentoCrud.existsById(id))
            throw new PagamentoException(CANNOT_FIND_ELEMENT.name());

        return Pagamento.to(pagamentoCrud.findById(id).orElseThrow(() -> new PagamentoException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Adds an Ordine to a relative Pagamento
     *
     * @param pagamentoId id of {@link Pagamento Pagamento}
     * @param ordineDTO   {@link OrdineDTO OrdineDTO} of Ordine to add
     * @return added Ordine
     * @throws PagamentoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws PagamentoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    @Transactional
    public Ordine addOrdineToPagamento(Long pagamentoId, OrdineDTO ordineDTO) {
        if (isNull(pagamentoId) || isNull(ordineDTO))
            throw new PagamentoException(MISSING_PARAMETERS.name());

        if (!pagamentoCrud.existsById(pagamentoId))
            throw new PagamentoException(CANNOT_FIND_ELEMENT.name());

        return ordineHelper.addOrdineToPagamento(pagamentoId, ordineDTO);
    }
}
