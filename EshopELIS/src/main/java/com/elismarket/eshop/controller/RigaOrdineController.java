package com.elismarket.eshop.controller;

import com.elismarket.eshop.interfaces.RigaOrdine;
import com.elismarket.eshop.model.RigaOrdineImpl;
import com.elismarket.eshop.services.RigaOrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/rigaordine", produces = "application/json")
@CrossOrigin(origins = "*")
public class RigaOrdineController {
    @Autowired
    RigaOrdineService rigaOrdineService;

    @GetMapping("/getall")
    public List<RigaOrdineImpl> getAll() {
        return rigaOrdineService.getAll();
    }

    @GetMapping("/getquantita")
    public List<RigaOrdine> getByQuantita(Integer quantita) {
        return rigaOrdineService.getByQuantita(quantita);
    }
}
