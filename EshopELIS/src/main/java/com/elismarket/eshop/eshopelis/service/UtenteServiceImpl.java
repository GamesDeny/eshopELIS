package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.helper.FeedbackHelper;
import com.elismarket.eshop.eshopelis.helper.PagamentoHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.helper.PropostaHelper;
import com.elismarket.eshop.eshopelis.model.*;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.UtenteService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.*;

/*
 *
 * Service class for CRUD operations and control of User
 *
 */

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    UtenteCrud utenteCrud;

    @Autowired
    FeedbackHelper feedbackHelper;

    @Autowired
    PropostaHelper propostaHelper;

    @Autowired
    PagamentoHelper pagamentoHelper;

    @Autowired
    ProdottoHelper prodottoHelper;

    //operazioni di inserimento utente nel DB
    @Override
    public UtenteDTO addUtente(UtenteDTO utenteDTO) {
        Checkers.utenteFieldsChecker(utenteDTO);
        utenteDTO.logged = false;
        utenteDTO.isAdmin = false;
        Utente u = Utente.of(utenteDTO);

        duplicateChecker(utenteDTO);
        u.setPassword((Utente.hashPassword(u.getPassword())));

        return Utente.to(utenteCrud.saveAndFlush(u));
    }

    @Override
    public UtenteDTO updateUtente(Long utenteId, UtenteDTO utenteDTO) {

        duplicateChecker(utenteDTO);

        Utente u = utenteCrud.findById(utenteId).orElseThrow(() -> new UtenteException(CANNOT_FIND_ELEMENT.name()));

        //cambio DTO con le informazioni aggiornate
        utenteDTO.id = utenteId;
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
        utenteDTO.logged = false;

        Checkers.utenteFieldsChecker(utenteDTO);

        Utente save = Utente.of(utenteDTO);
        if (!Objects.isNull(utenteDTO.proposte_id))
            propostaHelper.linkUtenteToProposte(utenteId, utenteDTO.proposte_id);
        if (!Objects.isNull(utenteDTO.pagamenti_id))
            pagamentoHelper.linkUtenteToPagamenti(utenteId, utenteDTO.pagamenti_id);
        if (!Objects.isNull(utenteDTO.prodotti_id))
            prodottoHelper.linkUtenteToProdotti(utenteId, utenteDTO.prodotti_id);
        if (!Objects.isNull(utenteDTO.feedbacks_id))
            feedbackHelper.linkUtenteToFeedbacks(utenteId, utenteDTO.feedbacks_id);
        utenteCrud.saveAndFlush(save);

        return Utente.to(utenteCrud.findById(utenteDTO.id).orElseThrow(() -> new UtenteException(CANNOT_FIND_ELEMENT.name())));
    }

    private void duplicateChecker(UtenteDTO utenteDTO) {
        if (!(Objects.isNull(utenteCrud.findByMail(utenteDTO.mail)) &&
                Objects.isNull(utenteCrud.findByUsername(utenteDTO.username)) &&
                Objects.isNull(utenteCrud.findBySiglaResidenza(utenteDTO.siglaResidenza))))
            throw new UtenteException(DUPLICATE.name());
        else if (!Checkers.siglaChecker(utenteDTO.siglaResidenza))
            throw new UtenteException(INCONSISTENT_SIGLA.name());
        else if (!Checkers.birthDateChecker(utenteDTO.dataNascita))
            throw new UtenteException(DATE_NOT_VALID.name());
        else if ((utenteDTO.password).equals(utenteDTO.mail) || !(Checkers.mailChecker(utenteDTO.mail) && Checkers.passwordChecker(utenteDTO.password)))
            throw new UtenteException(MAIL_OR_PASSWORD_INCONSISTENT.name());
    }

    @Override
    public Boolean removeUtente(Long id) {
        if (!utenteCrud.existsById(id))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        utenteCrud.deleteById(id);

        return !utenteCrud.existsById(id);
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
                    throw new UtenteException(LIST_IS_EMPTY.name());
                utenteCrud.findAll().forEach(utente -> result.add(Utente.to(utente)));
                break;
            case "admin":
                if (utenteCrud.findAllByIsAdmin(true).isEmpty())
                    throw new UtenteException(LIST_IS_EMPTY.name());

                utenteCrud.findAllByIsAdmin(true).forEach(utente -> result.add(Utente.to(utente)));
                break;
            case "user":
                if (utenteCrud.findAllByIsAdmin(false).isEmpty())
                    throw new UtenteException(LIST_IS_EMPTY.name());
                utenteCrud.findAllByIsAdmin(false).forEach(utente -> result.add(Utente.to(utente)));

                break;
            default:
                throw new UtenteException(MISSING_PARAMETERS.name());
        }
        return result;
    }

    @Override
    public UtenteDTO getByMail(String mail) {
        if (Objects.isNull(utenteCrud.findByMail(mail)))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return Utente.to(utenteCrud.findByMail(mail));
    }

    @Override
    public UtenteDTO getByUser(String username) {
        if (Objects.isNull(utenteCrud.findByUsername(username)))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return Utente.to(utenteCrud.findByUsername(username));
    }

    @Override
    public UtenteDTO getById(Long id) {
        if (!utenteCrud.existsById(id))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return Utente.to(utenteCrud.findById(id).orElseThrow(() -> new UtenteException(CANNOT_FIND_ELEMENT.name())));
    }

    @Override
    public UtenteDTO getBySigla(Integer siglaResidenza) {
        if (Objects.isNull(utenteCrud.findBySiglaResidenza(siglaResidenza)))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return Utente.to(utenteCrud.findBySiglaResidenza(siglaResidenza));
    }

    @Override
    public UtenteDTO getLoginUtente(String username, String password) {
        if (Objects.isNull(username) || Objects.isNull(password))
            throw new UtenteException(MISSING_PARAMETERS.name());
        return Utente.to(utenteCrud.findByUsernameAndPassword(username, Utente.hashPassword(password)));
    }

    @Override
    public Feedback addFeedbackToUser(Long userId, FeedbackDTO feedbackDTO) {
        if (!utenteCrud.existsById(userId))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return feedbackHelper.addFeedbackToUser(userId, feedbackDTO);
    }

    @Override
    public Proposta addPropostaToUser(Long userId, PropostaDTO propostaDTO) {
        if (!utenteCrud.existsById(userId))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return propostaHelper.addPropostaToUser(userId, propostaDTO);
    }

    @Override
    public Prodotto addProdottoToUser(Long userId, ProdottoDTO prodottoDTO) {
        if (!utenteCrud.existsById(userId))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return prodottoHelper.addProdottoToUser(userId, prodottoDTO);
    }

    @Override
    public Pagamento addPagamentoToUser(Long userId, PagamentoDTO pagamentoDTO) {
        if (utenteCrud.existsById(userId))
            return pagamentoHelper.addPagamentoToUser(userId, pagamentoDTO);

        throw new UtenteException(CANNOT_FIND_ELEMENT.name());
    }

}