package com.elismarket.eshop.businessLogic.cruderepos;

import com.elismarket.eshop.model.entities.PagamentoImpl;
import com.elismarket.eshop.model.interfaces.Pagamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/*
 *
 * CRUD class for Paymant methods
 * findAllByTipo returns a list of all payments made for a certain type (cash/paypal)
 */

@Repository
public interface PagamentoCrud extends CrudRepository<PagamentoImpl, Long> {
    List<Pagamento> findAllByContantiNotNull();

    List<Pagamento> findAllByPaypalMailNotNull();
}