package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.UtenteService;
import com.elismarket.eshop.customExceptions.UtenteException;
import com.elismarket.eshop.model.dto.UtenteDTO;
import com.elismarket.eshop.model.interfaces.Utente;
import com.elismarket.eshop.utilities.UtenteFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/mail/{mail}")
    public Utente getByMail(@PathVariable("mail") UtenteDTO utenteDTO) {
        return utenteService.getUtente(utenteDTO, UtenteFields.mail.toString());
    }

    @GetMapping("/sigla/{siglaResidenza}")
    public Utente getBySiglaResidenza(@PathVariable("siglaResidenza") Integer siglaResidenza) {
        return utenteService.getUtente(siglaResidenza);
    }

    @PostMapping("/add")
    public ResponseEntity addUtente(@RequestBody UtenteDTO utenteDTO) {
        try {
            utenteService.addUtente(utenteDTO);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/update")
    public ResponseEntity updateUtente(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.addUtente(utenteDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity removeRigaOrdine(@PathVariable("id") Long id) {
        try {
            utenteService.removeUtente(id);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
