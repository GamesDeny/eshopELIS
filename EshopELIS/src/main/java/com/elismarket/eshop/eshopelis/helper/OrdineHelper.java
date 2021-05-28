package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrdineHelper {
    @Autowired
    private OrdineCrud ordineCrud;

    @Autowired
    private RigaOrdineCrud rigaOrdineCrud;

    @Autowired
    private PagamentoCrud pagamentoCrud;
}