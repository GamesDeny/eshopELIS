package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.exception.PropostaException;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.LIST_IS_EMPTY;

@Component
public class PropostaHelper {
    @Autowired
    PropostaCrud propostaCrud;

    @Autowired
    UtenteHelper utenteHelper;

    public Proposta addPropostaToUser(Long userId, PropostaDTO propostaDTO) {
        Utente u = utenteHelper.findById(userId);
        Proposta p = Proposta.of(propostaDTO);
        p.setUtente(u);

        return propostaCrud.saveAndFlush(p);
    }

    public void linkUtenteToProposte(Long utenteId, List<Long> proposte_id) {
        List<Proposta> result = propostaCrud.findAllById(proposte_id);
        if (result.isEmpty())
            throw new PropostaException(LIST_IS_EMPTY.name());

        result.forEach(proposta -> proposta.setUtente(utenteHelper.findById(utenteId)));
        propostaCrud.saveAll(result);
    }
}
