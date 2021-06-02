package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
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



/*
 *
 * Service class for CRUD operations and control of Payment methods
 *
 */

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    PagamentoCrud pagamentoCrud;

    @Autowired
    UtenteHelper utenteHelper;

    @Autowired
    OrdineHelper ordineHelper;

    @Autowired
    TipoMetodoHelper tipoMetodoHelper;

    @Override
    public PagamentoDTO addPagamento(PagamentoDTO pagamentoDTO) {
        Checkers.pagamentoFieldsChecker(pagamentoDTO);
        Pagamento p = Pagamento.of(pagamentoDTO);
        p.setTipoMetodo(tipoMetodoHelper.findById(pagamentoDTO.tipoMetodo_id));
        p.setUtente(utenteHelper.findById(pagamentoDTO.utente_id));

        if (!Checkers.mailChecker(pagamentoDTO.paypalMail))
            throw new PagamentoException(WRONG_PARAMETERS.name());

        return Pagamento.to(pagamentoCrud.saveAndFlush(Pagamento.of(pagamentoDTO)));
    }

    @Override
    public PagamentoDTO updatePagamento(Long pagamentoID, PagamentoDTO pagamentoDTO) {
        if (!Objects.isNull(pagamentoDTO.paypalMail) && !Checkers.mailChecker(pagamentoDTO.paypalMail))
            throw new PagamentoException(WRONG_PARAMETERS.name());

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

    @Override
    public Boolean removePagamento(Long id) {
        if (!pagamentoCrud.existsById(id))
            throw new PagamentoException(CANNOT_FIND_ELEMENT.name());

        pagamentoCrud.deleteById(id);
        return !pagamentoCrud.existsById(id);
    }

    @Override
    public List<PagamentoDTO> getAll() {
        if (pagamentoCrud.findAll().isEmpty())
            throw new PagamentoException(LIST_IS_EMPTY.name());

        List<PagamentoDTO> result = new ArrayList<>();
        pagamentoCrud.findAll().forEach(pagamento -> result.add(Pagamento.to(pagamento)));
        return result;
    }

    @Override
    public List<PagamentoDTO> getByContanti() {
        if (pagamentoCrud.findAllByContantiNotNull().isEmpty())
            throw new PagamentoException(LIST_IS_EMPTY.name());

        List<PagamentoDTO> result = new ArrayList<>();
        pagamentoCrud.findAllByContantiNotNull().forEach(pagamento -> result.add(Pagamento.to(pagamento)));
        return result;
    }

    @Override
    public List<PagamentoDTO> getByPaypalMail() {
        if (pagamentoCrud.findAllByPaypalMailNotNull().isEmpty())
            throw new PagamentoException(LIST_IS_EMPTY.name());

        List<PagamentoDTO> result = new ArrayList<>();
        pagamentoCrud.findAllByPaypalMailNotNull().forEach(pagamento -> result.add(Pagamento.to(pagamento)));
        return result;
    }

    @Override
    public PagamentoDTO getById(Long id) {
        if (!pagamentoCrud.existsById(id))
            throw new PagamentoException(CANNOT_FIND_ELEMENT.name());

        return Pagamento.to(pagamentoCrud.findById(id).orElseThrow(() -> new PagamentoException(CANNOT_FIND_ELEMENT.name())));
    }

    @Override
    public Ordine addOrdineToPagamento(Long pagamentoId, OrdineDTO ordineDTO) {
        if (!pagamentoCrud.existsById(pagamentoId))
            throw new PagamentoException(CANNOT_FIND_ELEMENT.name());
        return ordineHelper.addOrdineToPagamento(pagamentoId, ordineDTO);
    }
}
