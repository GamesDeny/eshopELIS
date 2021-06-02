package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.Proposta;

import java.util.List;

public interface UtenteService {
    UtenteDTO addUtente(UtenteDTO utenteDTO);

    UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO);

    Boolean removeUtente(Long id);

    List<UtenteDTO> getAll(String findby);

    UtenteDTO getByMail(String mail);

    UtenteDTO getByUser(String username);

    UtenteDTO getById(Long id);

    UtenteDTO getBySigla(Integer siglaResidenza);

    UtenteDTO getLoginUtente(String username, String password);

    Feedback addFeedbackToUser(Long userId, FeedbackDTO feedbackDTO);

    Proposta addPropostaToUser(Long userId, PropostaDTO propostaDTO);

    Prodotto addProdottoToUser(Long userId, ProdottoDTO prodottoDTO);

    Pagamento addPagamentoToUser(Long userId, PagamentoDTO pagamentoDTO);
}
