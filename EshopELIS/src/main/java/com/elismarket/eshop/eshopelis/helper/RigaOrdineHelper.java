package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Helper class for {@link RigaOrdine RigaORdine} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Component
@Transactional
public class RigaOrdineHelper {
    /**
     * @see OrdineHelper
     */
    @Autowired
    OrdineHelper ordineHelper;

    /**
     * @see ProdottoHelper
     */
    @Autowired
    ProdottoHelper prodottoHelper;

    /**
     * @see RigaOrdineHelper
     */
    @Autowired
    RigaOrdineCrud rigaOrdineCrud;

    /**
     * links the RigaOrdine to the relative Prodotto
     *
     * @param ordineId      id of the {@link Ordine Ordine} to retrieve
     * @param rigaOrdineDTO {@link RigaOrdineDTO RigaOrdineDTO}
     * @return RigaOrdine saved on DB
     */
    public RigaOrdine addRigaOrdineToOrdine(Long ordineId, RigaOrdineDTO rigaOrdineDTO) {
        RigaOrdine r = RigaOrdine.of(rigaOrdineDTO);
        Ordine o = ordineHelper.findById(ordineId);
        r.setOrdine(o);
        return rigaOrdineCrud.saveAndFlush(r);
    }

    /**
     * links the RigaOrdine to the relative Prodotto
     *
     * @param prodId        id of the {@link Prodotto Prodotto} to retrieve
     * @param rigaOrdineDTO {@link RigaOrdineDTO RigaOrdineDTO}
     * @return RigaOrdine saved on DB
     */
    public RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO) {
        RigaOrdine r = RigaOrdine.of(rigaOrdineDTO);
        Prodotto p = prodottoHelper.findById(prodId);
        r.setProdotto(p);
        return rigaOrdineCrud.saveAndFlush(r);
    }

    /**
     * links all the RigaOrdine to the relative order
     *
     * @param ordineId       id of the {@link Ordine Ordine} to retrieve
     * @param righeOrdine_id ids {@link RigaOrdine rigaOrdine}
     */
    public void linkRigheToOrdine(Long ordineId, List<Long> righeOrdine_id) {
        List<RigaOrdine> result = rigaOrdineCrud.findAllById(righeOrdine_id);
        result.forEach(rigaOrdine -> rigaOrdine.setOrdine(ordineHelper.findById(ordineId)));
        rigaOrdineCrud.saveAll(result);
    }

    /**
     * Links the Prodotto retrieved with prodottoId to every RigaOrdine in righeOrdine_id
     *
     * @param prodottoId     if of {@link Prodotto Prodotto} to link to the righeOrdine_id
     * @param righeOrdine_id ids {@link RigaOrdine rigaOrdine}
     */
    public void linkRigheToProdotto(Long prodottoId, List<Long> righeOrdine_id) {
        List<RigaOrdine> result = rigaOrdineCrud.findAllById(righeOrdine_id);
        result.forEach(riga -> riga.setProdotto(prodottoHelper.findById(prodottoId)));
        rigaOrdineCrud.saveAll(result);
    }

}
