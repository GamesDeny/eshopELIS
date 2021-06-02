package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackDTO {
    public Long id;
    public String oggetto, descrizione;
    public Boolean isAccepted;
    public LocalDate subscriptionDate;

    public Long utente_id;
}
