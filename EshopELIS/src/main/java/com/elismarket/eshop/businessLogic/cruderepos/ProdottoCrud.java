package com.elismarket.eshop.businessLogic.cruderepos;

import com.elismarket.eshop.model.entities.ProdottoImpl;
import com.elismarket.eshop.model.interfaces.Prodotto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *
 * CRUD class for Products
 *
 */

@Repository
public interface ProdottoCrud extends CrudRepository<ProdottoImpl, Long> {

    @Query(value = "SELECT DISTINCT p.nomeCategoria FROM ProdottoImpl p")
    List<String> findAllCategoria();

    List<Prodotto> findAllByNomeLike(String nome);

    List<Prodotto> findAllByNomeCategoriaLike(String categoria);

    List<Prodotto> findAllByQuantitaGreaterThanEqual(Integer quantita);

    List<Prodotto> findAllByQuantitaLessThanEqual(Integer quantita);

    ProdottoImpl findAllById(Long id);
}
