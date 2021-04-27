package com.elismarket.eshop.model.interfaces;

public interface RigaOrdine {
    Long getId();

    void setId(Long id);

    Float getPrezzoTotale();

    void setPrezzoTotale(Float prezzoTotale);

    Float getScontoApplicato();

    void setScontoApplicato(Float scontoApplicato);

    Integer getQuantitaProdotto();

    void setQuantitaProdotto(Integer quantitaProdotto);
}
