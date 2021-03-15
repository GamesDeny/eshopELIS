package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.ProdottoImpl;
import org.springframework.data.repository.CrudRepository;

public interface ProdottoCrud extends CrudRepository<ProdottoImpl, Long> {
}
