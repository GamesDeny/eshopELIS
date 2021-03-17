package com.elismarket.eshop.controller;

import com.elismarket.eshop.dto.ProdottoDTO;
import com.elismarket.eshop.interfaces.Prodotto;
import com.elismarket.eshop.model.ProdottoImpl;
import com.elismarket.eshop.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 *
 * Rest API for Products
 * Contains all informations for making API requests for frontend
 *
 */


@RestController
@RequestMapping(path = "/rest/prodotto", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    //returns all db users
    @GetMapping("/getall")
    public List<ProdottoImpl> getAll() {
        return prodottoService.getAll();
    }

    //returns only the admins
    @GetMapping("/getall/quantita/maggiore")
    public List<Prodotto> findByQuantitaMaggiore(Integer quantita) {
        return prodottoService.findByQuantitaMaggiore(quantita);
    }

    //returns only non-admin users
    @GetMapping("/getall/quantita/minore")
    public List<Prodotto> findByQuantitaMinore(Integer quantita) {
        return prodottoService.findByQuantitaMinore(quantita);
    }

    //returns a user depending on field
    @GetMapping("/getprodotto/categoria")
    public List<Prodotto> getByNomeCategoria(@RequestBody ProdottoDTO prodottoDTO) {
        return prodottoService.getProdottoByCategoria(prodottoDTO);
    }
}
