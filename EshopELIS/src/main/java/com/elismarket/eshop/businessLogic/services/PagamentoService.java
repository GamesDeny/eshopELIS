package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.cruderepos.PagamentoCrud;
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
    PagamentoCrud pagamentoCrud;


    public Iterable<PagamentoImpl> getAll() {
        return pagamentoCrud.findAll();
    }

    public List<Pagamento> getByContanti() {
        return pagamentoCrud.findAllByContantiNotNull();
    }

    public List<Pagamento> getByPaypalMail() {
        return pagamentoCrud.findAllByPaypalMailNotNull();
    }
}
