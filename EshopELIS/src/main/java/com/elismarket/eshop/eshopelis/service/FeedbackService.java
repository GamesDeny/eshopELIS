package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;

import java.util.List;

public interface FeedbackService {
    FeedbackDTO saveFeedback(FeedbackDTO feedbackDTO);

    FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO);

    Boolean deleteFeedback(FeedbackDTO feedbackDTO);

    FeedbackDTO getById(Long id);

    List<FeedbackDTO> getAll();


}
