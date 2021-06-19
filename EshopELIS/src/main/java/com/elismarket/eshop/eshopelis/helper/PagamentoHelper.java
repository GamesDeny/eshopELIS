package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.PagamentoException;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.model.TipoMetodo;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;

/**
 * Helper class for {@link Pagamento Pagamento} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Component
public class PagamentoHelper {

    /**
     * @see PagamentoCrud
     */
    @Autowired
    PagamentoCrud pagamentoCrud;

    /**
     * @see OrdineHelper
     */
    @Autowired
    OrdineHelper ordineHelper;

    /**
     * @see UtenteHelper
     */
    @Autowired
    UtenteHelper utenteHelper;

    /**
     * @see TipoMetodoHelper
     */
    @Autowired
    TipoMetodoHelper tipoMetodoHelper;

    /**
     * links Pagamento to the relative Utente
     *
     * @param utenteId     id for the {@link Utente Utente} to link to Pagamento
     * @param pagamentoDTO {@link PagamentoDTO PagamentoDTO} to add to user
     * @return {@link Pagamento Pagamento} added to DB
     */
    public Pagamento addPagamentoToUser(Long utenteId, PagamentoDTO pagamentoDTO) {
        Checkers.pagamentoFieldsChecker(pagamentoDTO);

        Pagamento p = Pagamento.of(pagamentoDTO);
        Utente u = utenteHelper.findById(utenteId);

        p.setUtente(u);
        return pagamentoCrud.saveAndFlush(p);
    }

    /**
     * retrieves a Pagamento for the provided id
     *
     * @param pagamento_id is of the {@link Pagamento Pagamento} to retrieve
     * @return retrieved Pagamento
     * @throws PagamentoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT} message
     */
    public Pagamento findById(Long pagamento_id) {
        return pagamentoCrud.findById(pagamento_id).orElseThrow(() -> new PagamentoException(CANNOT_FIND_ELEMENT.name()));
    }

    /**
     * links Utente to the relatives Pagamenti
     *
     * @param utenteId     id for the {@link Utente Utente} to link to Pagamenti
     * @param pagamenti_id ids for the {@link Pagamento Pagamento} to be linked to the Utente
     */
    public void linkUtenteToPagamenti(Long utenteId, List<Long> pagamenti_id) {
        List<Pagamento> result = pagamentoCrud.findAllById(pagamenti_id);
        result.forEach(pagamento -> pagamento.setUtente(utenteHelper.findById(utenteId)));
        pagamentoCrud.saveAll(result);
    }

    /**
     * links TipoMetodo to the relatives Pagamenti
     *
     * @param tipoPagamentoId id for the {@link TipoMetodo TipoMetodo} to link to Pagamenti
     * @param pagamenti_id    ids for the {@link Pagamento Pagamento} to be linked to the Metodo
     */
    public void linkMetodoToPagamento(Long tipoPagamentoId, List<Long> pagamenti_id) {
        List<Pagamento> result = pagamentoCrud.findAllById(pagamenti_id);
        result.forEach(pagamento -> pagamento.setTipoMetodo(tipoMetodoHelper.findById(tipoPagamentoId)));
        pagamentoCrud.saveAll(result);
    }

}
