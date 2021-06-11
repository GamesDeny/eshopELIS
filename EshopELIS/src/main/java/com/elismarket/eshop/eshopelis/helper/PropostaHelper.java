package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Helper class for {@link Proposta Proposta} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Component
public class PropostaHelper {
    /**
     * @see PropostaCrud
     */
    @Autowired
    PropostaCrud propostaCrud;

    /**
     * @see UtenteHelper
     */
    @Autowired
    UtenteHelper utenteHelper;

    /**
     * add the Proposta to the relative User
     *
     * @param userId      id of the {@link Utente Utente} to retrieve
     * @param propostaDTO {@link PropostaDTO PropostaDTO}
     * @return {@link Proposta Proposta} added to DB
     */
    public Proposta addPropostaToUser(Long userId, PropostaDTO propostaDTO) {
        Utente u = utenteHelper.findById(userId);
        Proposta p = Proposta.of(propostaDTO);
        p.setUtente(u);

        return propostaCrud.saveAndFlush(p);
    }

    /**
     * links all the Proposta to the relative Utente
     *
     * @param utenteId    id of the {@link Utente Utente} to retrieve
     * @param proposte_id id of the {@link Proposta Proposta} to retrieve
     */
    public void linkUtenteToProposte(Long utenteId, List<Long> proposte_id) {
        List<Proposta> result = propostaCrud.findAllById(proposte_id);
        result.forEach(proposta -> proposta.setUtente(utenteHelper.findById(utenteId)));
        propostaCrud.saveAll(result);
    }
}
