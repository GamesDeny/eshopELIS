package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackCrud extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findById(Long id);

    List<Feedback> findAllByUtente(Utente byId);
}
