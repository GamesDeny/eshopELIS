package com.elismarket.eshop.businessLogic.crudrepos;

import com.elismarket.eshop.model.entities.PagamentoImpl;
import com.elismarket.eshop.model.interfaces.Pagamento;
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
