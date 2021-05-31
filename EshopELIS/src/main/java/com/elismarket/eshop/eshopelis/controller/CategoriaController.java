package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/categoria", produces = "application/json")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @PostMapping("/add")
    public CategoriaDTO addCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.addCategoria(categoriaDTO);
    }

    @PatchMapping("/update/{id}")
    public CategoriaDTO updateCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.updateCategoria(id, categoriaDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCategoria(@PathVariable Long id) {
        return categoriaService.deleteCategoria(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @GetMapping("/all")
    public List<CategoriaDTO> getAllCategoria() {
        return categoriaService.getAll();
    }

    @GetMapping("/id/{id}")
    public CategoriaDTO getCategoria(@PathVariable Long id) {
        return categoriaService.getById(id);
    }

}
