package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RigaOrdineHelper {
    @Autowired
    private OrdineCrud ordineCrud;

    @Autowired
    private ProdottoCrud prodottoCrud;

    @Autowired
    private RigaOrdineCrud rigaOrdineCrud;
}
