package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UtenteCrud extends JpaRepository<Utente, Long> {

//    //    Imposta 0 di default
//    List<Utente> findAllByIdGreaterThanEqual(Long Id);

    List<Utente> findAllByIsAdmin(Boolean isAdmin);

    Utente findBySiglaResidenza(Integer siglaResidenza);

    Utente findByUsernameAndPassword(String username, String password);

    Utente findByMail(String mail);

    Utente findByUsername(String username);

}
