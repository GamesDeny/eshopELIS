package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.interfaces.Prodotto;
import com.elismarket.eshop.model.CategoriaImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaCrud extends CrudRepository<CategoriaImpl, Long> {

}
