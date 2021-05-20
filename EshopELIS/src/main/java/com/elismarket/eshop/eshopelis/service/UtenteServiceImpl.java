package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 *
 * Service class for CRUD operations and control of User
 *
 */

@Service
public class UtenteServiceImpl {

    @Autowired
    private UtenteCrud utenteCrud;

    //richiesta di utenti
    //se vuoto li ritorna tutti altrimenti ritorna in base al valore di findby
    //il valore mi serve a capire se vuole un utente normale o un admin
    public List<Utente> getAll(String findby) {
        switch (findby) {
            case "":
                List<Utente> result = new ArrayList<>();
                utenteCrud.findAll().forEach(result::add);
                return result;
            case "admin":
                return utenteCrud.findAllByIsAdmin(true);
            case "user":
                return utenteCrud.findAllByIsAdmin(false);
        }
        throw new UtenteException();
    }

    public Utente getByMail(String mail) {
        Utente u = utenteCrud.findByMail(mail);
        if (Objects.isNull(u))
            throw new UtenteException("Not found");
        return u;
    }

    public Utente getByUser(String username) {
        Utente u = utenteCrud.findByMail(username);
        if (Objects.isNull(u))
            throw new UtenteException("Not found");
        return u;
    }

    public Utente getUtente(Long id) {
        if (utenteCrud.findById(id).isPresent())
            throw new UtenteException("Not found");
        return utenteCrud.findById(id).get();
    }

    public Utente getUtente(Integer siglaResidenza) {
        try {
            return utenteCrud.findBySiglaResidenza(siglaResidenza);
        } catch (Exception e) {
            throw new UtenteException("User with this sigla doesn't exist");
        }
    }

    //operazioni di inserimento utente nel DB
    public Boolean addUtente(UtenteDTO utenteDTO) {
        Utente u = Utente.of(utenteDTO);
        try {
            u.setPassword((Utente.hashPassword(u.getPassword())));
            utenteCrud.save(u);
            return true;
        } catch (Exception e) {
//            throw new UtenteException("Aggiornamento non riuscito, ricontrolla i dati inviati!");
        }
        return false;
    }

    public void removeUtente(Long id) {
        try {
            utenteCrud.deleteById(id);
        } catch (Exception e) {
            throw new UtenteException("Cannot find Utente for provided item");
        }
    }

    public Utente getLoginUtente(String username, String password) {
        if (Objects.isNull(username) || Objects.isNull(password))
            throw new UtenteException("Missing parameters!");
        return utenteCrud.findByUsernameAndPassword(username, Utente.hashPassword(password));
    }

}