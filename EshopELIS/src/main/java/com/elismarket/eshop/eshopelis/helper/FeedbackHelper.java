package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.FeedbackCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Helper class for {@link Feedback Feedback} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Component
public class FeedbackHelper {

    /**
     * @see FeedbackCrud
     */
    @Autowired
    FeedbackCrud feedbackCrud;

    /**
     * @see UtenteHelper
     */
    @Autowired
    UtenteHelper utenteHelper;


    /**
     * adds a Feedback to the relative user
     *
     * @param utenteId    id for the {@link Utente Utente} to retrieve
     * @param feedbackDTO {@link FeedbackDTO FeedbackDTO}
     * @return {@link Feedback Feedback} added to the user
     */
    public Feedback addFeedbackToUser(Long utenteId, FeedbackDTO feedbackDTO) {
        Utente u = utenteHelper.findById(utenteId);
        Feedback feedback = Feedback.of(feedbackDTO);
        feedback.setUtente(u);
        return feedbackCrud.saveAndFlush(feedback);
    }

}
