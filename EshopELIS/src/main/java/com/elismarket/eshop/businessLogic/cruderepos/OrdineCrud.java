package com.elismarket.eshop.businessLogic.cruderepos;

import com.elismarket.eshop.model.entities.OrdineImpl;
import com.elismarket.eshop.model.interfaces.Ordine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/*
 *
 * CRUD class for Orders
 *
 */

@Repository
public interface OrdineCrud extends CrudRepository<OrdineImpl, Long> {
    List<Ordine> findAllByEvaso(Boolean evaso);

    List<Ordine> findAllByDataEvasioneBefore(LocalDate dataEvasione);

    List<Ordine> findAllByDataEvasioneAfter(LocalDate dataEvasione);

    List<Ordine> findAllByDataEvasioneBetween(LocalDate dataEvasione1, LocalDate dataEvasione2);

    void deleteById(Long id);
}
