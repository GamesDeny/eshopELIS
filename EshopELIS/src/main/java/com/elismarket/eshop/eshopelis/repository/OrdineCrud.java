package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Jpa repository for {@link Ordine Ordine}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface OrdineCrud extends JpaRepository<Ordine, Long> {
    List<Ordine> findAllByEvaso(Boolean evaso);

    List<Ordine> findAllByDataEvasioneBefore(LocalDate dataEvasione);

    List<Ordine> findAllByDataEvasioneAfter(LocalDate dataEvasione);

    List<Ordine> findAllByDataEvasioneBetween(LocalDate dataEvasione1, LocalDate dataEvasione2);

    List<Ordine> findAllByUtente(Utente utente);
}
