package com.elismarket.eshop.businessLogic.cruderepos;

import com.elismarket.eshop.model.entities.FeedbackImpl;
import com.elismarket.eshop.model.interfaces.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackCrud extends CrudRepository<FeedbackImpl, Long> {
    Optional<FeedbackImpl> findById(Long id);

    List<FeedbackImpl> findAllByUtente(Utente utente);
}
