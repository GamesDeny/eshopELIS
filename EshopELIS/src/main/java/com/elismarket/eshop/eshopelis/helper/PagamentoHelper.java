package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.exception.PagamentoException;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.LIST_IS_EMPTY;

@Component
public class PagamentoHelper {
    @Autowired
    OrdineHelper ordineHelper;

    @Autowired
    PagamentoCrud pagamentoCrud;

    @Autowired
    UtenteHelper utenteHelper;

    @Autowired
    TipoMetodoHelper tipoMetodoHelper;

    public Pagamento addPagamentoToUser(Long userId, PagamentoDTO pagamentoDTO) {
        Pagamento p = Pagamento.of(pagamentoDTO);
        Utente u = utenteHelper.findById(userId);

        p.setUtente(u);
        return pagamentoCrud.saveAndFlush(p);
    }

    public Pagamento findById(Long pagamento_id) {
        return pagamentoCrud.findById(pagamento_id).orElseThrow(() -> new PagamentoException(CANNOT_FIND_ELEMENT.name()));
    }

    public void linkUtenteToPagamenti(Long utenteId, List<Long> pagamenti_id) {
        List<Pagamento> result = pagamentoCrud.findAllById(pagamenti_id);
        if (result.isEmpty())
            throw new PagamentoException(LIST_IS_EMPTY.name());

        result.forEach(pagamento -> pagamento.setUtente(utenteHelper.findById(utenteId)));
        pagamentoCrud.saveAll(result);
    }

    public void linkMetodoToPagamento(Long tipoPagamentoId, List<Long> pagamenti_id) {
        List<Pagamento> result = pagamentoCrud.findAllById(pagamenti_id);
        if (result.isEmpty())
            throw new PagamentoException(LIST_IS_EMPTY.name());

        result.forEach(pagamento -> pagamento.setTipoMetodo(tipoMetodoHelper.findById(tipoPagamentoId)));
        pagamentoCrud.saveAll(result);
    }
}
