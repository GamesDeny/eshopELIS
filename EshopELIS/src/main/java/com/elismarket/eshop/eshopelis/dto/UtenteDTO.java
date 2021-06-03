package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.elismarket.eshop.eshopelis.model.Utente Utente} class
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UtenteDTO {
    public Long id;
    public String cognome;
    public LocalDate dataNascita;
    public Boolean isAdmin, logged;
    public String mail, nome, password;
    public Integer siglaResidenza;
    public String username;

    public List<Long> proposte_id;
    public List<Long> pagamenti_id;
    public List<Long> prodotti_id;
    public List<Long> feedbacks_id;
    public List<Long> ordini_id;
}
