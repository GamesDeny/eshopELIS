package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
 *
 * CRUD class for Products
 *
 */


public interface ProdottoCrud extends JpaRepository<Prodotto, Long> {

    List<Prodotto> findAllByCategoria(Categoria categoria);

    List<Prodotto> findAllByQuantitaGreaterThanEqual(Integer quantita);

    List<Prodotto> findAllByQuantitaLessThanEqual(Integer quantita);

    List<Prodotto> findAllByUtente(Utente byId);
}
