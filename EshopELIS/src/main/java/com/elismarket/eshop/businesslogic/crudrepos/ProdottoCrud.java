package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.ProdottoImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoCrud extends CrudRepository<ProdottoImpl, Long> {
}
