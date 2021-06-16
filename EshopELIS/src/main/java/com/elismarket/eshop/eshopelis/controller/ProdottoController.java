package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.service.interfaces.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for {@link Prodotto Prodotto} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/api/prodotto", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProdottoController {

    /**
     * @see ProdottoService
     */
    @Autowired
    ProdottoService prodottoService;

    /**
     * Adds a Prodotto
     *
     * @param prodottoDTO {@link ProdottoDTO ProdottoDTO} to add
     * @return Added Prodotto
     */
    @PostMapping("/add")
    public ProdottoDTO addProdotto(@RequestBody ProdottoDTO prodottoDTO) {
        return prodottoService.addProdotto(prodottoDTO);
    }

    /**
     * Updates Prodotto relative to the id
     *
     * @param id          of {@link Prodotto Prodotto}
     * @param prodottoDTO {@link ProdottoDTO ProdottoDTO} with updated fields
     * @return updated Prodotto
     */
    @PatchMapping("/update/{id}")
    public ProdottoDTO updateProdotto(@PathVariable Long id, @RequestBody ProdottoDTO prodottoDTO) {
        return prodottoService.updateProdotto(id, prodottoDTO);
    }

    /**
     * Deletes the Prodotto for the provided id
     *
     * @param id of the {@link Prodotto Prodotto} to remove
     * @return HTTP 200 if deleted successfully, else 500
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeProdotto(@PathVariable Long id) {
        return prodottoService.removeProdotto(id) ? ResponseEntity.status(500).build() : ResponseEntity.status(200).build();
    }

    /**
     * Retrieves Prodotto for provided id
     *
     * @param id of the {@link Prodotto Prodotto} to retrieve
     * @return {@link ProdottoDTO ProdottoDTO}
     */
    @GetMapping("/id/{id}")
    public ProdottoDTO getById(@PathVariable Long id) {
        return prodottoService.getById(id);
    }

    /**
     * Retrieves all Prodotto
     *
     * @return List {@link ProdottoDTO ProdottoDTO}
     */
    @GetMapping("/all")
    public List<ProdottoDTO> getAll() {
        return prodottoService.getAll();
    }

    /**
     * Retrieves all Prodotto added by a Utente
     *
     * @param userId id of {@link Utente Utente} that has an accepted Proposta
     * @return List {@link ProdottoDTO ProdottoDTO} of Proposta that became a Prodotto
     */
    @GetMapping("/all/utente/{userId}")
    public List<ProdottoDTO> findAllByUtente(@PathVariable Long userId) {
        return prodottoService.findAllByUtente(userId);
    }

    /**
     * Retrieves all Prodotto for a {@link Categoria Categoria}
     *
     * @param categoriaId id of the Categoria to search
     * @return List {@link ProdottoDTO ProdottoDTO}
     */
    @GetMapping("/all/categoria/{categoriaId}")
    public List<ProdottoDTO> getByNomeCategoria(@PathVariable Long categoriaId) {
        return prodottoService.getProdottoByCategoria(categoriaId);
    }

    /**
     * Adds a {@link RigaOrdine RigaOrdine} to a Prodotto
     *
     * @param prodId        id of the {@link Prodotto Prodotto} to link to the RigaOrdine
     * @param rigaOrdineDTO {@link RigaOrdineDTO RigaOrdineDTO}
     * @return added RigaOrdine
     */
    @PostMapping("/add/rigaOrdine/{prodId}")
    public RigaOrdine addRigaOrdineToProdotto(@PathVariable Long prodId, @RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return prodottoService.addRigaOrdineToProdotto(prodId, rigaOrdineDTO);
    }


    /**
     * Returns all {@link Prodotto Prodotto} ordered by an Utente
     *
     * @param utenteId id of the {@link Utente Utente}
     * @return List of the Prodotti related to the user
     */
    @PostMapping("/get/prodotti/{utenteId}")
    public List<ProdottoDTO> getProdottoOfOrdine(@PathVariable Long utenteId) {
        return prodottoService.getProdottoOfOrdine(utenteId);
    }
}
