package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.service.interfaces.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for {@link Pagamento Pagamento} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/api/pagamento", produces = "application/json")
@CrossOrigin(origins = "*")
public class PagamentoController {

    /**
     * @see PagamentoService
     */
    @Autowired
    PagamentoService pagamentoService;

    /**
     * adds a new Pagamento
     *
     * @param pagamentoDTO {@link PagamentoDTO PagamentoDTO} of the Pagamento to add
     * @return DTO of created item
     */
    @PostMapping("/add")
    public PagamentoDTO addPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.addPagamento(pagamentoDTO);
    }

    /**
     * Updates an existing Pagamento
     *
     * @param id           of the {@link Pagamento Pagamento} to update
     * @param pagamentoDTO {@link PagamentoDTO PagamentoDTO} of the Pagamento to update
     * @return DTO of updated item
     */
    @PatchMapping("/update/{id}")
    public PagamentoDTO updatePagamento(@PathVariable Long id, @RequestBody PagamentoDTO pagamentoDTO) {
        return pagamentoService.updatePagamento(id, pagamentoDTO);
    }

    /**
     * deletes the Pagamento
     *
     * @param id of the {@link Pagamento Pagamento} to remove
     * @return HTTP status 200 if item is deleted otherwise 500
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removePagamento(@PathVariable Long id) {
        return pagamentoService.removePagamento(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    /**
     * Returns all Pagamento
     *
     * @return List of {@link PagamentoDTO PagamentoDTO}
     */
    @GetMapping("/all")
    public List<PagamentoDTO> getAll() {
        return pagamentoService.getAll();
    }

    /**
     * Returns Pagamento for provided id
     *
     * @return {@link PagamentoDTO PagamentoDTO}
     */
    @GetMapping("/id/{id}")
    public PagamentoDTO getById(@PathVariable Long id) {
        return pagamentoService.getById(id);
    }

    /**
     * Returns all Pagamento where contanti != null and > 0
     *
     * @return List {@link PagamentoDTO PagamentoDTO}
     */
    @GetMapping("/contanti")
    public List<PagamentoDTO> getByContanti() {
        return pagamentoService.getByContanti();
    }

    /**
     * Returns all Pagamento where PaypalMail != null
     *
     * @return List {@link PagamentoDTO PagamentoDTO}
     */
    @GetMapping("/mail")
    public List<PagamentoDTO> getByPaypalMail() {
        return pagamentoService.getByPaypalMail();
    }

    /**
     * Adds an Ordine to a relative Pagamento
     *
     * @param pagamentoId id of {@link Pagamento Pagamento}
     * @param ordineDTO   {@link OrdineDTO OrdineDTO} of Ordine to add
     * @return added Ordine
     */
    @PostMapping("/add/ordine/{pagamentoId}")
    public Ordine addOrdineToPagamento(@PathVariable Long pagamentoId, @RequestBody OrdineDTO ordineDTO) {
        return pagamentoService.addOrdineToPagamento(pagamentoId, ordineDTO);
    }
}
