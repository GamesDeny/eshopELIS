package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PagamentoHelper {
    @Autowired
    private OrdineCrud ordineCrud;

    @Autowired
    private PagamentoCrud pagamentoCrud;

    @Autowired
    private UtenteCrud utenteCrud;

    public Pagamento addPagamentoToUser(Long userId, PagamentoDTO pagamentoDTO) {
        Pagamento p = Pagamento.of(pagamentoDTO);
        Utente u = utenteCrud.findById(userId).orElseThrow(() -> new UtenteException("Cannot find Utente"));

        p.setUtente(u);
        return p;
    }
}
