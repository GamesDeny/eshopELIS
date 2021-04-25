package com.elismarket.eshop.businessLogic.crudrepos;

import com.elismarket.eshop.model.entities.RigaOrdineImpl;
import com.elismarket.eshop.model.interfaces.RigaOrdine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
 *
 * CRUD class for Order rows
 *
 */


@Repository
public interface RigaOrdineCrud extends CrudRepository<RigaOrdineImpl, Long> {
    List<RigaOrdine> findAllByQuantitaProdottoGreaterThanEqual(Integer quantita);
}
