package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.OrdineService;
import com.elismarket.eshop.model.dto.OrdineDTO;
import com.elismarket.eshop.model.entities.OrdineImpl;
import com.elismarket.eshop.model.interfaces.Ordine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/ordine", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrdineController {
    @Autowired
    private OrdineService ordineService;

    @PostMapping("/add")
    public void addOrdine(@RequestBody OrdineDTO ordineDTO) {
        ordineService.saveOrdine(ordineDTO);
    }

    @DeleteMapping("/remove/{id}")
    public void removeOrdine(@PathVariable("id") Long id) {
        ordineService.removeOrdine(id);
    }

    @PatchMapping("/update")
    public Boolean updateOrdine(@RequestBody OrdineDTO ordineDTO) {
        return ordineService.updateOrdine(ordineDTO);
    }

    @GetMapping("/all")
    public List<OrdineImpl> getAll() {
        return ordineService.getAll();
    }

    @GetMapping("/evaso")
    public List<Ordine> getEvaso(Boolean evaso) {
        return ordineService.getEvaso(evaso);
    }

    @GetMapping("/data/before/{data}")
    public List<Ordine> getDataPrima(@PathVariable("data") LocalDate dataEvasione) {
        return ordineService.getDataPrima(dataEvasione);
    }

    @GetMapping("/data/between/{dataInizio}/{dataFine}")
    public List<Ordine> getDataTra(@PathVariable("dataInizio") LocalDate dataEvasione1, @PathVariable("dataFine") LocalDate dataEvasione2) {
        return ordineService.getDataTra(dataEvasione1, dataEvasione2);
    }

    @GetMapping("/data/after/{data}")
    public List<Ordine> getDataDopo(@PathVariable("data") LocalDate dataEvasione) {
        return ordineService.getDataDopo(dataEvasione);
    }

}
