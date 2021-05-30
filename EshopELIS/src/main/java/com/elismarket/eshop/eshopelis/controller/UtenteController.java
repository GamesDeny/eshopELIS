package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.service.UtenteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 *
 * Rest API for User
 * Contains all informations for making API requests for frontend
 *
 */


@RestController
@RequestMapping(path = "/api/utente", produces = "application/json")
@CrossOrigin(origins = "*")
public class UtenteController {
    @Autowired
    private UtenteServiceImpl utenteService;

    @PostMapping("/add")
    public UtenteDTO addUtente(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.addUtente(utenteDTO);
    }

    @PatchMapping("/update/{id}")
    public UtenteDTO updateUtente(@PathVariable Long id, @RequestBody UtenteDTO utenteDTO) {
        return utenteService.updateUtente(id, utenteDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeRigaOrdine(@PathVariable Long id) {
        return utenteService.removeUtente(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    //returns all db users
    //prima di ritornare la lista setto la password a null in modo che non sia trasportata
    @GetMapping("/all")
    public List<UtenteDTO> getAll() {
        return utenteService.getAll("");
    }

    //returns only the admins
    @GetMapping("/all/admin")
    public List<UtenteDTO> getAllAdmin() {
        return utenteService.getAll("admin");
    }

    //returns only non-admin users
    @GetMapping("/all/user")
    public List<UtenteDTO> getAllUsers() {
        return utenteService.getAll("user");
    }

    //returns a user depending on field
    @GetMapping("/username/{username}")
    public UtenteDTO getByUsername(@PathVariable String username) {
        return utenteService.getByUser(username);
    }

    @GetMapping("/mail/{mail}")
    public UtenteDTO getByMail(@PathVariable String mail) {
        return utenteService.getByMail(mail);
    }

    @GetMapping("/sigla/{siglaResidenza}")
    public UtenteDTO getBySiglaResidenza(@PathVariable Integer siglaResidenza) {
        return utenteService.getBySigla(siglaResidenza);
    }

    @PostMapping(path = "/login")
    public UtenteDTO getLoginUtente(@RequestBody UtenteDTO utente) {
        return utenteService.getLoginUtente(utente.username, utente.password);
    }

    @PostMapping("/add/feedback/{userId}")
    public Feedback addFeedbackToUser(@PathVariable Long userId, @RequestBody FeedbackDTO feedbackDTO) {
        return utenteService.addFeedbackToUser(userId, feedbackDTO);
    }

    @PostMapping("/add/proposta/{userId}")
    public Proposta addPropostaToUser(@PathVariable Long userId, @RequestBody PropostaDTO propostaDTO) {
        return utenteService.addPropostaToUser(userId, propostaDTO);
    }

    @PostMapping("/add/prodotto/{userId}")
    public Prodotto addProdottoToUser(@PathVariable Long userId, @RequestBody ProdottoDTO prodottoDTO) {
        return utenteService.addProdottoToUser(userId, prodottoDTO);
    }

    @PostMapping("/add/pagamento/{userId}")
    public Pagamento addPagamentoToUser(@PathVariable Long userId, @RequestBody PagamentoDTO pagamentoDTO) {
        return utenteService.addPagamentoToUser(userId, pagamentoDTO);
    }
}
