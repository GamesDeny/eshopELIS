package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.PagamentoService;
import com.elismarket.eshop.model.dto.PagamentoDTO;
import com.elismarket.eshop.model.entities.PagamentoImpl;
import com.elismarket.eshop.model.interfaces.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/rest/pagamento", produces = "application/json")
@CrossOrigin(origins = "*")
public class PagamentoController {
    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/all")
    public Iterable<PagamentoImpl> getAll() {
        return pagamentoService.getAll();
    }

    @GetMapping("/contanti")
    public List<Pagamento> getByContanti() {
        return pagamentoService.getByContanti();
    }

    @GetMapping("/mail")
    public List<Pagamento> getByPaypalMail() {
        return pagamentoService.getByPaypalMail();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.addPagamento(pagamentoDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updatePagamento(@PathVariable("id") Long id, @RequestBody PagamentoDTO pagamentoDTO) {
        Pagamento p = pagamentoService.getById(id);

        pagamentoDTO.setId(id);
        pagamentoDTO.setDescrizione(Objects.isNull(pagamentoDTO.getDescrizione()) ? p.getDescrizione() : pagamentoDTO.descrizione);
        pagamentoDTO.setContanti(Objects.isNull(pagamentoDTO.getContanti()) ? p.getContanti() : pagamentoDTO.contanti);
        pagamentoDTO.setPaypalMail(Objects.isNull(pagamentoDTO.getPaypalMail()) ? p.getPaypalMail() : pagamentoDTO.paypalMail);
        pagamentoDTO.setTipo(Objects.isNull(pagamentoDTO.getTipo()) ? p.getTipo() : pagamentoDTO.tipo);

        return pagamentoService.addPagamento(pagamentoDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @DeleteMapping("/remove/{id}")
    public void removePagamento(@PathVariable("id") Long id) {
        pagamentoService.removePagamento(id);
    }

}
