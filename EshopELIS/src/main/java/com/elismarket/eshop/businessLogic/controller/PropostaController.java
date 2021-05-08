package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/rest/proposta", produces = "application/json")
@CrossOrigin(origins = "*")
public class PropostaController {

    @Autowired
    private PropostaService propostaService;
}
