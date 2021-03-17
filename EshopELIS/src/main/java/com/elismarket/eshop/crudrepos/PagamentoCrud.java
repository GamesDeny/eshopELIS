package com.elismarket.eshop.crudrepos;

import com.elismarket.eshop.interfaces.Pagamento;
import com.elismarket.eshop.model.PagamentoImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
 *
 * CRUD class for Paymant methods
 *
 */

@Repository
public interface PagamentoCrud extends CrudRepository<PagamentoImpl, Long> {
    List<Pagamento> findAllByTipo(String tipo);

    List<Pagamento> findAllByContantiNotNull();

    List<Pagamento> findAllByPaypalMailNotNull();
}
