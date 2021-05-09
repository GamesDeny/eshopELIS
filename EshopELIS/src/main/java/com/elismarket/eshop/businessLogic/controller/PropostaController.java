package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.PropostaService;
import com.elismarket.eshop.model.dto.PropostaDTO;
import com.elismarket.eshop.model.interfaces.Proposta;
import com.elismarket.eshop.model.interfaces.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/proposta", produces = "application/json")
@CrossOrigin(origins = "*")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;

    @GetMapping("/accettati/si")
    public List<Proposta> findAccettati() {
        return propostaService.findAllByIsAccettato(true);
    }

    @GetMapping("/accettati/no")
    public List<Proposta> findNonAccettati() {
        return propostaService.findAllByIsAccettato(false);
    }

    @GetMapping("/utente/{id}")
    public List<Proposta> findAllByUtente(@RequestParam("id") Long id) {
        return propostaService.findAllByUtente(id);
    }

    @GetMapping("/utente")
    public List<Proposta> findAllByUtente(@RequestBody Utente utente) {
        return propostaService.findAllByUtente(utente);
    }

    @PostMapping("/add")
    public ResponseEntity addProposta(@RequestBody PropostaDTO propostaDTO) {
        return propostaService.addProposta(propostaDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @PatchMapping("/update")
    public ResponseEntity updateProposta(@RequestBody PropostaDTO propostaDTO) {
        return propostaService.addProposta(propostaDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity removeProposta(@RequestParam("id") Long id) {
        return propostaService.removeProposta(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }
}
