package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Jpa repository for {@link Pagamento Pagamento}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface PagamentoCrud extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findAllByContantiNotNullAndContantiGreaterThanEqual(Float value);

    List<Pagamento> findAllByPaypalMailNotNull();

}