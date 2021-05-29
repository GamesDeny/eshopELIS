package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/*
 *
 * CRUD class for Paymant methods
 * findAllByTipo returns a list of all payments made for a certain type (cash/paypal)
 */


public interface PagamentoCrud extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findAllByContantiNotNull();

    List<Pagamento> findAllByPaypalMailNotNull();

    Boolean existsByDescrizione(String descrizione);
}