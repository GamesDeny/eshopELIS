package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;

import java.util.List;

public interface PropostaService {
    List<PropostaDTO> findAllByIsAccettato(Boolean isAccettato);

    List<PropostaDTO> findAllByUtente(Long id);

    List<PropostaDTO> findAllByUtente(UtenteDTO utente);

    Boolean removeProposta(Long id);

    Boolean addProposta(PropostaDTO propostaDTO);

    PropostaDTO getById(Long id);

}
