package com.elismarket.eshop.businesslogic.services;

import com.elismarket.eshop.businesslogic.crudrepos.UtenteCrud;
import com.elismarket.eshop.interfaces.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteCrud utenteCrud;

    public List<Utente> getAll(){
        return  utenteCrud.findAllBy();
    }

    public List<Utente> getUtente(String user, String pass) {
        return utenteCrud.findAllByUsernameAndPassword(user, pass);
    }
    
    public Boolean insertUtente(String cognome, LocalDate dataNascita, String mail, String nome, String password, Integer siglaResidenza, String username){
        if(getUtente(username, password).size() > 0)
            return false;
        utenteCrud.insertUser(cognome, dataNascita, mail, nome, password, siglaResidenza, username);
        return true;
    }
}
