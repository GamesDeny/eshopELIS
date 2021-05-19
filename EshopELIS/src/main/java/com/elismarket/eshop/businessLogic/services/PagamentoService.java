package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.cruderepos.PagamentoCrud;
import com.elismarket.eshop.customExceptions.PagamentoException;
import com.elismarket.eshop.model.dto.PagamentoDTO;
import com.elismarket.eshop.model.entities.PagamentoImpl;
import com.elismarket.eshop.model.interfaces.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/*
 *
 * Service class for CRUD operations and control of Payment methods
 *
 */

@Service
public class PagamentoService {

    @Autowired
    private PagamentoCrud pagamentoCrud;

    public Iterable<PagamentoImpl> getAll() {
        return pagamentoCrud.findAll();
    }

    public List<Pagamento> getByContanti() {
        return pagamentoCrud.findAllByContantiNotNull();
    }

    public List<Pagamento> getByPaypalMail() {
        return pagamentoCrud.findAllByPaypalMailNotNull();
    }

    public Boolean addPagamento(PagamentoDTO pagamentoDTO) {
        try {
            pagamentoCrud.save(PagamentoImpl.of(pagamentoDTO));
            return true;
        } catch (Exception e) {
//            throw new PagamentoException("Aggiornamento non riuscito, ricontrolla i dati inviati!");
        }
        return false;
    }

    public void removePagamento(Long id) {
        try {
            pagamentoCrud.deleteById(id);
        } catch (Exception e) {
            throw new PagamentoException("Cannot find Pagamento for provided item");
        }
    }

    public Pagamento getById(Long id) {
        if (!pagamentoCrud.findById(id).isPresent())
            throw new PagamentoException("Not found");
        return pagamentoCrud.findById(id).get();
    }
}
