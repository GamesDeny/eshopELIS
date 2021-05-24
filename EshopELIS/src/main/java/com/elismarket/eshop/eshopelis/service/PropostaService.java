package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;

import java.util.List;

public interface PropostaService {
    PropostaDTO addProposta(PropostaDTO propostaDTO);

    Boolean removeProposta(Long id);

    List<PropostaDTO> findAllByIsAccettato(Boolean isAccettato);

    List<PropostaDTO> findAllByUtente(Long id);

    List<PropostaDTO> findAllByUtente(UtenteDTO utente);

    PropostaDTO getById(Long id);

}
