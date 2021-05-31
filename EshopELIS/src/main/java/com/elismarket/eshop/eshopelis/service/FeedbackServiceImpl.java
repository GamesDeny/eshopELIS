package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.exception.FeedbackException;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.repository.FeedbackCrud;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackCrud feedbackCrud;

    @Autowired
    UtenteHelper utenteHelper;


    @Override
    public FeedbackDTO addFeedback(FeedbackDTO feedbackDTO) {
        Checkers.feedbackFieldsChecker(feedbackDTO);
        return Feedback.to(feedbackCrud.saveAndFlush(Feedback.of(feedbackDTO)));
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

        Checkers.feedbackFieldsChecker(feedbackDTO);

        Feedback save = Feedback.of(feedbackDTO);
        save.setUtente(Objects.isNull(feedbackDTO.utente_id) ? f.getUtente() : utenteHelper.findById(feedbackDTO.utente_id));
        feedbackCrud.saveAndFlush(save);

        return Feedback.to(feedbackCrud.findById(id).orElseThrow(() -> new FeedbackException("Cannot find Feedback")));
    }

    @Override
    public Boolean deleteFeedback(Long id) {
        if (!feedbackCrud.existsById(id))
            throw new FeedbackException("Not found");

        feedbackCrud.deleteById(id);
        return !feedbackCrud.existsById(id);
    }

    @Override
    public List<FeedbackDTO> getAll() {
        if (feedbackCrud.findAll().isEmpty())
            throw new FeedbackException("List is empty");

        List<FeedbackDTO> result = new ArrayList<>();
        feedbackCrud.findAll().forEach((feedback) -> result.add(Feedback.to(feedback)));
        return result;
    }

    @Override
    public FeedbackDTO getById(Long id) {
        return Feedback.to(feedbackCrud.findById(id).orElseThrow(() -> new FeedbackException("Cannot find Feedback")));
    }

}
