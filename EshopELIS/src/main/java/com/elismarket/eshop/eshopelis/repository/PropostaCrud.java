package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Jpa repository for {@link Proposta Proposta}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface PropostaCrud extends JpaRepository<Proposta, Long> {

    List<Proposta> findAllByIsAccettato(Boolean isAccettato);

    List<Proposta> findAllByUtente(Utente utente);

    List<Proposta> findAllByIsAccettatoIsNull();
}
