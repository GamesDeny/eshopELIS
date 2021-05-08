package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.UtenteService;
import com.elismarket.eshop.customExceptions.UtenteException;
import com.elismarket.eshop.model.dto.UtenteDTO;
import com.elismarket.eshop.model.interfaces.Utente;
import com.elismarket.eshop.utilities.UtenteFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 *
 * Rest API for User
 * Contains all informations for making API requests for frontend
 *
 */


@RestController
@RequestMapping(path = "/rest/utente", produces = "application/json")
@CrossOrigin(origins = "*")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    //returns all db users
    //prima di ritornare la lista setto la password a null in modo che non sia trasportata
    @GetMapping("/all")
    public List<Utente> getAll() {
        List<Utente> result = utenteService.getAll("");

        if (result.size() == 0) {
            throw new UtenteException("Impossibile effettuare la get");
        } else {
            result.forEach(user -> user.setPassword(null));
        }

        return result;
    }

    //returns only the admins
    @GetMapping("/all/admin")
    public List<Utente> getAllAdmin() {
        List<Utente> result = utenteService.getAll("admin");

        result.forEach(user -> user.setPassword(null));

        return result;
    }

    //returns only non-admin users
    @GetMapping("/all/user")
    public List<Utente> getAllUsers() {
        List<Utente> result = utenteService.getAll("user");

        result.forEach(user -> user.setPassword(null));

        return result;
    }

    //returns a user depending on field
    @GetMapping("/utente/{username}")
    public Utente getByUsername(@PathVariable("username") UtenteDTO utenteDTO) {
        return utenteService.getUtente(utenteDTO, UtenteFields.username.toString());
    }

    @GetMapping("/utente/{mail}")
    public Utente getByMail(@PathVariable("mail") UtenteDTO utenteDTO) {
        return utenteService.getUtente(utenteDTO, UtenteFields.mail.toString());
    }

    @GetMapping("/utente/{siglaResidenza}")
    public Utente getBySiglaResidenza(@PathVariable("siglaResidenza") UtenteDTO utenteDTO) {
        return utenteService.getUtente(utenteDTO, UtenteFields.siglaResidenza.toString());
    }

    @PatchMapping("/utente/update}")
    public Boolean updateUtente(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.updateUtente(utenteDTO);
    }

    @DeleteMapping("/remove/{id}")
    public void removeRigaOrdine(@PathVariable("id") Long id) {
        utenteService.removeUtente(id);
    }

}
