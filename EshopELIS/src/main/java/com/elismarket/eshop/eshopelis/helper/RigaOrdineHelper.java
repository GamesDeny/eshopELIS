package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.OrdineException;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
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

    public RigaOrdine addRigaOrdineToOrdine(Long ordineId, RigaOrdineDTO rigaOrdineDTO) {
        RigaOrdine r = RigaOrdine.of(rigaOrdineDTO);
        Ordine o = ordineCrud.findById(ordineId).orElseThrow(() -> new OrdineException("Ordine not found"));

        r.setOrdine(o);
        return r;
    }

    public RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO) {
        RigaOrdine r = RigaOrdine.of(rigaOrdineDTO);
        Prodotto p = prodottoCrud.findById(prodId).orElseThrow(() -> new ProdottoException("Prodotto not found"));

        r.setProdotto(p);
        return r;
    }
}
