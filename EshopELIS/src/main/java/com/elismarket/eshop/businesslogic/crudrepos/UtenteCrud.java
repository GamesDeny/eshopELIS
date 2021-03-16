package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.interfaces.Utente;
import com.elismarket.eshop.model.UtenteImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteCrud extends CrudRepository<UtenteImpl, Long> {
    List<Utente> findAllByUsernameAndPassword(String username, String password);
}
