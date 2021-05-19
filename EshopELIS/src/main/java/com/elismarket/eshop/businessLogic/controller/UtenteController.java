package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.UtenteService;
import com.elismarket.eshop.model.dto.UtenteDTO;
import com.elismarket.eshop.model.entities.UtenteImpl;
import com.elismarket.eshop.model.interfaces.Utente;
import com.elismarket.eshop.utilities.UtenteFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


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
        return utenteService.getAll("");
    }

    //returns only the admins
    @GetMapping("/all/admin")
    public List<Utente> getAllAdmin() {
        return utenteService.getAll("admin");
    }

    //returns only non-admin users
    @GetMapping("/all/user")
    public List<Utente> getAllUsers() {
        return utenteService.getAll("user");
    }

    //returns a user depending on field
    @GetMapping("/username/{username}")
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

    @PostMapping("/login")
    public Utente getLoginUtente(@RequestBody UtenteImpl utente) {
        return utenteService.getLoginUtente(utente.getUsername(), utente.getPassword());
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

    @PatchMapping("/update/{id}")
    public ResponseEntity updateUtente(@PathVariable("id") Long id, @RequestBody UtenteDTO utenteDTO) {
        Utente u = utenteService.getUtente(id);

        //TODO prendi utentem modifica parametri e invia risposta. fallo per tutti i parametri e tutti i patch delle Entities
        //se non dovesse funzionare vai di switch senza break nel case
        u.setUsername(Objects.isNull(utenteDTO.getUsername()) ? u.getUsername() : utenteDTO.username);

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
