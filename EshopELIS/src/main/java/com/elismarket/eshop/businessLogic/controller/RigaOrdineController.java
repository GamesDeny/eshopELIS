package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.RigaOrdineService;
import com.elismarket.eshop.model.dto.RigaOrdineDTO;
import com.elismarket.eshop.model.entities.RigaOrdineImpl;
import com.elismarket.eshop.model.interfaces.RigaOrdine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/add")
    public ResponseEntity addRigaOrdine(@RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return rigaOrdineService.addRigaOrdine(rigaOrdineDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @PatchMapping("/update")
    public ResponseEntity updateRigaOrdine(@RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return rigaOrdineService.addRigaOrdine(rigaOrdineDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity removeRigaOrdine(@PathVariable("id") Long id) {
        return rigaOrdineService.removeRigaOrdine(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

}