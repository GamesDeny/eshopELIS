package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.service.PropostaServiceImpl;

import java.util.List;

/**
 * Service layer for the {@link Proposta Proposta} entity
 * For implementation of methods see {@link PropostaServiceImpl PropostaServiceImpl}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface PropostaService {
    PropostaDTO addProposta(Long userId, PropostaDTO propostaDTO);

    PropostaDTO updateProposta(Long id, PropostaDTO propostaDTO);

    Boolean removeProposta(Long id);

    List<PropostaDTO> findAllByIsAccettato(Boolean isAccettato);

    List<PropostaDTO> findAllByUtente(Long id);

    PropostaDTO findById(Long id);

    List<PropostaDTO> findAll();
}
