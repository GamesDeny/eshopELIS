package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
 *
 * CRUD class for Products
 *
 */


public interface ProdottoCrud extends JpaRepository<Prodotto, Long> {

    @Query(value = "SELECT DISTINCT p.nomeCategoria FROM Prodotto p")
    List<String> findAllCategoria();

    List<Prodotto> findAllByNomeLike(String nome);

    List<Prodotto> findAllByNomeCategoriaLike(String categoria);

    List<Prodotto> findAllByQuantitaGreaterThanEqual(Integer quantita);

    List<Prodotto> findAllByQuantitaLessThanEqual(Integer quantita);

    Prodotto findAllById(Long id);
}
