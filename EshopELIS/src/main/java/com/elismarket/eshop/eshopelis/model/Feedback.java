package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Feedback class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all Feedback information
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "feedback")
public class Feedback {

    /**
     * Primary key of the Entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Brief description of the Feedback
     */
    @Column(nullable = false)
    private String oggetto;

    /**
     * Feedback
     */
    @Column(nullable = false)
    private String descrizione;

    /**
     * status of the Feedback, if null it has not been worked
     */
    @Column
    private Boolean isAccepted;

    /**
     * date of subScription of the Feedback
     */
    @Column
    private LocalDate subscriptionDate;

    /**
     * {@link Utente Utente} that sent the Feedback
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    /**
     * Returns an instance of Feedback from a {@link FeedbackDTO FeedbackDTO}
     *
     * @param feedbackDTO instance of FeedbackDTO
     * @return Feedback representation of FeedbackDTO
     */
    public static Feedback of(FeedbackDTO feedbackDTO) {
        return Feedback.builder()
                .oggetto(feedbackDTO.oggetto)
                .descrizione(feedbackDTO.descrizione)
                .isAccepted(feedbackDTO.isAccepted)
                .subscriptionDate(feedbackDTO.subscriptionDate)
                .build();
    }

    /**
     * Returns an instance of {@link FeedbackDTO FeedbackDTO} from a Feedback
     *
     * @param feedback instance of Feedback
     * @return FeedbackDTO representation of the Feedback
     */
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
