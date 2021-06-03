package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jpa repository for {@link RigaOrdine RigaOrdine}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface RigaOrdineCrud extends JpaRepository<RigaOrdine, Long> {
}
