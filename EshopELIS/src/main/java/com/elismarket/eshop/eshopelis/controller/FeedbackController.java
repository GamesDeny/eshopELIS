package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
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

    @PostMapping("/add")
    public FeedbackDTO addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.saveFeedback(feedbackDTO);
    }

    @PatchMapping("/update/{id}")
    public FeedbackDTO updateFeedback(@PathVariable Long id, @RequestBody FeedbackDTO feedbackDTO) {
        return feedbackService.updateFeedback(id, feedbackDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFeedback(@PathVariable Long id) {
        return feedbackService.deleteFeedback(feedbackService.getById(id)) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @GetMapping("/all")
    public List<FeedbackDTO> getAllFeedback() {
        return feedbackService.getAll();
    }

    @GetMapping("/id/{id}")
    public FeedbackDTO getFeedback(@PathVariable Long id) {
        return feedbackService.getById(id);
    }
}
