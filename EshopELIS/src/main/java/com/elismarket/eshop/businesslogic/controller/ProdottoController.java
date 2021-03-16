package com.elismarket.eshop.businesslogic.controller;

import com.elismarket.eshop.businesslogic.services.ProdottoService;
import com.elismarket.eshop.interfaces.Prodotto;
import com.elismarket.eshop.model.ProdottoImpl;
import com.elismarket.eshop.model.UtenteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SessionAttributes({"utente"})
@RequestMapping(path="/rest/prodotti", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @ModelAttribute("prodotto")
    public Prodotto prodotto(){
        return new ProdottoImpl();
    }

    @GetMapping("/aggiungiProdotto")
    public String aggiungiProdotto(@RequestBody ProdottoImpl prodotto, @RequestParam("utente")UtenteImpl utente, Model model){
        //se va a buon fine, aggiunta corre, altrimento errore
        if(utente.getIsAdmin())
            model.addAttribute("esito", prodottoService.saveProdotto(prodotto)?"Prodotto aggiunto correttamente":"Errore nell'aggiunta");
        else
            model.addAttribute("esito", "Non sei amministratore!");
        return "esito";
    }

    @GetMapping(path = "/cerca/prodotto",consumes = "application/json" )
    @ResponseStatus( HttpStatus.CREATED )
    public String cercaProdotto(String nome, Model model){
        List<Prodotto> prodotti = prodottoService.findAllByNome(nome);

        if(prodotti.size() == 0) {
            model.addAttribute("esito", "Errore nella ricerca dei prodotti");
            return "esito";
        }

        model.addAttribute("prodotti", prodotti);
        return "risultatiRicercaProdotti";
    }

    /*
    * try{
            PersonaBean personaBean = ricercaPerId(id);

            HttpStatus httpStatus = personaBean != null? HttpStatus.OK : HttpStatus.NOT_FOUND;
            return new ResponseEntity<PersonaBean>(personaBean, httpStatus);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<PersonaBean>(new PersonaBean(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    * */
}
