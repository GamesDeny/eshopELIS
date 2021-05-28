package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdottoHelper {
    @Autowired
    private ProdottoCrud prodottoCrud;

    @Autowired
    private UtenteCrud utenteCrud;

    @Autowired
    private RigaOrdineCrud rigaOrdineCrud;
}
