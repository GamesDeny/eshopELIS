package com.elismarket.eshop.model.interfaces;

import java.time.LocalDate;

public interface Ordine {
    void setId(Long id);

    Long getId();

    void setEvaso(Boolean evaso);

    Boolean getEvaso();

    void setDataEvasione(LocalDate dataEvasione);

    LocalDate getDataEvasione();
}
