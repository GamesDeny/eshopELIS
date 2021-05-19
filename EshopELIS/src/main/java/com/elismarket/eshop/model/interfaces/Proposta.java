package com.elismarket.eshop.model.interfaces;

import java.time.LocalDate;

public interface Proposta {
    Long getId();

    void setId(Long id);

    String getNome();

    void setNome(String nome);

    String getDescrizione();

    void setDescrizione(String descrizione);

    Float getPrezzoProposto();

    void setPrezzoProposto(Float prezzoProposto);

    Integer getQuantita();

    void setQuantita(Integer quantita);

    LocalDate getSubmissionDate();

    void setSubmissionDate(LocalDate submissionDate);

    Boolean getIsAccettato();

    void setIsAccettato(Boolean isAccettato);

    String getMotivoRifiuto();

    void setMotivoRifiuto(String motivoRifiuto);
}
