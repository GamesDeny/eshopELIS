package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.MetodoDiPagamentoImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoCrud extends CrudRepository<MetodoDiPagamentoImpl, Long> {
}
