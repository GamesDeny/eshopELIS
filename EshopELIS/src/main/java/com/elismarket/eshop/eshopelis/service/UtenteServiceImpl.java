package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.helper.FeedbackHelper;
import com.elismarket.eshop.eshopelis.helper.PagamentoHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.helper.PropostaHelper;
import com.elismarket.eshop.eshopelis.model.*;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 *
 * Service class for CRUD operations and control of User
 *
 */

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private FeedbackHelper feedbackHelper;

    @Autowired
    private PropostaHelper propostaHelper;

    @Autowired
    private PagamentoHelper pagamentoHelper;

    @Autowired
    private ProdottoHelper prodottoHelper;

    @Autowired
    private UtenteCrud utenteCrud;

    //operazioni di inserimento utente nel DB
    @Override
    public UtenteDTO addUtente(UtenteDTO utenteDTO) {
        Utente u = Utente.of(utenteDTO);

        if (!Objects.isNull(utenteCrud.findByMail(utenteDTO.mail)) ||
                !Objects.isNull(utenteCrud.findByUsername(utenteDTO.username)) ||
                !Objects.isNull(utenteCrud.findBySiglaResidenza(utenteDTO.siglaResidenza)))
            throw new UtenteException("Duplicato!");
        else if (!Checkers.siglaChecker(utenteDTO.siglaResidenza))
            throw new UtenteException("Sigla inconsistente");
        else if (!Checkers.birthDateChecker(utenteDTO.dataNascita))
            throw new UtenteException("Data di nascita non valida");
        else if ((utenteDTO.password).equals(utenteDTO.mail) || !(Checkers.mailChecker(utenteDTO.mail) && Checkers.passwordChecker(utenteDTO.password)))
            throw new UtenteException("Mail e/o password inconsistenti");

        u.setPassword((Utente.hashPassword(u.getPassword())));
        utenteCrud.save(u);

        return Utente.to(utenteCrud.findByMail(utenteDTO.mail));
    }

    @Override
    public UtenteDTO updateUtente(Long id, UtenteDTO utenteDTO) {

        Utente u = utenteCrud.findById(id).orElseThrow(() -> new UtenteException("Utente not found"));

        //cambio DTO con le informazioni aggiornate
        utenteDTO.id = id;
        utenteDTO.username = Objects.isNull(utenteDTO.username) ? u.getUsername() : utenteDTO.username;
        //faccio hashing prima di aggiornare
        utenteDTO.password = Objects.isNull(utenteDTO.password) ? Utente.hashPassword(u.getPassword()) : Utente.hashPassword(utenteDTO.password);
        utenteDTO.nome = Objects.isNull(utenteDTO.nome) ? u.getNome() : utenteDTO.nome;
        utenteDTO.cognome = Objects.isNull(utenteDTO.cognome) ? u.getCognome() : utenteDTO.cognome;
        utenteDTO.logged = Objects.isNull(utenteDTO.logged) ? u.getLogged() : utenteDTO.logged;
        utenteDTO.isAdmin = Objects.isNull(utenteDTO.isAdmin) ? u.getIsAdmin() : utenteDTO.isAdmin;
        utenteDTO.mail = Objects.isNull(utenteDTO.mail) ? u.getMail() : utenteDTO.mail;
        utenteDTO.dataNascita = Objects.isNull(utenteDTO.dataNascita) ? u.getDataNascita() : utenteDTO.dataNascita;
        utenteDTO.siglaResidenza = Objects.isNull(utenteDTO.siglaResidenza) ? u.getSiglaResidenza() : utenteDTO.siglaResidenza;

        return Utente.to(utenteCrud.findById(utenteDTO.id).orElseThrow(() -> new UtenteException("Utente not found")));
    }

    @Override
    public Boolean removeUtente(Long id) {
        if (!utenteCrud.findById(id).isPresent())
            throw new UtenteException("Cannot find Utente for provided id");
        utenteCrud.deleteById(id);

        return !utenteCrud.findById(id).isPresent();
    }

    //richiesta di utenti
    //se vuoto li ritorna tutti altrimenti ritorna in base al valore di findby
    //il valore mi serve a capire se vuole un utente normale o un admin
    @Override
    public List<UtenteDTO> getAll(String findby) {
        List<UtenteDTO> result = new ArrayList<>();

        switch (findby) {
            case "":
                if (utenteCrud.findAll().isEmpty())
                    throw new UtenteException("List empty");
                utenteCrud.findAll().forEach(utente -> result.add(Utente.to(utente)));
                break;
            case "admin":
                if (utenteCrud.findAllByIsAdmin(true).isEmpty())
                    throw new UtenteException("List empty");

                utenteCrud.findAllByIsAdmin(true).forEach(utente -> result.add(Utente.to(utente)));
                break;
            case "user":
                if (utenteCrud.findAllByIsAdmin(false).isEmpty())
                    throw new UtenteException("List empty");
                utenteCrud.findAllByIsAdmin(false).forEach(utente -> result.add(Utente.to(utente)));

                break;
            default:
                throw new UtenteException("Error in parameter for getAll Function");
        }
        return result;
    }

    @Override
    public UtenteDTO getByMail(String mail) {
        if (Objects.isNull(utenteCrud.findByMail(mail)))
            throw new UtenteException("Not found");
        return Utente.to(utenteCrud.findByMail(mail));
    }

    @Override
    public UtenteDTO getByUser(String username) {
        if (Objects.isNull(utenteCrud.findByUsername(username)))
            throw new UtenteException("Not found");
        return Utente.to(utenteCrud.findByUsername(username));
    }

    @Override
    public UtenteDTO getById(Long id) {
        if (!utenteCrud.findById(id).isPresent())
            throw new UtenteException("Not found");
        return Utente.to(utenteCrud.findById(id).get());
    }

    @Override
    public UtenteDTO getBySigla(Integer siglaResidenza) {
        if (Objects.isNull(utenteCrud.findBySiglaResidenza(siglaResidenza)))
            throw new UtenteException("User with this sigla doesn't exist");
        return Utente.to(utenteCrud.findBySiglaResidenza(siglaResidenza));
    }

    @Override
    public UtenteDTO getLoginUtente(String username, String password) {
        if (Objects.isNull(username) || Objects.isNull(password))
            throw new UtenteException("Missing parameters!");
        return Utente.to(utenteCrud.findByUsernameAndPassword(username, Utente.hashPassword(password)));
    }

    @Override
    public Feedback addFeedbackToUser(Long userId, FeedbackDTO feedbackDTO) {
        return feedbackHelper.addFeedbackToUser(userId, feedbackDTO);
    }

    @Override
    public Proposta addPropostaToUser(Long userId, PropostaDTO propostaDTO) {
        return propostaHelper.addPropostaToUser(userId, propostaDTO);
    }

    @Override
    public Prodotto addProdottoToUser(Long userId, ProdottoDTO prodottoDTO) {
        return prodottoHelper.addProdottoToUser(userId, prodottoDTO);
    }

    @Override
    public Pagamento addPagamentoToUser(Long userId, PagamentoDTO pagamentoDTO) {
        return pagamentoHelper.addPagamentoToUser(userId, pagamentoDTO);
    }


}