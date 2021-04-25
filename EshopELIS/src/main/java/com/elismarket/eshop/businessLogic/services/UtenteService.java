package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.crudrepos.UtenteCrud;
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

    public List<Utente> getAll(String findby) {
        switch (findby) {
            case "":
                return utenteCrud.findAllBy();
            case "admin":
                return utenteCrud.findAllByIsAdmin(true);
            case "user":
                return utenteCrud.findAllByIsAdmin(false);
        }
        throw new RuntimeException("Missing param");
    }

    public Utente getUtente(UtenteDTO utenteDTO, String username) {
        if (Objects.isNull(utenteDTO.username) || Objects.isNull(utenteDTO.password))
            throw new RuntimeException("Missing parameters!");
        return utenteCrud.findAllByUsernameAndPassword(utenteDTO.username, utenteDTO.password);
    }

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
