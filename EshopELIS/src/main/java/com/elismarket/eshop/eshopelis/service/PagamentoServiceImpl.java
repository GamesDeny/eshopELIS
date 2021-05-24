package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.exception.PagamentoException;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



/*
 *
 * Service class for CRUD operations and control of Payment methods
 *
 */

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    private PagamentoCrud pagamentoCrud;

    public PagamentoDTO addPagamento(PagamentoDTO pagamentoDTO) {
        try {
            pagamentoCrud.save(Pagamento.of(pagamentoDTO));
        } catch (Exception e) {
            throw new PagamentoException("Aggiornamento non riuscito, ricontrolla i dati inviati!");
        }
        return pagamentoCrud.findById(pagamentoDTO.id).isPresent() ? Pagamento.to(pagamentoCrud.findById(pagamentoDTO.id).get()) : null;
    }

    public Boolean removePagamento(Long id) {
        try {
            pagamentoCrud.deleteById(id);
        } catch (Exception e) {
            throw new PagamentoException("Cannot find Pagamento for provided item");
        }

        return pagamentoCrud.findById(id).isPresent();
    }

    public List<PagamentoDTO> getAll() {
        List<PagamentoDTO> result = new ArrayList<>();

        pagamentoCrud.findAll().forEach(pagamento -> result.add(Pagamento.to(pagamento)));

        return result;
    }

    public List<PagamentoDTO> getByContanti() {
        List<PagamentoDTO> result = new ArrayList<>();

        pagamentoCrud.findAllByContantiNotNull().forEach(pagamento -> result.add(Pagamento.to(pagamento)));

        return result;
    }

    public List<PagamentoDTO> getByPaypalMail() {
        List<PagamentoDTO> result = new ArrayList<>();

        pagamentoCrud.findAllByPaypalMailNotNull().forEach(pagamento -> result.add(Pagamento.to(pagamento)));

        return result;
    }

    public PagamentoDTO getById(Long id) {
        if (!pagamentoCrud.findById(id).isPresent())
            throw new PagamentoException("Not found");
        return Pagamento.to(pagamentoCrud.findById(id).get());
    }
}
