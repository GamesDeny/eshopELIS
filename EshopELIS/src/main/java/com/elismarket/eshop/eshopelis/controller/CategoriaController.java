package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.service.interfaces.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for {@link Categoria Categoria} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/api/categoria", produces = "application/json")
@CrossOrigin(origins = "*")
public class CategoriaController {

    /**
     * @see CategoriaService
     */
    @Autowired
    CategoriaService categoriaService;

    /**
     * Adds a Categoria to the DB
     *
     * @param categoriaDTO {@link CategoriaDTO CategoriaDTO} with required fields
     * @return the {@link Categoria Categoria} created
     */
    @PostMapping("/add")
    public CategoriaDTO addCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.addCategoria(categoriaDTO);
    }

    /**
     * Updates with HTTP Patch for a Categoria
     *
     * @param id           of the {@link Categoria Categoria} to Update
     * @param categoriaDTO {@link CategoriaDTO CategoriaDTO} with the information to update
     * @return Updated CategoriaDTO
     */
    @PatchMapping("/update/{id}")
    public CategoriaDTO updateCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.updateCategoria(id, categoriaDTO);
    }

    /**
     * Delete of a {@link Categoria Categoria}
     *
     * @param id of the {@link Categoria Categoria} to delete
     * @return HTTP 200 if deleted successfully, else 500
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> deleteCategoria(@PathVariable Long id) {
        return categoriaService.deleteCategoria(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    /**
     * returns all Categoria in the DB
     *
     * @return List {@link Categoria Categoria}
     */
    @GetMapping("/all")
    public List<CategoriaDTO> getAllCategoria() {
        return categoriaService.getAll();
    }

    /**
     * Retrieves a Categoria for the provided item if exists
     *
     * @param id of the {@link Categoria Categoria} to retrieve
     * @return {@link Categoria Categoria} for provided id
     */
    @GetMapping("/id/{id}")
    public CategoriaDTO getCategoria(@PathVariable Long id) {
        return categoriaService.getById(id);
    }

}
