package com.elismarket.eshop.businesslogic.controller;

import com.elismarket.eshop.businesslogic.services.UtenteService;
import com.elismarket.eshop.interfaces.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SessionAttributes({"utente"})
@RequestMapping(path="/rest/utenti", produces = "application/json")
@CrossOrigin(origins = "*")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @GetMapping("/getAll")
    public List<Utente> getAll(){
        return utenteService.getAll();
    }
}
