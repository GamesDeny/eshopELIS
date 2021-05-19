package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.UtenteService;
import com.elismarket.eshop.model.dto.UtenteDTO;
import com.elismarket.eshop.model.entities.UtenteImpl;
import com.elismarket.eshop.model.interfaces.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/*
 *
 * Rest API for User
 * Contains all informations for making API requests for frontend
 *
 */


@RestController
@RequestMapping(path = "/rest/utente", produces = "application/json")
@CrossOrigin(origins = "*")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    //returns all db users
    //prima di ritornare la lista setto la password a null in modo che non sia trasportata
    @GetMapping("/all")
    public List<Utente> getAll() {
        return utenteService.getAll("");
    }

    //returns only the admins
    @GetMapping("/all/admin")
    public List<Utente> getAllAdmin() {
        return utenteService.getAll("admin");
    }

    //returns only non-admin users
    @GetMapping("/all/user")
    public List<Utente> getAllUsers() {
        return utenteService.getAll("user");
    }

    //returns a user depending on field
    @GetMapping("/username/{username}")
    public Utente getByUsername(@PathVariable("username") String username) {
        return utenteService.getByUser(username);
    }

    @GetMapping("/mail/{mail}")
    public Utente getByMail(@PathVariable("mail") String mail) {
        return utenteService.getByMail(mail);
    }

    @GetMapping("/sigla/{siglaResidenza}")
    public Utente getBySiglaResidenza(@PathVariable("siglaResidenza") Integer siglaResidenza) {
        return utenteService.getUtente(siglaResidenza);
    }

    @PostMapping("/login")
    public Utente getLoginUtente(@RequestBody UtenteImpl utente) {
        return utenteService.getLoginUtente(utente.getUsername(), utente.getPassword());
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addUtente(@RequestBody UtenteDTO utenteDTO) {
        try {
            utenteService.addUtente(utenteDTO);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateUtente(@PathVariable("id") Long id, @RequestBody UtenteDTO utenteDTO) {
        Utente u = utenteService.getUtente(id);

        //cambio DTO con le informazioni aggiornate
        utenteDTO.setId(id);
        utenteDTO.setUsername(Objects.isNull(utenteDTO.getUsername()) ? u.getUsername() : utenteDTO.username);
        //faccio hashing prima di aggiornare
        utenteDTO.setPassword(Objects.isNull(utenteDTO.getPassword()) ? Utente.hashPassword(u.getPassword()) : Utente.hashPassword(utenteDTO.password));
        utenteDTO.setNome(Objects.isNull(utenteDTO.getPassword()) ? u.getNome() : utenteDTO.nome);
        utenteDTO.setCognome(Objects.isNull(utenteDTO.getPassword()) ? u.getCognome() : utenteDTO.cognome);
        utenteDTO.setLogged(Objects.isNull(utenteDTO.getPassword()) ? u.getLogged() : utenteDTO.logged);
        utenteDTO.setIsAdmin(Objects.isNull(utenteDTO.getPassword()) ? u.getIsAdmin() : utenteDTO.isAdmin);
        utenteDTO.setMail(Objects.isNull(utenteDTO.getPassword()) ? u.getMail() : utenteDTO.mail);
        utenteDTO.setDataNascita(Objects.isNull(utenteDTO.getDataNascita()) ? u.getDataNascita() : utenteDTO.dataNascita);
        utenteDTO.setSiglaResidenza(Objects.isNull(utenteDTO.getSiglaResidenza()) ? u.getSiglaResidenza() : utenteDTO.siglaResidenza);

        return utenteService.addUtente(utenteDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeRigaOrdine(@PathVariable("id") Long id) {
        try {
            utenteService.removeUtente(id);
            return ResponseEntity.status(200).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
