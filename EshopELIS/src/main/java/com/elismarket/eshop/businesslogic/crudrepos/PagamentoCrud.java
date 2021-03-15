package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.PagamentoImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoCrud extends CrudRepository<PagamentoImpl, Long> {
}
