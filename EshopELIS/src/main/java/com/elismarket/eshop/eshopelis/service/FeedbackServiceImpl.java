package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.exception.FeedbackException;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.repository.FeedbackCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private UtenteHelper utenteHelper;

    @Autowired
    private FeedbackCrud feedbackCrud;

    @Override
    public FeedbackDTO saveFeedback(FeedbackDTO feedbackDTO) {
        try {
            feedbackCrud.save(Feedback.of(feedbackDTO));
        } catch (Exception e) {
            throw new FeedbackException("Cannot save Feedback");
        }
        return feedbackCrud.findById(feedbackDTO.id).isPresent() ? Feedback.to(feedbackCrud.findById(feedbackDTO.id).orElseThrow(() -> new FeedbackException("Cannot find Feedback"))) : null;
    }

    @Override
    public FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO) {
        if (!feedbackCrud.existsById(id))
            throw new FeedbackException("Not found");

        Feedback f = feedbackCrud.findById(id).orElseThrow(() -> new FeedbackException("Cannot find Feedback"));

        feedbackDTO.id = id;
        feedbackDTO.descrizione = Objects.isNull(feedbackDTO.descrizione) ? f.getDescrizione() : feedbackDTO.descrizione;
        feedbackDTO.oggetto = Objects.isNull(feedbackDTO.oggetto) ? f.getOggetto() : feedbackDTO.oggetto;
        feedbackDTO.isAccepted = Objects.isNull(feedbackDTO.isAccepted) ? f.getIsAccepted() : feedbackDTO.isAccepted;
        feedbackDTO.subscriptionDate = Objects.isNull(feedbackDTO.subscriptionDate) ? f.getSubscriptionDate() : feedbackDTO.subscriptionDate;

        return Feedback.to(feedbackCrud.findById(id).orElseThrow(() -> new FeedbackException("Cannot find Feedback")));
    }

    @Override
    public Boolean deleteFeedback(FeedbackDTO feedbackDTO) {
        try {
            feedbackCrud.delete(Feedback.of(feedbackDTO));
        } catch (Exception e) {
            throw new FeedbackException("Cannot save Feedback");
        }
        return feedbackCrud.findById(feedbackDTO.id).isPresent();
    }

    @Override
    public List<FeedbackDTO> getAll() {
        List<FeedbackDTO> result = new ArrayList<>();

        try {
            feedbackCrud.findAll().forEach((feedback) -> result.add(Feedback.to(feedback)));
            return result;
        } catch (Exception e) {
            throw new FeedbackException("Cannot get all elements");
        }
    }

    @Override
    public FeedbackDTO getById(Long id) {
        return Feedback.to(feedbackCrud.findById(id).orElseThrow(() -> new FeedbackException("Cannot find Feedback")));
    }

}
