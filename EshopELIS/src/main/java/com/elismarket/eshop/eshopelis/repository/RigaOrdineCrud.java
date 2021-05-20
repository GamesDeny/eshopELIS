package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/*
 *
 * CRUD class for Order rows
 *
 */


public interface RigaOrdineCrud extends JpaRepository<RigaOrdine, Long> {
    List<RigaOrdine> findAllByQuantitaProdottoGreaterThanEqual(Integer quantita);
}
