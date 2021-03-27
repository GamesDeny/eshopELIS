package com.elismarket.eshop.crudrepos;

import com.elismarket.eshop.interfaces.Prodotto;
import com.elismarket.eshop.model.ProdottoImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*
 *
 * CRUD class for Products
 *
 */

@Repository
public interface ProdottoCrud extends CrudRepository<ProdottoImpl, Long> {
    List<Prodotto> findAllByNome(String nome);

    List<Prodotto> findAllByNomeCategoria(String categoria);

    List<Prodotto> findAllByQuantitaGreaterThanEqual(Integer quantita);

    List<Prodotto> findAllByQuantitaLessThanEqual(Integer quantita);

    Prodotto findAllById(@RequestParam("id") Long id);
}
