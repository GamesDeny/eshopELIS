package com.elismarket.eshop.controller;

import com.elismarket.eshop.dto.ProdottoDTO;
import com.elismarket.eshop.interfaces.Prodotto;
import com.elismarket.eshop.model.ProdottoImpl;
import com.elismarket.eshop.model.UtenteImpl;
import com.elismarket.eshop.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 *
 * Rest API for Products
 * Contains all informations for making API requests for frontend
 *
 */


@RestController
@SessionAttributes({"utente"})
@RequestMapping(path = "/rest/prodotti", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @ModelAttribute("prodotto")
    public Prodotto prodotto() {
        return new ProdottoImpl();
    }

    @GetMapping("/aggiungiProdotto")
    public String aggiungiProdotto(@RequestBody ProdottoDTO prodotto, @RequestParam("utente") UtenteImpl utente, Model model) {
        //se va a buon fine, aggiunta corre, altrimento errore
        if (utente.getIsAdmin())
            model.addAttribute("esito", prodottoService.saveProdotto(prodotto) ? "Prodotto aggiunto correttamente" : "Errore nell'aggiunta");
        else
            model.addAttribute("esito", "Non sei amministratore!");
        return "esito";
    }

    //other way to do it
    @GetMapping(path = "/cerca/prodotto", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public String cercaProdotto(String nome, Model model) {
        List<Prodotto> prodotti = prodottoService.findAllByNome(nome);

        if (prodotti.size() == 0) {
            model.addAttribute("esito", "Errore nella ricerca dei prodotti");
            return "esito";
        }

        model.addAttribute("prodotti", prodotti);
        return "risultatiRicercaProdotti";
    }
}
