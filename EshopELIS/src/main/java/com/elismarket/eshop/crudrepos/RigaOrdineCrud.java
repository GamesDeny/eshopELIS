package com.elismarket.eshop.crudrepos;

import com.elismarket.eshop.model.RigaOrdineImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/*
 *
 * CRUD class for Order rows
 *
 */


@Repository
public interface RigaOrdineCrud extends CrudRepository<RigaOrdineImpl, Long> {
}