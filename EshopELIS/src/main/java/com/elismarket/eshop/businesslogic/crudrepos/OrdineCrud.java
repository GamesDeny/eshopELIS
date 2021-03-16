package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.OrdineImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrdineCrud extends CrudRepository<OrdineImpl, Long> {
}
