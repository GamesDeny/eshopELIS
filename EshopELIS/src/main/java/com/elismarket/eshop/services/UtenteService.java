package com.elismarket.eshop.services;

import com.elismarket.eshop.crudrepos.UtenteCrud;
import com.elismarket.eshop.dto.UtenteDTO;
import com.elismarket.eshop.interfaces.Utente;
import com.elismarket.eshop.model.UtenteImpl;
import com.elismarket.eshop.utilities.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Utente> getAll() {
        return utenteCrud.findAllBy();
    }

    public Utente getUtente(UtenteDTO utenteDTO) {
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
