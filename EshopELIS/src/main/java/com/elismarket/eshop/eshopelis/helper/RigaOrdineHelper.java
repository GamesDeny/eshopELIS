package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.RigaOrdineException;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RigaOrdineHelper {
    @Autowired
    OrdineHelper ordineHelper;

    @Autowired
    ProdottoHelper prodottoHelper;

    @Autowired
    RigaOrdineCrud rigaOrdineCrud;

    public RigaOrdine addRigaOrdineToOrdine(Long ordineId, RigaOrdineDTO rigaOrdineDTO) {
        RigaOrdine r = RigaOrdine.of(rigaOrdineDTO);
        Ordine o = ordineHelper.findById(ordineId);

        r.setOrdine(o);
        return rigaOrdineCrud.saveAndFlush(r);
    }

    public RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO) {
        RigaOrdine r = RigaOrdine.of(rigaOrdineDTO);
        Prodotto p = prodottoHelper.findById(prodId);

        r.setProdotto(p);
        return rigaOrdineCrud.saveAndFlush(r);
    }

    public void linkRigheToOrdine(Long ordineId, List<Long> righeOrdine_id) {
        List<RigaOrdine> result = rigaOrdineCrud.findAllById(righeOrdine_id);
        if (result.isEmpty())
            throw new RigaOrdineException("List is empty");

        result.forEach(rigaOrdine -> rigaOrdine.setOrdine(ordineHelper.getOrdine(ordineId)));
        rigaOrdineCrud.saveAll(result);
    }

    public void linkRigheToProdotto(Long prodottoId, List<Long> righeOrdine_id) {
        List<RigaOrdine> result = rigaOrdineCrud.findAllById(righeOrdine_id);
        if (result.isEmpty())
            throw new RigaOrdineException("List is empty");

        result.forEach(riga -> riga.setProdotto(prodottoHelper.findById(prodottoId)));
        rigaOrdineCrud.saveAll(result);
    }

    public void saveAll(List<RigaOrdineDTO> righe) {
        if (righe.isEmpty())
            throw new RigaOrdineException("List is empty");

        List<RigaOrdine> result = new ArrayList<>();
        righe.forEach(riga -> result.add(RigaOrdine.of(riga)));

        rigaOrdineCrud.saveAll(result);
    }
}
