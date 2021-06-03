package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Jpa repository for {@link Feedback Feedback}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface FeedbackCrud extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByUtente(Utente byId);
}
