package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.service.interfaces.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/pagamento", produces = "application/json")
@CrossOrigin(origins = "*")
public class PagamentoController {
    @Autowired
    PagamentoService pagamentoService;

    @PostMapping("/add")
    public PagamentoDTO addPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.addPagamento(pagamentoDTO);
    }

    @PatchMapping("/update/{id}")
    public PagamentoDTO updatePagamento(@PathVariable Long id, @RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.updatePagamento(id, pagamentoDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removePagamento(@PathVariable Long id) {
        return pagamentoService.removePagamento(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @GetMapping("/all")
    public List<PagamentoDTO> getAll() {
        List<PagamentoDTO> result = new ArrayList<>();

        pagamentoService.getAll().forEach(result::add);

        return result;
    }

    @GetMapping("/contanti")
    public List<PagamentoDTO> getByContanti() {
        return pagamentoService.getByContanti();
    }

    @GetMapping("/mail")
    public List<PagamentoDTO> getByPaypalMail() {
        return pagamentoService.getByPaypalMail();
    }

    @PostMapping("/add/ordine/{pagamentoId}")
    public Ordine addOrdineToPagamento(@PathVariable Long pagamentoId, @RequestBody OrdineDTO ordineDTO) {
        return pagamentoService.addOrdineToPagamento(pagamentoId, ordineDTO);
    }
}
