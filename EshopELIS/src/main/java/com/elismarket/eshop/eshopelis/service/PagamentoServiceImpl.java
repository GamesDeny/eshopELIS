package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.exception.PagamentoException;
import com.elismarket.eshop.eshopelis.helper.OrdineHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



/*
 *
 * Service class for CRUD operations and control of Payment methods
 *
 */

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    private UtenteHelper utenteHelper;

    @Autowired
    private OrdineHelper ordineHelper;

    @Autowired
    private PagamentoCrud pagamentoCrud;

    @Override
    public PagamentoDTO addPagamento(PagamentoDTO pagamentoDTO) {

        Checkers.pagamentoFieldsChecker(pagamentoDTO);

        if (!Checkers.mailChecker(pagamentoDTO.paypalMail))
            throw new PagamentoException("Paypal mail not valid");

        pagamentoCrud.save(Pagamento.of(pagamentoDTO));

        if (!pagamentoCrud.existsByDescrizione(pagamentoDTO.descrizione))
            throw new PagamentoException("Aggiornamento non riuscito, ricontrolla i dati inviati!");

        return Pagamento.to(pagamentoCrud.findById(pagamentoDTO.id).orElseThrow(() -> new PagamentoException("Not found")));
    }

    @Override
    public PagamentoDTO updatePagamento(Long id, PagamentoDTO pagamentoDTO) {
        if (!Objects.isNull(pagamentoDTO.paypalMail) && !Checkers.mailChecker(pagamentoDTO.paypalMail))
            throw new PagamentoException("Paypal mail not valid");

        Pagamento p = pagamentoCrud.findById(id).orElseThrow(() -> new PagamentoException("Not found"));

        pagamentoDTO.id = id;
        pagamentoDTO.descrizione = Objects.isNull(pagamentoDTO.descrizione) ? p.getDescrizione() : pagamentoDTO.descrizione;
        pagamentoDTO.contanti = Objects.isNull(pagamentoDTO.contanti) ? p.getContanti() : pagamentoDTO.contanti;
        pagamentoDTO.paypalMail = Objects.isNull(pagamentoDTO.paypalMail) ? p.getPaypalMail() : pagamentoDTO.paypalMail;
        pagamentoDTO.tipo = Objects.isNull(pagamentoDTO.tipo) ? p.getTipo() : pagamentoDTO.tipo;

        Checkers.pagamentoFieldsChecker(pagamentoDTO);

        return Pagamento.to(pagamentoCrud.findById(id).orElseThrow(() -> new PagamentoException("Not found")));
    }

    @Override
    public Boolean removePagamento(Long id) {

        pagamentoCrud.deleteById(id);

        if (pagamentoCrud.existsById(id))
            throw new PagamentoException("Cannot find Pagamento for provided item");

        return pagamentoCrud.existsById(id);
    }

    @Override
    public List<PagamentoDTO> getAll() {
        List<PagamentoDTO> result = new ArrayList<>();

        pagamentoCrud.findAll().forEach(pagamento -> result.add(Pagamento.to(pagamento)));

        return result;
    }

    @Override
    public List<PagamentoDTO> getByContanti() {
        List<PagamentoDTO> result = new ArrayList<>();

        pagamentoCrud.findAllByContantiNotNull().forEach(pagamento -> result.add(Pagamento.to(pagamento)));

        return result;
    }

    @Override
    public List<PagamentoDTO> getByPaypalMail() {
        List<PagamentoDTO> result = new ArrayList<>();

        pagamentoCrud.findAllByPaypalMailNotNull().forEach(pagamento -> result.add(Pagamento.to(pagamento)));

        return result;
    }

    @Override
    public PagamentoDTO getById(Long id) {
        if (!pagamentoCrud.existsById(id))
            throw new PagamentoException("Not found");
        return Pagamento.to(pagamentoCrud.findById(id).orElseThrow(() -> new PagamentoException("Not found")));
    }

    @Override
    public Ordine addOrdineToPagamento(Long pagamentoId, OrdineDTO ordineDTO) {
        if (!pagamentoCrud.existsById(pagamentoId))
            throw new PagamentoException("Not found");
        return ordineHelper.addOrdineToPagamento(pagamentoId, ordineDTO);
    }
}
