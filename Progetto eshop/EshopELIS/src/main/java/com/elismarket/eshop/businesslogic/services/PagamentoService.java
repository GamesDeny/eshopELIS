package com.elismarket.eshop.businesslogic.services;

import com.elismarket.eshop.businesslogic.crudrepos.PagamentoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    PagamentoCrud pagamentoCrud;
}
