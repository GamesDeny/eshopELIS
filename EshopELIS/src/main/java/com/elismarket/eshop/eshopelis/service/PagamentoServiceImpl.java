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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.*;

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
    public PagamentoDTO addPagamento(PagamentoDTO pagamentoDTO) {
        Checkers.pagamentoFieldsChecker(pagamentoDTO);
        Pagamento p = Pagamento.of(pagamentoDTO);
        p.setTipoMetodo(tipoMetodoHelper.findById(pagamentoDTO.tipoMetodo_id));
        p.setUtente(utenteHelper.findById(pagamentoDTO.utente_id));

        if (!(Objects.isNull(pagamentoDTO.paypalMail) || Checkers.mailChecker(pagamentoDTO.paypalMail)))
            throw new PagamentoException(INVALID_MAIL.name());

        return Pagamento.to(pagamentoCrud.saveAndFlush(p));
    }

    /**
     * Updates an existing Pagamento
     *
     * @param pagamentoID  of the {@link Pagamento Pagamento} to update
     * @param pagamentoDTO {@link PagamentoDTO PagamentoDTO} of the Pagamento to update
     * @return DTO of updated item
     * @throws PagamentoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws PagamentoException with {@link ExceptionPhrases#INVALID_MAIL INVALID_MAIL} message
     */
    @Override
    public PagamentoDTO updatePagamento(Long pagamentoID, PagamentoDTO pagamentoDTO) {
        if (!Objects.isNull(pagamentoDTO.paypalMail) && !Checkers.mailChecker(pagamentoDTO.paypalMail))
            throw new PagamentoException(INVALID_MAIL.name());

        Pagamento p = pagamentoCrud.findById(pagamentoID).orElseThrow(() -> new PagamentoException(CANNOT_FIND_ELEMENT.name()));

        pagamentoDTO.id = pagamentoID;
        pagamentoDTO.descrizione = Objects.isNull(pagamentoDTO.descrizione) ? p.getDescrizione() : pagamentoDTO.descrizione;
        pagamentoDTO.contanti = Objects.isNull(pagamentoDTO.contanti) ? p.getContanti() : pagamentoDTO.contanti;
        pagamentoDTO.paypalMail = Objects.isNull(pagamentoDTO.paypalMail) ? p.getPaypalMail() : pagamentoDTO.paypalMail;

        Checkers.pagamentoFieldsChecker(pagamentoDTO);

        Pagamento save = Pagamento.of(pagamentoDTO);
        save.setUtente(utenteHelper.findById(pagamentoDTO.utente_id));
        save.setTipoMetodo(tipoMetodoHelper.findById(pagamentoDTO.tipoMetodo_id));
        save.setOrdine(ordineHelper.findById(pagamentoDTO.ordine_id));

        pagamentoCrud.saveAndFlush(save);

        return Pagamento.to(pagamentoCrud.findById(pagamentoID).orElseThrow(() -> new PagamentoException(CANNOT_FIND_ELEMENT.name())));
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
    public Boolean removePagamento(Long id) {
        if (Objects.isNull(id))
            throw new PagamentoException(MISSING_PARAMETERS.name());

        if (!pagamentoCrud.existsById(id))
            throw new PagamentoException(CANNOT_FIND_ELEMENT.name());

        pagamentoCrud.deleteById(id);
        return !pagamentoCrud.existsById(id);
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
        if (Objects.isNull(id))
            throw new PagamentoException(MISSING_PARAMETERS.name());

        if (!pagamentoCrud.existsById(id))
            throw new PagamentoException(CANNOT_FIND_ELEMENT.name());

        return Pagamento.to(pagamentoCrud.findById(id).orElseThrow(() -> new PagamentoException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Returns all Pagamento where contanti != null and greater than 0
     *
     * @return List {@link PagamentoDTO PagamentoDTO}
     */
    @Override
    public List<PagamentoDTO> getByContanti() {

        List<PagamentoDTO> result = new ArrayList<>();
        pagamentoCrud.findAllByContantiNotNullAndContantiGreaterThanEqual(0f).forEach(pagamento -> result.add(Pagamento.to(pagamento)));
        return result;
    }

    /**
     * Returns all Pagamento where PaypalMail != null
     *
     * @return List {@link PagamentoDTO PagamentoDTO}
     */
    @Override
    public List<PagamentoDTO> getByPaypalMail() {

        List<PagamentoDTO> result = new ArrayList<>();
        pagamentoCrud.findAllByPaypalMailNotNull().forEach(pagamento -> result.add(Pagamento.to(pagamento)));
        return result;
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
    public Ordine addOrdineToPagamento(Long pagamentoId, OrdineDTO ordineDTO) {
        if (Objects.isNull(pagamentoId) || Objects.isNull(ordineDTO))
            throw new PagamentoException(MISSING_PARAMETERS.name());

        if (!pagamentoCrud.existsById(pagamentoId))
            throw new PagamentoException(CANNOT_FIND_ELEMENT.name());
        return ordineHelper.addOrdineToPagamento(pagamentoId, ordineDTO);
    }
}
