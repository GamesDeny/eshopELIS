package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.cruderepos.FeedbackCrud;
import com.elismarket.eshop.customExceptions.FeedbackException;
import com.elismarket.eshop.model.dto.FeedbackDTO;
import com.elismarket.eshop.model.entities.FeedbackImpl;
import com.elismarket.eshop.model.interfaces.Feedback;
import com.elismarket.eshop.model.interfaces.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackCrud feedbackCrud;

    public Boolean saveFeedback(FeedbackDTO feedback) {
        try {
            feedbackCrud.save(FeedbackImpl.of(feedback));
            return true;
        } catch (Exception e) {
//            throw new FeedbackException("Cannot save Feedback");
        }
        return false;
    }

    public List<FeedbackImpl> findAllByUtente(Utente utente) {
        return feedbackCrud.findAllByUtente(utente);
    }

    public void deleteFeedback(FeedbackImpl feedback) {
        try {
            feedbackCrud.delete(feedback);
        } catch (Exception e) {
            throw new FeedbackException("Cannot save Feedback");
        }
    }

    public Feedback getById(Long id) {
        try {
            if (feedbackCrud.findById(id).isPresent())
                return feedbackCrud.findById(id).get();
            throw new RuntimeException();
        } catch (Exception e) {
            throw new FeedbackException("Cannot save Feedback");
        }
    }

    public List<FeedbackImpl> getAll() {
        try {
            return (List<FeedbackImpl>) feedbackCrud.findAll();
        } catch (Exception e) {
            throw new FeedbackException("Cannot get all elements");
        }
    }
}
