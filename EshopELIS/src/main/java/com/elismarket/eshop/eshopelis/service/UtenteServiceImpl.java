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
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteCrud utenteCrud;

    //operazioni di inserimento utente nel DB
    public UtenteDTO addUtente(UtenteDTO utenteDTO) {
        Utente u = Utente.of(utenteDTO);
        try {
            u.setPassword((Utente.hashPassword(u.getPassword())));
            utenteCrud.save(u);
        } catch (Exception e) {
            throw new UtenteException("Aggiornamento non riuscito, ricontrolla i dati inviati!");
        }
        return utenteCrud.findById(utenteDTO.id).isPresent() ? Utente.to(utenteCrud.findById(utenteDTO.id).get()) : null;
    }

    public Boolean removeUtente(Long id) {
        try {
            utenteCrud.deleteById(id);
        } catch (Exception e) {
            throw new UtenteException("Cannot find Utente for provided item");
        }
        return utenteCrud.findById(id).isPresent();
    }

    //richiesta di utenti
    //se vuoto li ritorna tutti altrimenti ritorna in base al valore di findby
    //il valore mi serve a capire se vuole un utente normale o un admin
    public List<UtenteDTO> getAll(String findby) {
        List<UtenteDTO> result = new ArrayList<>();

        switch (findby) {
            case "":
                utenteCrud.findAll().forEach(utente -> result.add(Utente.to(utente)));
                return result;
            case "admin":
                utenteCrud.findAllByIsAdmin(true).forEach(utente -> result.add(Utente.to(utente)));
                return result;
            case "user":
                utenteCrud.findAllByIsAdmin(false).forEach(utente -> result.add(Utente.to(utente)));
                return result;
        }
        throw new UtenteException();
    }

    public UtenteDTO getByMail(String mail) {
        if (Objects.isNull(utenteCrud.findByMail(mail)))
            throw new UtenteException("Not found");
        return Utente.to(utenteCrud.findByMail(mail));
    }

    public UtenteDTO getByUser(String username) {
        if (Objects.isNull(utenteCrud.findByUsername(username)))
            throw new UtenteException("Not found");
        return Utente.to(utenteCrud.findByUsername(username));
    }

    public UtenteDTO getUtente(Long id) {
        if (utenteCrud.findById(id).isPresent())
            throw new UtenteException("Not found");
        return Utente.to(utenteCrud.findById(id).get());
    }

    public UtenteDTO getUtente(Integer siglaResidenza) {
        try {
            return Utente.to(utenteCrud.findBySiglaResidenza(siglaResidenza));
        } catch (Exception e) {
            throw new UtenteException("User with this sigla doesn't exist");
        }
    }

    public UtenteDTO getLoginUtente(String username, String password) {
        if (Objects.isNull(username) || Objects.isNull(password))
            throw new UtenteException("Missing parameters!");
        return Utente.to(utenteCrud.findByUsernameAndPassword(username, Utente.hashPassword(password)));
    }

}