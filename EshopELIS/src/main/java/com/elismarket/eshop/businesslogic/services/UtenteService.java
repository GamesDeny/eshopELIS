package com.elismarket.eshop.businesslogic.services;

import com.elismarket.eshop.businesslogic.crudrepos.UtenteCrud;
import com.elismarket.eshop.interfaces.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteCrud utenteCrud;

    public List<Utente> getAllUtenti(String user, String pass) {
        return utenteCrud.findAllByUsernameAndPassword(user, pass);
    }
}
