package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.service.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/feedback", produces = "application/json")
@CrossOrigin(origins = "*")
public class FeedbackController {

    @Autowired
    private FeedbackServiceImpl feedbackService;

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
            feedbackService.deleteFeedback(Feedback.of(feedbackService.getById(id)));
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/all")
    public List<FeedbackDTO> getAllFeedback() {
        return feedbackService.getAll();
    }

    @GetMapping("/id/{id}")
    public FeedbackDTO getFeedback(@PathVariable("id") Long id) {
        return feedbackService.getById(id);
    }
}
