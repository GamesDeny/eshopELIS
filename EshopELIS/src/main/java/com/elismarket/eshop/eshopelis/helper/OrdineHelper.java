package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.exception.OrdineException;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.LIST_IS_EMPTY;

@Component
public class OrdineHelper {
    @Autowired
    OrdineCrud ordineCrud;

    @Autowired
    RigaOrdineCrud rigaOrdineCrud;

    @Autowired
    PagamentoHelper pagamentoHelper;

    public Ordine addOrdineToPagamento(Long pagamentoId, OrdineDTO ordineDTO) {
        Ordine o = Ordine.of(ordineDTO);
        Pagamento p = pagamentoHelper.findById(pagamentoId);

        o.setPagamento(p);
        return ordineCrud.saveAndFlush(o);
    }

    public Ordine getOrdine(Long ordineId) {
        return ordineCrud.findById(ordineId).orElseThrow(() -> new OrdineException(CANNOT_FIND_ELEMENT.name()));
    }

    public Ordine findById(Long ordineId) {
        return ordineCrud.findById(ordineId).orElseThrow(() -> new OrdineException(CANNOT_FIND_ELEMENT.name()));
    }

    public void linkPagamentoToOrdine(Long pagamentoID, List<Long> ordini_id) {
        List<Ordine> result = ordineCrud.findAllById(ordini_id);

        if (result.isEmpty())
            throw new OrdineException(LIST_IS_EMPTY.name());

        result.forEach(ordine -> ordine.setPagamento(pagamentoHelper.findById(pagamentoID)));
        ordineCrud.saveAll(result);
    }
}
