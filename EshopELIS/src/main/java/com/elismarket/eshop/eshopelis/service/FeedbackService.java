package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;

import java.util.List;

public interface FeedbackService {
    FeedbackDTO saveFeedback(FeedbackDTO feedback);

    Boolean deleteFeedback(FeedbackDTO feedbackDTO);

    List<FeedbackDTO> findAllByUtente(UtenteDTO utente);

    FeedbackDTO getById(Long id);

    List<FeedbackDTO> getAll();


}
