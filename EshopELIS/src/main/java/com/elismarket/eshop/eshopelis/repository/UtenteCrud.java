package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Jpa repository for {@link Utente Utente}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface UtenteCrud extends JpaRepository<Utente, Long> {

    List<Utente> findAllByIsAdmin(Boolean isAdmin);

    Utente findBySiglaResidenza(Integer siglaResidenza);

    Utente findByUsernameAndPassword(String username, String password);

    Utente findByMail(String mail);

    Utente findByUsername(String username);

}
