package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.PagamentoService;
import com.elismarket.eshop.model.dto.PagamentoDTO;
import com.elismarket.eshop.model.entities.PagamentoImpl;
import com.elismarket.eshop.model.interfaces.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/update")
    public Boolean updatePagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.updatePagamento(pagamentoDTO);
    }

    @DeleteMapping("/remove/{id}")
    public void removePagamento(@PathVariable("id") Long id) {
        pagamentoService.removePagamento(id);
    }

}
