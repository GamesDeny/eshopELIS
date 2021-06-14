package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.model.*;
import com.elismarket.eshop.eshopelis.service.UtenteServiceImpl;

import java.util.List;

/**
 * Service layer for the {@link Utente Utente} entity
 * For implementation of methods see {@link UtenteServiceImpl UtenteServiceImpl}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface UtenteService {
    UtenteDTO addUtente(UtenteDTO utenteDTO);

    UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO);

    Boolean removeUtente(Long id);

    List<UtenteDTO> getAll(String findby);

    UtenteDTO getByMail(String mail);

    UtenteDTO getByUser(String username);

    UtenteDTO getById(Long id);

    UtenteDTO getLoginUtente(String username, String password);

    Boolean getLogout(Long userId);

    Feedback addFeedbackToUser(Long userId, FeedbackDTO feedbackDTO);

    Proposta addPropostaToUser(Long userId, PropostaDTO propostaDTO);

    Prodotto addProdottoToUser(Long userId, ProdottoDTO prodottoDTO);

    Pagamento addPagamentoToUser(Long userId, PagamentoDTO pagamentoDTO);
}
