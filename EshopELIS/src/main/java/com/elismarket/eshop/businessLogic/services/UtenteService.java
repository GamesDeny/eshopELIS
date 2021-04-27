package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.cruderepos.UtenteCrud;
import com.elismarket.eshop.model.dto.UtenteDTO;
import com.elismarket.eshop.model.entities.UtenteImpl;
import com.elismarket.eshop.model.interfaces.Utente;
import com.elismarket.eshop.utilities.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/*
 *
 * Service class for CRUD operations and control of User
 *
 */

@Service
public class UtenteService {

    @Autowired
    private UtenteCrud utenteCrud;

    //richiesta di utenti
    //se vuoto li ritorna tutti altrimenti ritorna in base al valore di findby
    //il valore mi serve a capire se vuole un utente normale o un admin
    public List<Utente> getAll(String findby) {
        switch (findby) {
            case "":
                List<Utente> lista = utenteCrud.findAllBy();
            case "admin":
                return utenteCrud.findAllByIsAdmin(true);
            case "user":
                return utenteCrud.findAllByIsAdmin(false);
        }
        throw new RuntimeException("Missing parameters!");
    }

    //ottengo il singolo utente per il login o per controllare se già presente in db
    public Utente getUtente(UtenteDTO utenteDTO, String username) {
        if (Objects.isNull(utenteDTO.username) || Objects.isNull(utenteDTO.password))
            throw new RuntimeException("Missing parameters!");
        return utenteCrud.findAllByUsernameAndPassword(utenteDTO.username, utenteDTO.password);
    }

    //operazioni di inserimento utente nel DB
    public Boolean insertUtente(UtenteDTO utenteDTO) {
        if (Objects.isNull(utenteDTO.username) || Objects.isNull(utenteDTO.password))
            return false;

        if (Checkers.passwordChecker(utenteDTO.password) && Checkers.mailChecker(utenteDTO.mail)) {
            utenteCrud.save(UtenteImpl.of(utenteDTO));
            return true;
        }
        return false;
    }
}