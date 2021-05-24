package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.service.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public FeedbackDTO updateFeedback(@PathVariable("id") Long id, @RequestBody FeedbackDTO feedbackDTO) {
        Feedback f = Feedback.of(feedbackService.getById(id));

        feedbackDTO.setId(id);
        feedbackDTO.setDescrizione(Objects.isNull(feedbackDTO.descrizione) ? f.getDescrizione() : feedbackDTO.descrizione);
        feedbackDTO.setOggetto(Objects.isNull(feedbackDTO.oggetto) ? f.getOggetto() : feedbackDTO.oggetto);
        feedbackDTO.setIsAccepted(Objects.isNull(feedbackDTO.isAccepted) ? f.getIsAccepted() : feedbackDTO.isAccepted);
        feedbackDTO.setSubscriptionDate(Objects.isNull(feedbackDTO.subscriptionDate) ? f.getSubscriptionDate() : feedbackDTO.subscriptionDate);

        return feedbackService.saveFeedback(Feedback.to(f));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFeedback(@PathVariable("id") Long id) {
        return feedbackService.deleteFeedback(feedbackService.getById(id)) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
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
