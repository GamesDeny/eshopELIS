package com.elismarket.eshop.controller;

import com.elismarket.eshop.interfaces.Pagamento;
import com.elismarket.eshop.model.PagamentoImpl;
import com.elismarket.eshop.services.PagamentoService;
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

    @GetMapping("/getall")
    public List<PagamentoImpl> getAll() {
        return pagamentoService.getAll();
    }

    @GetMapping("/gettipo")
    public List<Pagamento> getByTipo(String tipo) {
        return pagamentoService.getByTipo(tipo);
    }

    @GetMapping("/getcontanti")
    public List<Pagamento> getByContanti() {
        return pagamentoService.getByContanti();
    }

    @GetMapping("/getmail")
    public List<Pagamento> getByPaypalMail() {
        return pagamentoService.getByPaypalMail();
    }
}
