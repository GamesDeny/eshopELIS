package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaHelper {
    @Autowired
    private PropostaCrud propostaCrud;

    @Autowired
    private UtenteCrud utenteCrud;

    public Proposta addPropostaToUser(Long userId, PropostaDTO propostaDTO) {
        Utente u = utenteCrud.findById(userId).orElseThrow(() -> new UtenteException("Cannot find user"));

        Proposta p = Proposta.of(propostaDTO);
        p.setUtente(u);

        return p;
    }
}
