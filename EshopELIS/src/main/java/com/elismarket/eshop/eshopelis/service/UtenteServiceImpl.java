package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import com.elismarket.eshop.eshopelis.utility.Checkers;
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

        if (!Objects.isNull(utenteCrud.findByMail(utenteDTO.mail)) ||
                !Objects.isNull(utenteCrud.findByUsername(utenteDTO.username)) ||
                !Objects.isNull(utenteCrud.findBySiglaResidenza(utenteDTO.siglaResidenza)))
            throw new UtenteException("Duplicato!");
        else if (!Checkers.siglaChecker(utenteDTO.siglaResidenza))
            throw new UtenteException("Sigla inconsistente");
        else if (!Checkers.birthDateChecker(utenteDTO.dataNascita))
            throw new UtenteException("Data di nascita non valida");
        else if ((utenteDTO.password).equals(utenteDTO.mail) || !(Checkers.mailChecker(utenteDTO.mail) && Checkers.passwordChecker(utenteDTO.password)))
            throw new UtenteException("Mail e/o password inconsistenti");

        u.setPassword((Utente.hashPassword(u.getPassword())));
        utenteCrud.save(u);

        return Utente.to(utenteCrud.findByMail(utenteDTO.mail));
    }

    public Boolean removeUtente(Long id) {
        if (!utenteCrud.findById(id).isPresent())
            throw new UtenteException("Cannot find Utente for provided id");
        utenteCrud.deleteById(id);

        return !utenteCrud.findById(id).isPresent();
    }

    //richiesta di utenti
    //se vuoto li ritorna tutti altrimenti ritorna in base al valore di findby
    //il valore mi serve a capire se vuole un utente normale o un admin
    public List<UtenteDTO> getAll(String findby) {
        List<UtenteDTO> result = new ArrayList<>();

        switch (findby) {
            case "":
                if (utenteCrud.findAll().isEmpty())
                    throw new UtenteException("List empty");

                utenteCrud.findAll().forEach(utente -> result.add(Utente.to(utente)));
                return result;
            case "admin":
                if (utenteCrud.findAllByIsAdmin(true).isEmpty())
                    throw new UtenteException("List empty");

                utenteCrud.findAllByIsAdmin(true).forEach(utente -> result.add(Utente.to(utente)));
                return result;
            case "user":
                if (utenteCrud.findAllByIsAdmin(false).isEmpty())
                    throw new UtenteException("List empty");

                utenteCrud.findAllByIsAdmin(false).forEach(utente -> result.add(Utente.to(utente)));
                return result;
        }
        throw new UtenteException("Error in parameter for getAll Function");
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
        if (!utenteCrud.findById(id).isPresent())
            throw new UtenteException("Not found");
        return Utente.to(utenteCrud.findById(id).get());
    }

    public UtenteDTO getUtente(Integer siglaResidenza) {
        if (Objects.isNull(utenteCrud.findBySiglaResidenza(siglaResidenza)))
            throw new UtenteException("User with this sigla doesn't exist");
        return Utente.to(utenteCrud.findBySiglaResidenza(siglaResidenza));
    }

    public UtenteDTO getLoginUtente(String username, String password) {
        if (Objects.isNull(username) || Objects.isNull(password))
            throw new UtenteException("Missing parameters!");
        return Utente.to(utenteCrud.findByUsernameAndPassword(username, Utente.hashPassword(password)));
    }

}