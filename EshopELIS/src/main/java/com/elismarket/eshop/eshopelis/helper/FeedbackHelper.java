package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.FeedbackCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Helper is needed for the updates that include other entities
 * add it to the service and let controller return object, not DTO
 */

@Component
public class FeedbackHelper {

    @Autowired
    private FeedbackCrud feedbackCrud;

    @Autowired
    private UtenteCrud utenteCrud;


    //casto variabile, nel caso di array faccio il .add
    public Feedback addFeedbackToUser(Long userId, FeedbackDTO feedbackDTO) {
        Utente u = utenteCrud.findById(userId).orElseThrow(() -> new UtenteException("USER_NOT_FOUND"));
        Feedback feedback = Feedback.of(feedbackDTO);
        feedback.setUtente(u);
        return feedbackCrud.saveAndFlush(feedback);
    }
}
