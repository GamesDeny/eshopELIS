package com.elismarket.eshop.businesslogic.controller;

import com.elismarket.eshop.businesslogic.services.UtenteService;
import com.elismarket.eshop.interfaces.Utente;
import com.elismarket.eshop.model.UtenteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes({"utente"})
public class LoginServlet {
    @Autowired
    private UtenteService utenteService;

    @ModelAttribute("utente")
    public Utente createUtente(){
        return new UtenteImpl();
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String user, @RequestParam("password") String pass, Model model){
        List<Utente> utenti = utenteService.getAllUtenti(user, pass);

        if(utenti.size() == 1) {
            (utenti.get(0)).setLogged();
            model.addAttribute("utente", utenti.get(0));
            return "homeInterna";
        }
        model.addAttribute("messaggio", "Utente non trovato!");
        return "paginaLogin";
    }

    @GetMapping("/logout")
    public String logout(Model model, @ModelAttribute("utente") Utente utente){
        utente.setLogged();
        return "paginaLogin";
    }
}
