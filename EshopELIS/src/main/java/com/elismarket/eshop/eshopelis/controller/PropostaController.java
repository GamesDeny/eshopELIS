package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.service.PropostaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/rest/proposta", produces = "application/json")
@CrossOrigin(origins = "*")
public class PropostaController {

    @Autowired
    private PropostaServiceImpl propostaService;

    @PostMapping("/add")
    public PropostaDTO addProposta(@RequestBody PropostaDTO propostaDTO) {
        return propostaService.addProposta(propostaDTO);
    }

    @PatchMapping("/update/{id}")
    public PropostaDTO updateProposta(@PathVariable("id") Long id, @RequestBody PropostaDTO propostaDTO) {
        Proposta p = Proposta.of(propostaService.getById(id));

        propostaDTO.setId(id);
        propostaDTO.setPrezzoProposto(Objects.isNull(propostaDTO.getPrezzoProposto()) ? p.getPrezzoProposto() : propostaDTO.prezzoProposto);
        propostaDTO.setNome(Objects.isNull(propostaDTO.getNome()) ? p.getNome() : propostaDTO.nome);
        propostaDTO.setDescrizione(Objects.isNull(propostaDTO.getDescrizione()) ? p.getDescrizione() : propostaDTO.descrizione);
        propostaDTO.setIsAccettato(Objects.isNull(propostaDTO.getIsAccettato()) ? p.getIsAccettato() : propostaDTO.isAccettato);
        propostaDTO.setMotivoRifiuto(Objects.isNull(propostaDTO.getMotivoRifiuto()) ? p.getMotivoRifiuto() : propostaDTO.motivoRifiuto);
        propostaDTO.setQuantita(Objects.isNull(propostaDTO.getQuantita()) ? p.getQuantita() : propostaDTO.quantita);
        propostaDTO.setSubmissionDate(Objects.isNull(propostaDTO.getSubmissionDate()) ? p.getSubmissionDate() : propostaDTO.submissionDate);

        return propostaService.addProposta(propostaDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeProposta(@RequestParam("id") Long id) {
        return propostaService.removeProposta(id) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @GetMapping("/accettati/si")
    public List<PropostaDTO> findAccettati() {
        return propostaService.findAllByIsAccettato(true);
    }

    @GetMapping("/accettati/no")
    public List<PropostaDTO> findNonAccettati() {
        return propostaService.findAllByIsAccettato(false);
    }

    @GetMapping("/utente/{id}")
    public List<PropostaDTO> findAllByUtente(@RequestParam("id") Long id) {
        return propostaService.findAllByUtente(id);
    }

    @GetMapping("/utente")
    public List<PropostaDTO> findAllByUtente(@RequestBody UtenteDTO utente) {
        return propostaService.findAllByUtente(utente);
    }
}
