package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.service.interfaces.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * RestController for {@link Ordine Ordine} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/api/ordine", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrdineController {

    /**
     * @see OrdineService
     */
    @Autowired
    OrdineService ordineService;

    /**
     * Adds an Ordine
     *
     * @param userId      id of the {@link Utente Utente} that placed order
     * @param pagamentoId id of the {@link Pagamento Pagamento} that
     * @param righe       ids of the {@link RigaOrdine RigaOrdine} that are connected to the order
     * @return added Ordine
     */
    @PostMapping("/add/{userId}/{pagamentoId}")
    public OrdineDTO addOrdine(@PathVariable Long userId, @PathVariable Long pagamentoId, @RequestBody List<RigaOrdineDTO> righe) {
        return ordineService.saveOrdine(userId, pagamentoId, righe);
    }

    /**
     * Updates Ordine with the relative id with the info provided in the DTO
     *
     * @param id        of the {@link Ordine Ordine} to
     * @param ordineDTO {@link OrdineDTO OrdineDTO} with information to update
     * @return DTO of updated item
     */
    @PatchMapping("/update/{id}")
    public OrdineDTO updateOrdine(@PathVariable Long id, @RequestBody OrdineDTO ordineDTO) {
        return ordineService.updateOrdine(id, ordineDTO);
    }

    /**
     * Delete of Ordine with relative id
     *
     * @param id of the {@link Ordine Ordine} to
     * @return HTTP status 200 if item is deleted otherwise 500
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeOrdine(@PathVariable Long id) {
        return ordineService.removeOrdine(id) ? ResponseEntity.status(500).build() : ResponseEntity.status(200).build();
    }

    /**
     * Returns all Ordine in the DB
     *
     * @return List of {@link OrdineDTO OrdineDTO}
     */
    @GetMapping("/all")
    public List<OrdineDTO> getAll() {
        return ordineService.getAll();
    }

    /**
     * Returns Ordine for provided id
     *
     * @param id of the {@link Ordine Ordine}
     * @return {@link OrdineDTO OrdineDTO}
     */
    @GetMapping("/id/{id}")
    public OrdineDTO getById(@PathVariable Long id) {
        return ordineService.getById(id);
    }

    /**
     * Returns all orders for an Utente
     *
     * @param userId id of the {@link Utente Utente}
     * @return List of {@link OrdineDTO OrdineDTO}
     */
    @GetMapping("/all/utente/{userId}")
    public List<OrdineDTO> getAllByUtente(@PathVariable Long userId) {
        return ordineService.getAllByUtente(userId);
    }

    /**
     * Returns all ordini evasi depending on variable
     *
     * @param evaso value of evaso
     * @return List of {@link OrdineDTO OrdineDTO} evasi or not
     */
    @GetMapping("/evaso/{evaso}")
    public List<OrdineDTO> getEvaso(@PathVariable Boolean evaso) {
        return ordineService.getEvaso(evaso);
    }

    /**
     * Set isEvaso of an Ordine to false and restore quantita of the Prodotto in the Ordine
     *
     * @param id the Ordine to evade
     * @return List {@link OrdineDTO OrdineDTO}
     * @see Ordine#getDataEvasione()
     */
    @GetMapping("/evasione/{id}")
    public OrdineDTO getNotEvaded(@PathVariable Long id) {
        return ordineService.setEvadiFalse(id);
    }

    /**
     * Adds a RigaOrdine to the relative Ordine
     *
     * @param ordineId      id of {@link Ordine Ordine}
     * @param rigaOrdineDTO {@link RigaOrdine RigaOrdine} to add
     * @return the inserted data
     */
    @PostMapping("/add/rigaordine/{ordineId}")
    public RigaOrdine addRigaOrdineToOrdine(@PathVariable Long ordineId, @RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return ordineService.addRigaOrdineToOrdine(ordineId, rigaOrdineDTO);
    }
}
