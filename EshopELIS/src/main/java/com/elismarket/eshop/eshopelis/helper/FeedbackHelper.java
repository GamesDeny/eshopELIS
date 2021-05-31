package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.exception.FeedbackException;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.FeedbackCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Helper is needed for the updates that include other entities
 * add it to the service and let controller return object, not DTO
 */

@Component
public class FeedbackHelper {

    @Autowired
    private FeedbackCrud feedbackCrud;

    @Autowired
    private UtenteHelper utenteHelper;


    //casto variabile, nel caso di array faccio il .add
    public Feedback addFeedbackToUser(Long userId, FeedbackDTO feedbackDTO) {
        Utente u = utenteHelper.findById(userId);
        Feedback feedback = Feedback.of(feedbackDTO);
        feedback.setUtente(u);
        return feedbackCrud.saveAndFlush(feedback);
    }

    public void linkUtenteToFeedbacks(Long utenteId, List<Long> feedbacks_id) {
        List<Feedback> result = feedbackCrud.findAllById(feedbacks_id);
        if (result.isEmpty())
            throw new FeedbackException("List is empty");

        result.forEach(feedback -> feedback.setUtente(utenteHelper.findById(utenteId)));
        feedbackCrud.saveAll(result);
    }
}
