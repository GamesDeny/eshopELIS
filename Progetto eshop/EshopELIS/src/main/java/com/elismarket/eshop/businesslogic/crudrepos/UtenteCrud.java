package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.UtenteImpl;
import org.springframework.data.repository.CrudRepository;

public interface UtenteCrud extends CrudRepository<UtenteImpl, Long> {
}
