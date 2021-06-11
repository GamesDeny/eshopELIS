package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.FeedbackException;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.repository.FeedbackCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.FeedbackService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;

/**
 * {@link Feedback Feedback} service class for interaction between DB and relative Controller
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

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
     * Adds an item with
     *
     * @param feedbackDTO {@link FeedbackDTO FeedbackDTO} with the fields
     * @return DTO of created item
     * @throws FeedbackException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @see Checkers#feedbackFieldsChecker(FeedbackDTO)
     */
    @Override
    public FeedbackDTO addFeedback(FeedbackDTO feedbackDTO) {
        Checkers.feedbackFieldsChecker(feedbackDTO);
        Feedback f = Feedback.of(feedbackDTO);
        f.setSubscriptionDate(LocalDate.now());
        f.setUtente(utenteHelper.findById(feedbackDTO.utente_id));
        return Feedback.to(feedbackCrud.saveAndFlush(f));
    }

    /**
     * Updates Feedback for the provided id with the FeedbackDTO informations
     *
     * @param id          of the {@link Feedback Feedback} to
     * @param feedbackDTO {@link FeedbackDTO FeedbackDTO} with the updated fields
     * @return DTO with updated item
     * @throws FeedbackException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws FeedbackException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @see Checkers#feedbackFieldsChecker(FeedbackDTO)
     */
    @Override
    public FeedbackDTO updateFeedback(Long id, FeedbackDTO feedbackDTO) {
        if (Objects.isNull(id) || Objects.isNull(feedbackDTO))
            throw new FeedbackException(MISSING_PARAMETERS.name());

        if (!feedbackCrud.existsById(id))
            throw new FeedbackException(CANNOT_FIND_ELEMENT.name());

        Feedback f = feedbackCrud.findById(id).orElseThrow(() -> new FeedbackException(CANNOT_FIND_ELEMENT.name()));

        feedbackDTO.id = id;
        feedbackDTO.descrizione = Objects.isNull(feedbackDTO.descrizione) ? f.getDescrizione() : feedbackDTO.descrizione;
        feedbackDTO.oggetto = Objects.isNull(feedbackDTO.oggetto) ? f.getOggetto() : feedbackDTO.oggetto;
        feedbackDTO.isAccepted = Objects.isNull(feedbackDTO.isAccepted) ? f.getIsAccepted() : feedbackDTO.isAccepted;
        feedbackDTO.subscriptionDate = Objects.isNull(feedbackDTO.subscriptionDate) ? f.getSubscriptionDate() : feedbackDTO.subscriptionDate;

        Checkers.feedbackFieldsChecker(feedbackDTO);

        Feedback save = Feedback.of(feedbackDTO);
        save.setUtente(Objects.isNull(feedbackDTO.utente_id) ? f.getUtente() : utenteHelper.findById(feedbackDTO.utente_id));
        feedbackCrud.saveAndFlush(save);

        return Feedback.to(feedbackCrud.findById(id).orElseThrow(() -> new FeedbackException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Deletes Feedback for the provided id
     *
     * @param id of the {@link Feedback Feedback} to
     * @return HTTP 200 if deleted successfully, else 500
     * @throws FeedbackException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws FeedbackException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public Boolean deleteFeedback(Long id) {
        if (Objects.isNull(id))
            throw new FeedbackException(MISSING_PARAMETERS.name());

        if (!feedbackCrud.existsById(id))
            throw new FeedbackException(CANNOT_FIND_ELEMENT.name());

        feedbackCrud.deleteById(id);
        return !feedbackCrud.existsById(id);
    }

    /**
     * Retrieves all Feedbacks
     *
     * @return List of {@link FeedbackDTO FeedbackDTO}
     */
    @Override
    public List<FeedbackDTO> getAll() {

        List<FeedbackDTO> result = new ArrayList<>();
        feedbackCrud.findAll().forEach((feedback) -> result.add(Feedback.to(feedback)));
        return result;
    }

    /**
     * Retrieves all Feedback for a provided Utente
     *
     * @param userId id of the Utente
     * @return representation of all Feedback of a Utente
     * @throws FeedbackException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws FeedbackException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public List<FeedbackDTO> getAllByUtente(Long userId) {
        if (Objects.isNull(userId))
            throw new FeedbackException(MISSING_PARAMETERS.name());

        List<FeedbackDTO> result = new ArrayList<>();
        feedbackCrud.findAllByUtente(utenteHelper.findById(userId)).forEach(feedback -> result.add(Feedback.to(feedback)));
        return result;
    }

    /**
     * Retrieves feedback from id
     *
     * @param id of the {@link Feedback Feedback} to retrieve
     * @return {@link FeedbackDTO FeedbackDTO} representation of retrieved item
     * @throws FeedbackException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws FeedbackException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public FeedbackDTO getById(Long id) {
        if (Objects.isNull(id))
            throw new FeedbackException(MISSING_PARAMETERS.name());

        return Feedback.to(feedbackCrud.findById(id).orElseThrow(() -> new FeedbackException(CANNOT_FIND_ELEMENT.name())));
    }

}
