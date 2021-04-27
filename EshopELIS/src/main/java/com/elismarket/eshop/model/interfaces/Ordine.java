package com.elismarket.eshop.model.interfaces;

import java.time.LocalDate;

public interface Ordine {
    Long getId();

    void setId(Long id);

    Boolean getEvaso();

    void setEvaso(Boolean evaso);

    LocalDate getDataEvasione();

    void setDataEvasione(LocalDate dataEvasione);
}
