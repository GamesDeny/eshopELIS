package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.PagamentoService;
import com.elismarket.eshop.model.entities.PagamentoImpl;
import com.elismarket.eshop.model.interfaces.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/pagamento", produces = "application/json")
@CrossOrigin(origins = "*")
public class PagamentoController {
    @Autowired
    PagamentoService pagamentoService;

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
}
