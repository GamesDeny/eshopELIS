package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.model.*;
import com.elismarket.eshop.eshopelis.service.interfaces.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for {@link Utente Utente} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@RestController
@RequestMapping(path = "/api/utente", produces = "application/json")
@CrossOrigin(origins = "*")
public class UtenteController {

    /**
     * @see UtenteService
     */
    @Autowired
    UtenteService utenteService;

    /**
     * Adds a new Utente
     *
     * @param utenteDTO {@link UtenteDTO UtenteDTO} with the fields to insert
     * @return added {@link Utente Utente}
     */
    @PostMapping("/add")
    public UtenteDTO addUtente(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.addUtente(utenteDTO);
    }

    /**
     * Updates Utente related to the id with updated fields in UtenteDTO
     *
     * @param id        of the {@link Utente Utente}
     * @param utenteDTO {@link UtenteDTO UtenteDTO} with updated fields
     * @return updated Utente
     */
    @PatchMapping("/update/{id}")
    public UtenteDTO updateUtente(@PathVariable Long id, @RequestBody UtenteDTO utenteDTO) {
        return utenteService.updateUtente(id, utenteDTO);
    }

    /**
     * Removes Utente related to the id
     *
     * @param id of the {@link Utente Utente}
     * @return HTTP 200 if deleted successfully, else 500
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeRigaOrdine(@PathVariable Long id) {
        return utenteService.removeUtente(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    /**
     * Retrieves all Utente
     *
     * @return List {@link UtenteDTO UtenteDTO}
     */
    @GetMapping("/all")
    public List<UtenteDTO> getAll() {
        return utenteService.getAll("");
    }

    /**
     * Retrieves all admin
     *
     * @return List {@link UtenteDTO UtenteDTO}
     * @see Utente#getIsAdmin()
     */
    @GetMapping("/all/admin")
    public List<UtenteDTO> getAllAdmin() {
        return utenteService.getAll("admin");
    }

    /**
     * Retrieves all users
     *
     * @return List {@link UtenteDTO UtenteDTO}
     * @see Utente#getIsAdmin()
     */
    @GetMapping("/all/user")
    public List<UtenteDTO> getAllUsers() {
        return utenteService.getAll("user");
    }

    /**
     * Retrieves user related to the username
     *
     * @param userId of the {@link Utente Utente}
     * @return retrieved Utente
     */
    @GetMapping("/id/{id}")
    public UtenteDTO getById(@PathVariable Long userId) {
        return utenteService.getById(userId);
    }

    /**
     * Retrieves user related to the username
     *
     * @param username of the {@link Utente Utente}
     * @return retrieved Utente
     */
    @GetMapping("/username/{username}")
    public UtenteDTO getByUsername(@PathVariable String username) {
        return utenteService.getByUser(username);
    }

    /**
     * Retrieves user related to the mail
     *
     * @param mail of the {@link Utente Utente}
     * @return retrieved Utente
     */
    @GetMapping("/mail/{mail}")
    public UtenteDTO getByMail(@PathVariable String mail) {
        return utenteService.getByMail(mail);
    }

    /**
     * Retrieves user related to the siglaResidenza
     *
     * @param siglaResidenza of the {@link Utente Utente}
     * @return retrieved Utente
     */
    @GetMapping("/sigla/{siglaResidenza}")
    public UtenteDTO getBySiglaResidenza(@PathVariable Integer siglaResidenza) {
        return utenteService.getBySigla(siglaResidenza);
    }

    /**
     * Login for the Utente
     *
     * @param utenteDTO {@link UtenteDTO UtenteDTO} with username and password for login
     * @return Logged Utente
     */
    @PostMapping(path = "/login")
    public UtenteDTO getLoginUtente(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.getLoginUtente(utenteDTO.username, utenteDTO.password);
    }

    /**
     * Logout for the Utente
     *
     * @param userId of the {@link Utente Utente} to logout
     * @return HTTP status 200 if all went good, otherwise 500
     */
    @PostMapping(path = "/logout/{userId}")
    public ResponseEntity<Object> getLogout(@PathVariable Long userId) {
        return utenteService.getLogout(userId) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    /**
     * Adds a Feedback to an Utente
     *
     * @param userId      of the {@link Utente Utente}
     * @param feedbackDTO {@link FeedbackDTO FeedbackDTO} to link to the Utente
     * @return Added Feedback
     */
    @PostMapping("/add/feedback/{userId}")
    public Feedback addFeedbackToUser(@PathVariable Long userId, @RequestBody FeedbackDTO feedbackDTO) {
        return utenteService.addFeedbackToUser(userId, feedbackDTO);
    }

    /**
     * Adds a Proposta to an Utente
     *
     * @param userId      of the {@link Utente Utente}
     * @param propostaDTO {@link PropostaDTO PropostaDTO} to link to the Utente
     * @return Added Proposta
     */
    @PostMapping("/add/proposta/{userId}")
    public Proposta addPropostaToUser(@PathVariable Long userId, @RequestBody PropostaDTO propostaDTO) {
        return utenteService.addPropostaToUser(userId, propostaDTO);
    }

    /**
     * Adds a Prodotto to an Utente
     *
     * @param userId      of the {@link Utente Utente}
     * @param prodottoDTO {@link ProdottoDTO ProdottoDTO} to link to the Utente
     * @return Added Prodotto
     */
    @PostMapping("/add/prodotto/{userId}")
    public Prodotto addProdottoToUser(@PathVariable Long userId, @RequestBody ProdottoDTO prodottoDTO) {
        return utenteService.addProdottoToUser(userId, prodottoDTO);
    }

    /**
     * Adds a Pagamento to an Utente
     *
     * @param userId       of the {@link Utente Utente}
     * @param pagamentoDTO {@link PagamentoDTO PagamentoDTO} to link to the Utente
     * @return Added Pagamento
     */
    @PostMapping("/add/pagamento/{userId}")
    public Pagamento addPagamentoToUser(@PathVariable Long userId, @RequestBody PagamentoDTO pagamentoDTO) {
        return utenteService.addPagamentoToUser(userId, pagamentoDTO);
    }
}
