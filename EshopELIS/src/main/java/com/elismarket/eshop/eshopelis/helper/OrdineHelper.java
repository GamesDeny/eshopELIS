package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.OrdineException;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;

/**
 * Helper class for {@link Ordine Ordine} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Component
public class OrdineHelper {
    /**
     * @see OrdineCrud
     */
    @Autowired
    OrdineCrud ordineCrud;

    /**
     * @see RigaOrdineHelper
     */
    @Autowired
    RigaOrdineHelper rigaOrdineHelper;

    /**
     * @see PagamentoHelper
     */
    @Autowired
    PagamentoHelper pagamentoHelper;

    /**
     * links an Ordine to the relative Pagamento
     *
     * @param pagamentoId id for the {@link Pagamento Pagamento} to link to the Ordine
     * @param ordineDTO   {@link OrdineDTO OrdineDTO} to link to the Pagamento
     * @return {@link Ordine Ordine} for the provided DTO
     */
    public Ordine addOrdineToPagamento(Long pagamentoId, OrdineDTO ordineDTO) {
        Ordine o = Ordine.of(ordineDTO);
        Pagamento p = pagamentoHelper.findById(pagamentoId);
        o.setPagamento(p);
        o.setUtente(p.getUtente());
        return ordineCrud.saveAndFlush(o);
    }

    /**
     * Returns the Ordine for the provided id
     *
     * @param ordineId id for the {@link Ordine Ordine} to retrieve
     * @return {@link Ordine Ordine} for the provided id
     * @throws OrdineException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     */
    public Ordine findById(Long ordineId) {
        return ordineCrud.findById(ordineId).orElseThrow(() -> new OrdineException(CANNOT_FIND_ELEMENT.name()));
    }

    /**
     * Returns all evaded Ordine
     *
     * @return list of OrdineDTO evasi
     */
    public List<OrdineDTO> getOrdiniEvasi() {
        List<OrdineDTO> ordini = new ArrayList<>();
        ordineCrud.findAllByEvaso(Boolean.TRUE).forEach(ordine -> ordini.add(Ordine.to(ordine)));
        return ordini;
    }
}
