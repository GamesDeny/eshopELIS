package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.service.OrdineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/rest/ordine", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrdineController {
    @Autowired
    private OrdineServiceImpl ordineService;

    @PostMapping("/add")
    public OrdineDTO addOrdine(@RequestBody OrdineDTO ordineDTO) {
        return ordineService.saveOrdine(ordineDTO);
    }

    @PatchMapping("/update/{id}")
    public OrdineDTO updateOrdine(@PathVariable("id") Long id, @RequestBody OrdineDTO ordineDTO) {
        Ordine o = ordineService.getById(id);

        ordineDTO.setId(id);
        ordineDTO.setEvaso(Objects.isNull(ordineDTO.getEvaso()) ? o.getEvaso() : ordineDTO.evaso);
        ordineDTO.setDataEvasione(Objects.isNull(ordineDTO.getDataEvasione()) ? o.getDataEvasione() : ordineDTO.dataEvasione);

        return ordineService.saveOrdine(ordineDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeOrdine(@PathVariable("id") Long id) {
        return ordineService.removeOrdine(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @GetMapping("/all")
    public List<OrdineDTO> getAll() {
        return ordineService.getAll();
    }

    @GetMapping("/evaso")
    public List<OrdineDTO> getEvaso(Boolean evaso) {
        return ordineService.getEvaso(evaso);
    }

    @GetMapping("/data/before/{data}")
    public List<OrdineDTO> getDataPrima(@PathVariable("data") LocalDate dataEvasione) {
        return ordineService.getDataPrima(dataEvasione);
    }

    @GetMapping("/data/between/{dataInizio}/{dataFine}")
    public List<OrdineDTO> getDataTra(@PathVariable("dataInizio") LocalDate dataEvasione1, @PathVariable("dataFine") LocalDate dataEvasione2) {
        return ordineService.getDataTra(dataEvasione1, dataEvasione2);
    }

    @GetMapping("/data/after/{data}")
    public List<OrdineDTO> getDataDopo(@PathVariable("data") LocalDate dataEvasione) {
        return ordineService.getDataDopo(dataEvasione);
    }

}
