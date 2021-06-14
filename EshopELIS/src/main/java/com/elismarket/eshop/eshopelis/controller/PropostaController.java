package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.service.interfaces.PropostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for {@link Proposta Proposta} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/api/proposta", produces = "application/json")
@CrossOrigin(origins = "*")
public class PropostaController {

    /**
     * @see PropostaService
     */
    @Autowired
    PropostaService propostaService;

    /**
     * Adds a new Proposta
     *
     * @param propostaDTO {@link PropostaDTO PropostaDTO} to add
     * @return Added Proposta
     */
    @PostMapping("/add")
    public PropostaDTO addProposta(@RequestBody PropostaDTO propostaDTO) {
        return propostaService.addProposta(propostaDTO);
    }

    /**
     * Updates the Proposta related to the id with relative PropostaDTO
     *
     * @param id          id of the {@link Proposta Proposta}
     * @param propostaDTO {@link PropostaDTO PropostaDTO} with updated fields
     * @return updated Proposta
     */
    @PatchMapping("/update/{id}")
    public PropostaDTO updateProposta(@PathVariable Long id, @RequestBody PropostaDTO propostaDTO) {
        return propostaService.updateProposta(id, propostaDTO);
    }

    /**
     * Remove the Proposta for the provided id
     *
     * @param id of the {@link Proposta Proposta} to remove
     * @return HTTP 200 if deleted successfully, else 500
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeProposta(@RequestParam Long id) {
        return propostaService.removeProposta(id) ? ResponseEntity.status(500).build() : ResponseEntity.status(200).build();
    }

    /**
     * Retrieves Proposta for provided id
     *
     * @param id of the {@link Proposta Proposta} to retrieve
     * @return {@link PropostaDTO PropostaDTO}
     */
    @GetMapping("/id/{id}")
    public PropostaDTO getById(@PathVariable Long id) {
        return propostaService.findById(id);
    }

    /**
     * Retrieves all Proposta
     *
     * @return List {@link PropostaDTO PropostaDTO}
     */
    @GetMapping("/all")
    public List<PropostaDTO> getById() {
        return propostaService.findAll();
    }

    /**
     * Retrieves all Proposta that has not been worked
     *
     * @return List {@link PropostaDTO PropostaDTO}
     */
    @GetMapping("/accettati")
    public List<PropostaDTO> findNotWorked() {
        return propostaService.findAllByIsAccettato(null);
    }


    /**
     * Retrieves all Proposta that has not been accettato
     *
     * @return List {@link PropostaDTO PropostaDTO}
     */
    @GetMapping("/accettati/si")
    public List<PropostaDTO> findAccettati() {
        return propostaService.findAllByIsAccettato(true);
    }

    /**
     * Retrieves all Proposta that has been accettato
     *
     * @return List {@link PropostaDTO PropostaDTO}
     */
    @GetMapping("/accettati/no")
    public List<PropostaDTO> findNonAccettati() {
        return propostaService.findAllByIsAccettato(false);
    }

    /**
     * Retrieves all Proposta for an Utente
     *
     * @param userId id of {@link Utente Utente}
     * @return List {@link PropostaDTO PropostaDTO}
     */
    @GetMapping("/all/utente/{userId}")
    public List<PropostaDTO> getAllByUtente(@PathVariable Long userId) {
        return propostaService.findAllByUtente(userId);
    }

}
