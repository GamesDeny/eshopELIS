package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.FeedbackService;
import com.elismarket.eshop.model.dto.FeedbackDTO;
import com.elismarket.eshop.model.entities.FeedbackImpl;
import com.elismarket.eshop.model.interfaces.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/feedback", produces = "application/json")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    //TODO restyling of API and service
    @PostMapping("/add")
    public ResponseEntity<Object> addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.saveFeedback(feedbackDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateFeedback(@PathVariable("id") Long id, @RequestBody FeedbackDTO feedbackDTO) {

        return feedbackService.saveFeedback(feedbackDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFeedback(@PathVariable("id") Long id) {
        try {
            feedbackService.deleteFeedback((FeedbackImpl) feedbackService.getById(id));
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/all")
    public List<FeedbackImpl> getAllFeedback() {
        return feedbackService.getAll();
    }

    @GetMapping("/id/{id}")
    public Feedback getFeedback(@PathVariable("id") Long id) {
        return feedbackService.getById(id);
    }
}
