package com.elismarket.eshop.services;

import com.elismarket.eshop.crudrepos.PagamentoCrud;
import com.elismarket.eshop.interfaces.Pagamento;
import com.elismarket.eshop.model.PagamentoImpl;
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


    public List<PagamentoImpl> getAll() {
        return (List<PagamentoImpl>) pagamentoCrud.findAll();
    }

    public List<Pagamento> getByTipo(String tipo) {
        return pagamentoCrud.findAllByTipo(tipo);
    }

    public List<Pagamento> getByContanti() {
        return pagamentoCrud.findAllByContantiNotNull();
    }

    public List<Pagamento> getByPaypalMail() {
        return pagamentoCrud.findAllByPaypalMailNotNull();
    }
}
