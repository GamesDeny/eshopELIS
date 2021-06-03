package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.service.interfaces.RigaOrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for {@link RigaOrdine RigaOrdine} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/api/rigaordine", produces = "application/json")
@CrossOrigin(origins = "*")
public class RigaOrdineController {

    /**
     * @see RigaOrdineService
     */
    @Autowired
    RigaOrdineService rigaOrdineService;

    /**
     * Adds a new RigaOrdine
     *
     * @param rigaOrdineDTO {@link RigaOrdineDTO RigaOrdineDTO} with the fields
     * @return added RigaOrdine
     */
    @PostMapping("/add")
    public RigaOrdineDTO addRigaOrdine(@RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return rigaOrdineService.addRigaOrdine(rigaOrdineDTO);
    }

    /**
     * Updates RigaOrdine related to the id with the updated fields in the RigaOrdineDTO
     *
     * @param id            of the {@link RigaOrdine RigaOrdine} to update
     * @param rigaOrdineDTO {@link RigaOrdineDTO RigaOrdineDTO} with updated fields
     * @return updated RigaOrdine
     */
    @PatchMapping("/update/{id}")
    public RigaOrdineDTO updateRigaOrdine(@PathVariable Long id, @RequestBody RigaOrdineDTO rigaOrdineDTO) {
        return rigaOrdineService.updateRigaOrdine(id, rigaOrdineDTO);
    }

    /**
     * Remove the relative RigaOrdine
     *
     * @param id of the {@link RigaOrdine RigaOrdine} to remove
     * @return HTTP 200 if deleted successfully, else 500
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeRigaOrdine(@PathVariable Long id) {
        return rigaOrdineService.removeRigaOrdine(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    /**
     * Retrieves all RigaOrdine
     *
     * @return List {@link RigaOrdineDTO RigaOrdineDTO}
     */
    @GetMapping("/all")
    public List<RigaOrdineDTO> getAll() {
        return rigaOrdineService.getAll();
    }

    /**
     * Retrieves RigaOrdine for provided id
     *
     * @param id for the {@link RigaOrdine RigaOrdine}
     * @return List {@link RigaOrdineDTO RigaOrdineDTO}
     */
    @GetMapping("/id/{id}")
    public RigaOrdineDTO getById(@PathVariable Long id) {
        return rigaOrdineService.getById(id);
    }

}