package com.elismarket.eshop.services;

import com.elismarket.eshop.crudrepos.UtenteCrud;
import com.elismarket.eshop.interfaces.Utente;
import com.elismarket.eshop.utilities.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/*
 *
 * Service class for CRUD operations and control of User
 *
 */

@Service
public class UtenteService {

    @Autowired
    private UtenteCrud utenteCrud;

    public List<Utente> getAll() {
        return utenteCrud.findAllBy();
    }

    public Utente getUtente(String user, String pass) {
        return utenteCrud.findAllByUsernameAndPassword(user, pass);
    }

    public Boolean insertUtente(String cognome, LocalDate dataNascita, String mail, String nome, String password, Integer siglaResidenza, String username) {
        if (getUtente(username, password) == null)
            return false;

        if (Checkers.passwordChecker(password) && Checkers.mailChecker(mail)) {
            utenteCrud.insertUser(cognome, dataNascita, mail, nome, password, siglaResidenza, username);
            return true;
        }
        return false;
    }
}
