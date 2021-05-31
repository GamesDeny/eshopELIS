package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/api/ordine", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrdineController {
    @Autowired
    OrdineService ordineService;

    @PostMapping("/add")
    public OrdineDTO addOrdine(@RequestBody List<RigaOrdineDTO> righe) {
        return ordineService.saveOrdine(righe);
    }

    @PatchMapping("/update/{id}")
    public OrdineDTO updateOrdine(@PathVariable Long id, @RequestBody OrdineDTO ordineDTO) {
        return ordineService.updateOrdine(id, ordineDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeOrdine(@PathVariable Long id) {
        return ordineService.removeOrdine(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @GetMapping("/all")
    public List<OrdineDTO> getAll() {
        return ordineService.getAll();
    }

    @GetMapping("/evaso/{evaso}")
    public List<OrdineDTO> getEvaso(@PathVariable Boolean evaso) {
        return ordineService.getEvaso(evaso);
    }

    @GetMapping("/data/before/{data}")
    public List<OrdineDTO> getDataPrima(@PathVariable LocalDate data) {
        return ordineService.getDataPrima(data);
    }

    @GetMapping("/data/between/{dataInizio}/{dataFine}")
    public List<OrdineDTO> getDataTra(@PathVariable LocalDate dataInizio, @PathVariable LocalDate dataFine) {
        return ordineService.getDataTra(dataInizio, dataFine);
    }

    @GetMapping("/data/after/{data}")
    public List<OrdineDTO> getDataDopo(@PathVariable LocalDate data) {
        return ordineService.getDataDopo(data);
    }

    @PostMapping("/add/rigaordine/{ordineId}")
    public RigaOrdine addRigaOrdineToOrdine(@PathVariable Long ordineId, @RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return ordineService.addRigaOrdineToOrdine(ordineId, rigaOrdineDTO);
    }
}
