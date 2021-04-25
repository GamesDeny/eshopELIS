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
    OrdineService ordineService;


    @PostMapping("/add")
    public void addOrdine(@RequestBody OrdineDTO ordineDTO) {
        ordineService.saveOrdine(ordineDTO);
    }

    @DeleteMapping("/remove/id")
    public void removeOrdine(@RequestParam("id") Long id) {
        ordineService.removeOrdine(id);
    }

    @GetMapping("/getall")
    public List<OrdineImpl> getAll() {
        return ordineService.getAll();
    }

    @GetMapping("/getevaso")
    public List<Ordine> getEvaso(Boolean evaso) {
        return ordineService.getEvaso(evaso);
    }

    @GetMapping("/getdata/before")
    public List<Ordine> getDataPrima(LocalDate dataEvasione) {
        return ordineService.getDataPrima(dataEvasione);
    }

    @GetMapping("/getdata/between")
    public List<Ordine> getDataTra(LocalDate dataEvasione1, LocalDate dataEvasione2) {
        return ordineService.getDataTra(dataEvasione1, dataEvasione2);
    }

    @GetMapping("/getdata/after")
    public List<Ordine> getDataDopo(LocalDate dataEvasione) {
        return ordineService.getDataDopo(dataEvasione);
    }
}
