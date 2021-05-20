package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;

import java.util.List;

public interface FeedbackService {
    Boolean saveFeedback(FeedbackDTO feedback);

    List<FeedbackDTO> findAllByUtente(UtenteDTO utente);

    void deleteFeedback(FeedbackDTO feedbackDTO);

    FeedbackDTO getById(Long id);

    List<FeedbackDTO> getAll();


}
