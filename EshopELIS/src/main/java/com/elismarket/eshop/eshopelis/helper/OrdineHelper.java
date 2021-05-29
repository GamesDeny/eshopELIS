package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.exception.PagamentoException;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Pagamento;
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

    public Ordine addOrdineToPagamento(Long pagamentoId, OrdineDTO ordineDTO) {
        Ordine o = Ordine.of(ordineDTO);
        Pagamento p = pagamentoCrud.findById(pagamentoId).orElseThrow(() -> new PagamentoException("Cannot find Pagamento"));

        o.setPagamento(p);
        return o;
    }
}
