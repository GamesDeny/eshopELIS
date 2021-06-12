package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.service.interfaces.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for {@link Feedback Feedback} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/api/feedback", produces = "application/json")
@CrossOrigin(origins = "*")
public class FeedbackController {

    /**
     * @see FeedbackService
     */
    @Autowired
    private FeedbackService feedbackService;

    /**
     * Adds an item with
     *
     * @param feedbackDTO {@link FeedbackDTO FeedbackDTO} with the fields
     * @return DTO of created item
     */
    @PostMapping("/add")
    public FeedbackDTO addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.addFeedback(feedbackDTO);
    }

    /**
     * Updates Feedback for the provided id with the FeedbackDTO informations
     *
     * @param id          of the {@link Feedback Feedback} to
     * @param feedbackDTO {@link FeedbackDTO FeedbackDTO} with the updated fields
     * @return DTO with updated item
     */
    @PatchMapping("/update/{id}")
    public FeedbackDTO updateFeedback(@PathVariable Long id, @RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.updateFeedback(id, feedbackDTO);
    }

    /**
     * Deletes Feedback for the provided id
     *
     * @param id of the {@link Feedback Feedback} to
     * @return HTTP 200 if deleted successfully, else 500
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> deleteFeedback(@PathVariable Long id) {
        return feedbackService.deleteFeedback(id) ? ResponseEntity.status(500).build() : ResponseEntity.status(200).build();
    }

    /**
     * Retrieves all Feedbacks
     *
     * @return List of {@link FeedbackDTO FeedbackDTO}
     */
    @GetMapping("/all")
    public List<FeedbackDTO> getAllFeedback() {
        return feedbackService.getAll();
    }

    /**
     * Retrieves all Feedback for a provided Utente
     *
     * @param userId id of the Utente
     * @return representation of all Feedback of a Utente
     */
    @GetMapping("/all/utente/{userId}")
    public List<FeedbackDTO> getAllByUtente(@PathVariable Long userId) {
        return feedbackService.getAllByUtente(userId);
    }

    /**
     * Retrieves feedback from id
     *
     * @param id of the {@link Feedback Feedback} to retrieve
     * @return {@link FeedbackDTO FeedbackDTO} representation of retrieved item
     */
    @GetMapping("/id/{id}")
    public FeedbackDTO getFeedback(@PathVariable Long id) {
        return feedbackService.getById(id);
    }
}
