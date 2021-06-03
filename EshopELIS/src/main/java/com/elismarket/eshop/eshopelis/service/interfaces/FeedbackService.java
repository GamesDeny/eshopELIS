package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.service.FeedbackServiceImpl;

import java.util.List;

/**
 * Service layer for the {@link Feedback Feedback} entity
 * For implementation of methods see {@link FeedbackServiceImpl FeedbackServiceImpl}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface FeedbackService {
    FeedbackDTO addFeedback(FeedbackDTO feedbackDTO);

    FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO);

    Boolean deleteFeedback(Long id);

    FeedbackDTO getById(Long id);

    List<FeedbackDTO> getAll();

    List<FeedbackDTO> getAllByUtente(Long userId);
}
