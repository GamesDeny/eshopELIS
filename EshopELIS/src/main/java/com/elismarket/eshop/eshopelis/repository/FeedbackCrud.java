package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedbackCrud extends JpaRepository<Feedback, Long> {
    Optional<Feedback> findById(Long id);
}
