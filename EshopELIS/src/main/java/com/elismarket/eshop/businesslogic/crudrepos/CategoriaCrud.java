package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.CategoriaImpl;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaCrud extends CrudRepository<CategoriaImpl, Long> {
}
