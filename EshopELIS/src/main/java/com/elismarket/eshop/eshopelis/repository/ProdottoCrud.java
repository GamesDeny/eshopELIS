package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Jpa repository for {@link Prodotto Prodotto}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface ProdottoCrud extends JpaRepository<Prodotto, Long> {

    List<Prodotto> findAllByCategoria(Categoria categoria);

    List<Prodotto> findAllByUtente(Utente byId);
}
