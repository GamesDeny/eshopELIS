package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.interfaces.Prodotto;
import com.elismarket.eshop.model.ProdottoImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoCrud extends CrudRepository<ProdottoImpl, Long> {
    public List<Prodotto> findAllByNome(String nome);

    @Query("SELECT p FROM ProdottoImpl p WHERE p.categoria.nome = :nomeCat")
    public List<Prodotto> findByNomeCategoria(@Param("nomeCat") String nomeCat);
}
