package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * DTO for {@link com.elismarket.eshop.eshopelis.model.Feedback Feedback} class
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackDTO {
    public Long id;
    public String oggetto, descrizione;
    public Boolean isAccepted;
    public LocalDate subscriptionDate;

    public Long utente_id;
}
