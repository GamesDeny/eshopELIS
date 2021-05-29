package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.service.RigaOrdineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/rigaordine", produces = "application/json")
@CrossOrigin(origins = "*")
public class RigaOrdineController {
    @Autowired
    private RigaOrdineServiceImpl rigaOrdineService;


    @PostMapping("/add")
    public RigaOrdineDTO addRigaOrdine(@RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return rigaOrdineService.addRigaOrdine(rigaOrdineDTO);
    }

    @PatchMapping("/update/{id}")
    public RigaOrdineDTO updateRigaOrdine(@PathVariable Long id, @RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return rigaOrdineService.updateRigaOrdine(id, rigaOrdineDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeRigaOrdine(@PathVariable Long id) {
        return rigaOrdineService.removeRigaOrdine(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }


    @GetMapping("/all")
    public Iterable<RigaOrdineDTO> getAll() {
        return rigaOrdineService.getAll();
    }

    @GetMapping("/quantita/{value}")
    public List<RigaOrdineDTO> getByQuantita(@PathVariable Integer value) {
        return rigaOrdineService.getByQuantita(value);
    }

}