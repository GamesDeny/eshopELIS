package com.elismarket.eshop.businessLogic.cruderepos;

import com.elismarket.eshop.model.entities.UtenteImpl;
import com.elismarket.eshop.model.interfaces.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteCrud extends CrudRepository<UtenteImpl, Long> {

//    //    Imposta 0 di default
//    List<Utente> findAllByIdGreaterThanEqual(Long Id);

    List<Utente> findAllByIsAdmin(Boolean isAdmin);

    Utente findBySiglaResidenza(Integer siglaResidenza);

    Utente findByUsernameAndPassword(String username, String password);

    Utente findByMail(String mail);

}
