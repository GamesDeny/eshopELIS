package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.model.Feedback;

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
}
