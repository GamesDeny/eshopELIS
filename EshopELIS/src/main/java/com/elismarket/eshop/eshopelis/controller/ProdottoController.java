package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.service.interfaces.ProdottoService;
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
@RequestMapping(path = "/api/prodotto", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProdottoController {
    @Autowired
    ProdottoService prodottoService;

    @PostMapping("/add")
    public ProdottoDTO addProdotto(@RequestBody ProdottoDTO prodottoDTO) {
        return prodottoService.addProdotto(prodottoDTO);
    }

    @PatchMapping("/update/{id}")
    public ProdottoDTO updateProdotto(@PathVariable Long id, @RequestBody ProdottoDTO prodottoDTO) {
        return prodottoService.updateProdotto(id, prodottoDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeProdotto(@PathVariable Long id) {
        return prodottoService.removeProdotto(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    //returns all db products
    @GetMapping("/all")
    public List<ProdottoDTO> getAll() {
        return prodottoService.getAll();
    }

    @GetMapping("/all/utente/{userId}")
    public List<ProdottoDTO> findAllByUtente(@PathVariable Integer userId) {
        return prodottoService.findAllByUtente(userId);
    }

    @GetMapping("/all/quantita/minore/{quantita}")
    public List<ProdottoDTO> findByQuantitaMinore(@PathVariable Integer quantita) {
        return prodottoService.findByQuantitaMinore(quantita);
    }

    @GetMapping("/all/categoria/{categoriaId}")
    public List<ProdottoDTO> getByNomeCategoria(@PathVariable Long categoriaId) {
        return prodottoService.getProdottoByCategoria(categoriaId);
    }

    @PostMapping("/add/rigaOrdine/{prodId}")
    public RigaOrdine addRigaOrdineToProdotto(@PathVariable Long prodId, @RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return prodottoService.addRigaOrdineToProdotto(prodId, rigaOrdineDTO);
    }
}
