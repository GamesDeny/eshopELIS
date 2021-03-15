package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.OrdineImpl;
import org.springframework.data.repository.CrudRepository;

public interface OrdineCrud extends CrudRepository<OrdineImpl, Long> {
}
