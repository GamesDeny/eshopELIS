package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.RigaOrdineService;
import com.elismarket.eshop.model.dto.RigaOrdineDTO;
import com.elismarket.eshop.model.entities.RigaOrdineImpl;
import com.elismarket.eshop.model.interfaces.RigaOrdine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/rigaordine", produces = "application/json")
@CrossOrigin(origins = "*")
public class RigaOrdineController {
    @Autowired
    private RigaOrdineService rigaOrdineService;

    @GetMapping("/all")
    public Iterable<RigaOrdineImpl> getAll() {
        return rigaOrdineService.getAll();
    }

    @GetMapping("/quantita/{value}")
    public List<RigaOrdine> getByQuantita(Integer quantita) {
        return rigaOrdineService.getByQuantita(quantita);
    }

    @PatchMapping("/update")
    public Boolean updateRigaOrdine(@RequestBody RigaOrdineDTO rigaOrdineDTO) {
        try {
            rigaOrdineService.updateRigaOrdine(rigaOrdineDTO);
            return true;
        } catch (Exception e) {
//            throw new RigaOrdineException("Aggiornamento non riuscito, ricontrolla i dati inviati!");
        }
        return false;
    }

    @DeleteMapping("/remove/{id}")
    public void removeRigaOrdine(@PathVariable("id") Long id) {
        rigaOrdineService.removeRigaOrdine(id);
    }

}