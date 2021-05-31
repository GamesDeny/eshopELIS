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
        return ordineCrud.findById(ordineId).orElseThrow(() -> new OrdineException("Cannot find Ordine"));
    }

    public Ordine findById(Long ordineId) {
        return ordineCrud.findById(ordineId).orElseThrow(() -> new OrdineException("Cannot find Ordine"));
    }

    public void linkPagamentoToOrdine(Long pagamentoID, List<Long> ordini_id) {
        List<Ordine> result = ordineCrud.findAllById(ordini_id);

        if (result.isEmpty())
            throw new OrdineException("List is empty");

        result.forEach(ordine -> ordine.setPagamento(pagamentoHelper.findById(pagamentoID)));
        ordineCrud.saveAll(result);
    }
}
