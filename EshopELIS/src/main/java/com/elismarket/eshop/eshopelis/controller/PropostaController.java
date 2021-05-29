package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.service.PropostaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/proposta", produces = "application/json")
@CrossOrigin(origins = "*")
public class PropostaController {

    @Autowired
    private PropostaServiceImpl propostaService;

    @PostMapping("/add")
    public PropostaDTO addProposta(@RequestBody PropostaDTO propostaDTO) {
        return propostaService.addProposta(propostaDTO);
    }

    @PatchMapping("/update/{id}")
    public PropostaDTO updateProposta(@PathVariable Long id, @RequestBody PropostaDTO propostaDTO) {
        return propostaService.updateProposta(id, propostaDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeProposta(@RequestParam Long id) {
        return propostaService.removeProposta(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @GetMapping("/accettati/si")
    public List<PropostaDTO> findAccettati() {
        return propostaService.findAllByIsAccettato(true);
    }

    @GetMapping("/accettati/no")
    public List<PropostaDTO> findNonAccettati() {
        return propostaService.findAllByIsAccettato(false);
    }

    @GetMapping("/utente/{id}")
    public List<PropostaDTO> findAllByUtente(@RequestParam Long id) {
        return propostaService.findAllByUtente(id);
    }
}
