package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.TipoMetodo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jpa repository for {@link TipoMetodo TipoMetodo}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface TipoMetodoCrud extends JpaRepository<TipoMetodo, Long> {
}
