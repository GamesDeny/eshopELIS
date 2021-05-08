package com.elismarket.eshop.model.interfaces;

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
}
