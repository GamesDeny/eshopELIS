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
    private Long id;

    @Column(nullable = false)
    private String oggetto;

    @Column(nullable = false)
    private String descrizione;

    @Column
    private Boolean isAccepted;

    @Column
    private LocalDate subscriptionDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "utente_id")
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

        f.id = feedback.getId();
        f.oggetto = feedback.getOggetto();
        f.descrizione = feedback.getDescrizione();
        f.isAccepted = feedback.getIsAccepted();
        f.subscriptionDate = feedback.getSubscriptionDate();
        f.utente_id = feedback.getUtente().getId();

        return f;
    }
}
