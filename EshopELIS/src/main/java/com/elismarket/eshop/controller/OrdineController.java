package com.elismarket.eshop.controller;

import com.elismarket.eshop.interfaces.Ordine;
import com.elismarket.eshop.model.OrdineImpl;
import com.elismarket.eshop.services.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/rest/ordine", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrdineController {
    @Autowired
    OrdineService ordineService;

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
