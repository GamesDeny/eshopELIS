package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.exception.FeedbackException;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.FeedbackCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

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

    public List<FeedbackDTO> findAllByUtente(UtenteDTO utente) {
        List<FeedbackDTO> result = new ArrayList<>();

        feedbackCrud.findAllByUtente(Utente.of(utente)).forEach(feedback -> result.add(Feedback.to(feedback)));

        return result;
    }

    public void deleteFeedback(FeedbackDTO feedbackDTO) {
        try {
            feedbackCrud.delete(Feedback.of(feedbackDTO));
        } catch (Exception e) {
//            throw new FeedbackException("Cannot save Feedback");
        }
    }

    public FeedbackDTO getById(Long id) {
        try {
            if (feedbackCrud.findById(id).isPresent())
                return Feedback.to(feedbackCrud.findById(id).get());
            throw new RuntimeException();
        } catch (Exception e) {
            throw new FeedbackException("Cannot save Feedback");
        }
    }

    public List<FeedbackDTO> getAll() {
        List<FeedbackDTO> result = new ArrayList<>();

        try {
            feedbackCrud.findAll().forEach((feedback) -> result.add(Feedback.to(feedback)));
            return result;
        } catch (Exception e) {
            throw new FeedbackException("Cannot get all elements");
        }
    }


}
