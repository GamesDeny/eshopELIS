package com.elismarket.eshop.model.entities;

import com.elismarket.eshop.model.dto.FeedbackDTO;
import com.elismarket.eshop.model.interfaces.Feedback;
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
public class FeedbackImpl implements Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String oggetto, descrizione;
    private Boolean isAccepted;
    private LocalDate subscriptionDate;

    @ManyToOne
    private UtenteImpl utente;

    public static FeedbackImpl of(FeedbackDTO feedbackDTO) {
        return FeedbackImpl.builder()
                .oggetto(feedbackDTO.oggetto)
                .descrizione(feedbackDTO.descrizione)
                .isAccepted(feedbackDTO.isAccepted)
                .subscriptionDate(feedbackDTO.subscriptionDate)
                .build();
    }
}
