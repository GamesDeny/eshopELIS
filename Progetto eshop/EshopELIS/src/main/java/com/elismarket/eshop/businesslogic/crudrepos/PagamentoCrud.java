package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.PagamentoImpl;
import org.springframework.data.repository.CrudRepository;

public interface PagamentoCrud extends CrudRepository<PagamentoImpl, Long> {
}
