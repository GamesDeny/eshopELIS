package com.elismarket.eshop.crudrepos;

import com.elismarket.eshop.model.OrdineImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 *
 * CRUD class for Orders
 *
 */

@Repository
public interface OrdineCrud extends CrudRepository<OrdineImpl, Long> {
}
