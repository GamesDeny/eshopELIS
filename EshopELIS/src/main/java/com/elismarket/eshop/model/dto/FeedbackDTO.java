package com.elismarket.eshop.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedbackDTO {
    public Long id;
    public String oggetto, descrizione;
    public Boolean isAccepted;
    public LocalDate subscriptionDate;
}
