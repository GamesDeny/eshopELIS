package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.service.RigaOrdineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/rest/rigaordine", produces = "application/json")
@CrossOrigin(origins = "*")
public class RigaOrdineController {
    @Autowired
    private RigaOrdineServiceImpl rigaOrdineService;

    @GetMapping("/all")
    public Iterable<RigaOrdineDTO> getAll() {
        return rigaOrdineService.getAll();
    }

    @GetMapping("/quantita/{value}")
    public List<RigaOrdineDTO> getByQuantita(Integer quantita) {
        return rigaOrdineService.getByQuantita(quantita);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addRigaOrdine(@RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return rigaOrdineService.addRigaOrdine(rigaOrdineDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateRigaOrdine(@PathVariable("id") Long id, @RequestBody RigaOrdineDTO rigaOrdineDTO) {
        RigaOrdine r = RigaOrdine.of(rigaOrdineService.getById(id));

        rigaOrdineDTO.setId(id);
        rigaOrdineDTO.setPrezzoTotale(Objects.isNull(rigaOrdineDTO.getPrezzoTotale()) ? r.getPrezzoTotale() : rigaOrdineDTO.prezzoTotale);
        rigaOrdineDTO.setQuantitaProdotto(Objects.isNull(rigaOrdineDTO.getQuantitaProdotto()) ? r.getQuantitaProdotto() : rigaOrdineDTO.quantitaProdotto);
        rigaOrdineDTO.setScontoApplicato(Objects.isNull(rigaOrdineDTO.getScontoApplicato()) ? r.getScontoApplicato() : rigaOrdineDTO.scontoApplicato);

        return rigaOrdineService.addRigaOrdine(rigaOrdineDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeRigaOrdine(@PathVariable("id") Long id) {
        return rigaOrdineService.removeRigaOrdine(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

}