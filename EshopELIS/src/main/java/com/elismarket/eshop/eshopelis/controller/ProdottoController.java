package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.service.ProdottoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private ProdottoServiceImpl prodottoService;

    @PostMapping("/add")
    public ProdottoDTO addProduct(@RequestBody ProdottoDTO prodottoDTO) {
        return prodottoService.saveProdotto(prodottoDTO);
    }

    @PatchMapping("/update/{id}")
    public ProdottoDTO updateProdotto(@PathVariable("id") Long id, @RequestBody ProdottoDTO prodottoDTO) {
        return prodottoService.updateProdotto(id, prodottoDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeProdotto(@PathVariable("id") Long id) {
        return prodottoService.removeProdotto(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    //returns all db products
    @GetMapping("/all")
    public List<ProdottoDTO> getAll() {
        return prodottoService.getAll();
    }

    @GetMapping("/all/quantita/maggiore/{quantita}")
    public List<ProdottoDTO> findByQuantitaMaggiore(@PathVariable("quantita") Integer quantita) {
        return prodottoService.findByQuantitaMaggiore(quantita);
    }

    @GetMapping("/all/quantita/minore/{quantita}")
    public List<ProdottoDTO> findByQuantitaMinore(@PathVariable("quantita") Integer quantita) {
        return prodottoService.findByQuantitaMinore(quantita);
    }

    @GetMapping("/all/categoria/{name}")
    public List<ProdottoDTO> getByNomeCategoria(@PathVariable("name") String categoria) {
        return prodottoService.getProdottoByCategoria(categoria);
    }

    @GetMapping("/categoria/all")
    public List<String> getAllCategoria() {
        return prodottoService.getAllCategoria();
    }

}
