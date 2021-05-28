package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoHelper {
    @Autowired
    private OrdineCrud ordineCrud;

    @Autowired
    private PagamentoCrud pagamentoCrud;

    @Autowired
    private UtenteCrud utenteCrud;

}
