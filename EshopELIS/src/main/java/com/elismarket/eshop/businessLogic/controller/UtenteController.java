package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.UtenteService;
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
    @GetMapping("/getall")
    public List<Utente> getAll() {
        return utenteService.getAll("");
    }

    //returns only the admins
    @GetMapping("/getall/admin")
    public List<Utente> getAllAdmin() {
        return utenteService.getAll("admin");
    }

    //returns only non-admin users
    @GetMapping("/getall/user")
    public List<Utente> getAllUsers() {
        return utenteService.getAll("user");
    }

    //returns a user depending on field
    @GetMapping("/getutente/username")
    public Utente getByUsername(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.getUtente(utenteDTO, UtenteFields.username.toString());
    }

    @GetMapping("/getutente/mail")
    public Utente getByMail(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.getUtente(utenteDTO, UtenteFields.mail.toString());
    }

    @GetMapping("/getutente/datanascita")
    public Utente getByDataNascita(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.getUtente(utenteDTO, UtenteFields.dataNascita.toString());
    }

    @GetMapping("/getutente/siglaresidenza")
    public Utente getBySiglaResidenza(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.getUtente(utenteDTO, UtenteFields.siglaResidenza.toString());
    }
}
