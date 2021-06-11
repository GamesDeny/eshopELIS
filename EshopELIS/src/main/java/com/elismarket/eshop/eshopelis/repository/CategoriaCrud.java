package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Jpa repository for {@link Categoria Categoria}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface CategoriaCrud extends JpaRepository<Categoria, Long> {
    Categoria findByNome(String name);
}
