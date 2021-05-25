package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.service.PagamentoServiceImpl;
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
    private PagamentoServiceImpl pagamentoService;

    @PostMapping("/add")
    public PagamentoDTO addPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.addPagamento(pagamentoDTO);
    }

    @PatchMapping("/update/{id}")
    public PagamentoDTO updatePagamento(@PathVariable("id") Long id, @RequestBody PagamentoDTO pagamentoDTO) {
        Pagamento p = Pagamento.of(pagamentoService.getById(id));

        pagamentoDTO.setId(id);
        pagamentoDTO.setDescrizione(Objects.isNull(pagamentoDTO.getDescrizione()) ? p.getDescrizione() : pagamentoDTO.descrizione);
        pagamentoDTO.setContanti(Objects.isNull(pagamentoDTO.getContanti()) ? p.getContanti() : pagamentoDTO.contanti);
        pagamentoDTO.setPaypalMail(Objects.isNull(pagamentoDTO.getPaypalMail()) ? p.getPaypalMail() : pagamentoDTO.paypalMail);
        pagamentoDTO.setTipo(Objects.isNull(pagamentoDTO.getTipo()) ? p.getTipo() : pagamentoDTO.tipo);

        return pagamentoService.addPagamento(pagamentoDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removePagamento(@PathVariable("id") Long id) {
        return pagamentoService.removePagamento(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }


    @GetMapping("/all")
    public List<PagamentoDTO> getAll() {
        return pagamentoService.getAll();
    }

    @GetMapping("/contanti")
    public List<PagamentoDTO> getByContanti() {
        return pagamentoService.getByContanti();
    }

    @GetMapping("/mail")
    public List<PagamentoDTO> getByPaypalMail() {
        return pagamentoService.getByPaypalMail();
    }

}