package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;

import java.util.List;

public interface PropostaService {
    PropostaDTO addProposta(Long userId, PropostaDTO propostaDTO);

    PropostaDTO updateProposta(Long id, PropostaDTO propostaDTO);

    Boolean removeProposta(Long id);

    List<PropostaDTO> findAllByIsAccettato(Boolean isAccettato);

    List<PropostaDTO> findAllByUtente(Long id);

    PropostaDTO findById(Long id);

    List<PropostaDTO> findAll();
}
