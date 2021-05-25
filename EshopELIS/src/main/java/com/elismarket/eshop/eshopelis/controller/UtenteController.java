package com.elismarket.eshop.eshopelis.controller;

import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.service.UtenteServiceImpl;
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
    private UtenteServiceImpl utenteService;

    @PostMapping("/add")
    public UtenteDTO addUtente(@RequestBody UtenteDTO utenteDTO) {
        return utenteService.addUtente(utenteDTO);
    }

    @PatchMapping("/update/{id}")
    public UtenteDTO updateUtente(@PathVariable("id") Long id, @RequestBody UtenteDTO utenteDTO) {
        Utente u = Utente.of(utenteService.getUtente(id));

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

        return utenteService.addUtente(utenteDTO);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Object> removeRigaOrdine(@PathVariable("id") Long id) {
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
    public UtenteDTO getByUsername(@PathVariable("username") String username) {
        return utenteService.getByUser(username);
    }

    @GetMapping("/mail/{mail}")
    public UtenteDTO getByMail(@PathVariable("mail") String mail) {
        return utenteService.getByMail(mail);
    }

    @GetMapping("/sigla/{siglaResidenza}")
    public UtenteDTO getBySiglaResidenza(@PathVariable("siglaResidenza") Integer siglaResidenza) {
        return utenteService.getUtente(siglaResidenza);
    }

    @PostMapping(path = "/login")
    public UtenteDTO getLoginUtente(@RequestBody UtenteDTO utente) {
        return utenteService.getLoginUtente(utente.getUsername(), utente.getPassword());
    }

}
