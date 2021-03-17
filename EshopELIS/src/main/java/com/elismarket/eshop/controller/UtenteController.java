package com.elismarket.eshop.controller;

import com.elismarket.eshop.dto.UtenteDTO;
import com.elismarket.eshop.interfaces.Utente;
import com.elismarket.eshop.services.UtenteService;
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
@SessionAttributes({"utente"})
@RequestMapping(path = "/rest/utenti", produces = "application/json")
@CrossOrigin(origins = "*")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("/getAll")
    public List<Utente> getAll() {
        return utenteService.getAll();
    }

    @GetMapping("/getUser")
    public Utente getByUsername(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.getUtente(utenteDTO);
    }


}
