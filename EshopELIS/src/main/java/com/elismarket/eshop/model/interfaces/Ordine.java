package com.elismarket.eshop.model.interfaces;

import com.elismarket.eshop.model.entities.PagamentoImpl;

import java.time.LocalDate;

public interface Ordine {
    Long getId();

    void setId(Long id);

    Boolean getEvaso();

    void setEvaso(Boolean evaso);

    LocalDate getDataEvasione();

    void setDataEvasione(LocalDate dataEvasione);

    Pagamento getPagamento();

    void setPagamento(PagamentoImpl pagamento);
}
