package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.UtenteImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteCrud extends CrudRepository<UtenteImpl, Long> {
}
