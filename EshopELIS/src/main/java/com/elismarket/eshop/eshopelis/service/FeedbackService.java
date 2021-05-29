package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;

import java.util.List;

public interface FeedbackService {
    FeedbackDTO addFeedback(FeedbackDTO feedbackDTO);

    FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO);

    Boolean deleteFeedback(Long id);

    FeedbackDTO getById(Long id);

    List<FeedbackDTO> getAll();


}
