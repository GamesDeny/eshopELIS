package com.elismarket.eshop.businesslogic.controller;

import com.elismarket.eshop.businesslogic.services.ProdottoService;
import com.elismarket.eshop.interfaces.Prodotto;
import com.elismarket.eshop.model.ProdottoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @ModelAttribute("prodotto")
    public Prodotto prodotto(){
        return new ProdottoImpl();
    }

    @GetMapping("/aggiungiProdotto")
    public String aggiungiProdotto(ProdottoImpl prodotto, Model model){
        //se va a buon fine, aggiunta corre, altrimento errore
        model.addAttribute("esito", prodottoService.saveProdotto(prodotto)?"Prodotto aggiunto correttamente":"Errore nell'aggiunta");
        return "esito";
    }

    @GetMapping("/aggiungiProdotto")
    public String aggiungiProdotto(String nome, String descrizione, Float prezzo, Integer minOrd, Integer maxOrd, Integer sconto, Model model){
        //se va a buon fine, aggiunta corre, altrimento errore
        ProdottoImpl prodotto = new ProdottoImpl(nome,descrizione,prezzo,minOrd, maxOrd, sconto, null);
        model.addAttribute("esito", prodottoService.saveProdotto(prodotto)?"Prodotto aggiunto correttamente":"Errore nell'aggiunta");
        return "esito";
    }

    @GetMapping("/cercaProdotto")
    public String cercaProdotto(String nome, Model model){
        List<Prodotto> prodotti = prodottoService.findAllByNome(nome);

        if(prodotti.size() == 0) {
            model.addAttribute("esito", "Errore nella ricerca dei prodotti");
            return "esito";
        }

        model.addAttribute("prodotti", prodotti);
        return "risultatiRicercaProdotti";
    }
}
