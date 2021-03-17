package com.elismarket.eshop.crudrepos;

import com.elismarket.eshop.model.MetodoPagamentoImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/*
 *
 * CRUD class for Paymant methods
 *
 */

@Repository
public interface PagamentoCrud extends CrudRepository<MetodoPagamentoImpl, Long> {
}
