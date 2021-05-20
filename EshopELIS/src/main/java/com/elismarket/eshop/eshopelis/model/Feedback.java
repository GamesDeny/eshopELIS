package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String oggetto, descrizione;
    private Boolean isAccepted;
    private LocalDate subscriptionDate;

    @ManyToOne
    private Utente utente;

    public static Feedback of(FeedbackDTO feedbackDTO) {
        return Feedback.builder()
                .oggetto(feedbackDTO.oggetto)
                .descrizione(feedbackDTO.descrizione)
                .isAccepted(feedbackDTO.isAccepted)
                .subscriptionDate(feedbackDTO.subscriptionDate)
                .build();
    }

    //transform feedback into relative DTO
    public static FeedbackDTO to(Feedback feedback) {
        FeedbackDTO f = new FeedbackDTO();

        f.setId(feedback.getId());
        f.setOggetto(feedback.getOggetto());
        f.setDescrizione(feedback.getDescrizione());
        f.setIsAccepted(feedback.getIsAccepted());
        f.setSubscriptionDate(feedback.getSubscriptionDate());

        return f;
    }
}
