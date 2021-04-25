package com.elismarket.eshop.model.interfaces;

public interface RigaOrdine {
    void setId(Long id);

    Long getId();

    void setPrezzoTotale(Float prezzoTotale);

    Float getPrezzoTotale();

    void setScontoApplicato(Float scontoApplicato);

    Float getScontoApplicato();

    void setQuantitaProdotto(Integer quantitaProdotto);

    Integer getQuantitaProdotto();
}
