package com.elismarket.eshop.crudrepos;

import com.elismarket.eshop.interfaces.Prodotto;
import com.elismarket.eshop.model.ProdottoImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *
 * CRUD class for Products
 *
 */

@Repository
public interface ProdottoCrud extends CrudRepository<ProdottoImpl, Long> {
    List<Prodotto> findAllByNome(String nome);

    @Query("SELECT p FROM ProdottoImpl p WHERE p.nomeCategoria = :nomeCategoria")
    List<Prodotto> findAllByNomeCategoria(@Param("nomeCategoria") String nomeCategoria);
}