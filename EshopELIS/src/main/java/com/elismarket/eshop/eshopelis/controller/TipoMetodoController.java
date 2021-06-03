package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import com.elismarket.eshop.eshopelis.model.TipoMetodo;
import com.elismarket.eshop.eshopelis.service.interfaces.TipoMetodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for {@link TipoMetodo TipoMetodo} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/api/tipometodo", produces = "application/json")
@CrossOrigin(origins = "*")
public class TipoMetodoController {

    /**
     * @see TipoMetodoService
     */
    @Autowired
    TipoMetodoService tipoMetodoService;

    /**
     * Adds a new TipoMetodo
     *
     * @param tipoMetodoDTO {@link TipoMetodoDTO TipoMetodoDTO}
     * @return added TipoMetodo
     */
    @PostMapping("/add")
    public TipoMetodoDTO addTipoMetodo(@RequestBody TipoMetodoDTO tipoMetodoDTO) {
        return tipoMetodoService.addTipoMetodo(tipoMetodoDTO);
    }

    /**
     * Updates TipoMetodo related to the id with updated fields in TipoMetodoDTO
     *
     * @param id            of the {@link TipoMetodo TipoMetodo}
     * @param tipoMetodoDTO {@link TipoMetodoDTO TipoMetodoDTO} with updated fields
     * @return updated TipoMetodo
     */
    @PatchMapping("/update/{id}")
    public TipoMetodoDTO updateTipoMetodo(@PathVariable Long id, @RequestBody TipoMetodoDTO tipoMetodoDTO) {
        return tipoMetodoService.updateTipoMetodo(id, tipoMetodoDTO);
    }

    /**
     * Removes the TipoMetodo related to the id
     *
     * @param id of the {@link TipoMetodo TipoMetodo}
     * @return HTTP 200 if deleted successfully, else 500
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> deleteTipoMetodo(@PathVariable Long id) {
        return tipoMetodoService.deleteTipoMetodo(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    /**
     * Retrieves all TipoMetodo in DB
     *
     * @return List {@link TipoMetodoDTO TipoMetodoDTO}
     */
    @GetMapping("/all")
    public List<TipoMetodoDTO> getAllTipoMetodo() {
        return tipoMetodoService.getAll();
    }

    /**
     * Retrieves the TipoMetodo related to the id
     *
     * @param id of the {@link TipoMetodo TipoMetodo}
     * @return retrieved TipoMetodo
     */
    @GetMapping("/id/{id}")
    public TipoMetodoDTO getTipoMetodo(@PathVariable Long id) {
        return tipoMetodoService.getById(id);
    }
}
