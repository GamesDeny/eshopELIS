package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.exception.FeedbackException;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.FeedbackCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackServiceImpl {

    @Autowired
    private FeedbackCrud feedbackCrud;

    public Boolean saveFeedback(FeedbackDTO feedback) {
        try {
            feedbackCrud.save(Feedback.of(feedback));
            return true;
        } catch (Exception e) {
//            throw new FeedbackException("Cannot save Feedback");
        }
        return false;
    }

    public List<FeedbackDTO> findAllByUtente(Utente utente) {
        List<FeedbackDTO> result = new ArrayList<>();

        feedbackCrud.findAllByUtente(utente).forEach(feedback -> result.add(toDTO(feedback)));

        return result;
    }

    public void deleteFeedback(Feedback feedback) {
        try {
            feedbackCrud.delete(feedback);
        } catch (Exception e) {
//            throw new FeedbackException("Cannot save Feedback");
        }
    }

    public FeedbackDTO getById(Long id) {
        try {
            if (feedbackCrud.findById(id).isPresent())
                return toDTO(feedbackCrud.findById(id).get());
            throw new RuntimeException();
        } catch (Exception e) {
            throw new FeedbackException("Cannot save Feedback");
        }
    }

    public List<FeedbackDTO> getAll() {
        List<FeedbackDTO> result = new ArrayList<>();

        try {
            feedbackCrud.findAll().forEach((feedback) -> result.add(toDTO(feedback)));
            return result;
        } catch (Exception e) {
            throw new FeedbackException("Cannot get all elements");
        }
    }

    //transform feedback into relative DTO
    private FeedbackDTO toDTO(Feedback feedback) {
        FeedbackDTO f = new FeedbackDTO();

        f.setId(feedback.getId());
        f.setOggetto(feedback.getOggetto());
        f.setDescrizione(feedback.getDescrizione());
        f.setIsAccepted(feedback.getIsAccepted());
        f.setSubscriptionDate(feedback.getSubscriptionDate());

        return f;
    }
}
