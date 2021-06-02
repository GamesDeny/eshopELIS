package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import com.elismarket.eshop.eshopelis.service.interfaces.TipoMetodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/tipometodo", produces = "application/json")
@CrossOrigin(origins = "*")
public class TipoMetodoController {

    @Autowired
    TipoMetodoService tipoMetodoService;

    @PostMapping("/add")
    public TipoMetodoDTO addTipoMetodo(@RequestBody TipoMetodoDTO tipoMetodoDTO) {
        return tipoMetodoService.addTipoMetodo(tipoMetodoDTO);
    }

    @PatchMapping("/update/{id}")
    public TipoMetodoDTO updateTipoMetodo(@PathVariable Long id, @RequestBody TipoMetodoDTO tipoMetodoDTO) {
        return tipoMetodoService.updateTipoMetodo(id, tipoMetodoDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTipoMetodo(@PathVariable Long id) {
        return tipoMetodoService.deleteTipoMetodo(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @GetMapping("/all")
    public List<TipoMetodoDTO> getAllTipoMetodo() {
        return tipoMetodoService.getAll();
    }

    @GetMapping("/id/{id}")
    public TipoMetodoDTO getTipoMetodo(@PathVariable Long id) {
        return tipoMetodoService.getById(id);
    }
}
