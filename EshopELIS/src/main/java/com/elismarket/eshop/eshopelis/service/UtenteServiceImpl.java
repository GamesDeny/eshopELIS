package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.exception.*;
import com.elismarket.eshop.eshopelis.helper.FeedbackHelper;
import com.elismarket.eshop.eshopelis.helper.PagamentoHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.helper.PropostaHelper;
import com.elismarket.eshop.eshopelis.model.*;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.UtenteService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.*;

/**
 * {@link Utente Utente} service class for interaction between DB and relative Controller
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Service
public class UtenteServiceImpl implements UtenteService {

    /**
     * @see UtenteCrud
     */
    @Autowired
    UtenteCrud utenteCrud;

    /**
     * @see FeedbackHelper
     */
    @Autowired
    FeedbackHelper feedbackHelper;

    /**
     * @see PropostaHelper
     */
    @Autowired
    PropostaHelper propostaHelper;

    /**
     * @see PagamentoHelper
     */
    @Autowired
    PagamentoHelper pagamentoHelper;

    /**
     * @see ProdottoHelper
     */
    @Autowired
    ProdottoHelper prodottoHelper;

    /**
     * Adds a new Utente
     *
     * @param utenteDTO {@link UtenteDTO UtenteDTO} with the fields to insert
     * @return added {@link Utente Utente}
     */
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

    /**
     * Updates Utente related to the id with updated fields in UtenteDTO
     *
     * @param userId    of the {@link Utente Utente}
     * @param utenteDTO {@link UtenteDTO UtenteDTO} with updated fields
     * @return updated Utente
     * @throws UtenteException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public UtenteDTO updateUtente(Long userId, UtenteDTO utenteDTO) {
        if (Objects.isNull(userId))
            throw new UtenteException(MISSING_PARAMETERS.name());


        duplicateChecker(utenteDTO);

        Utente u = utenteCrud.findById(userId).orElseThrow(() -> new UtenteException(CANNOT_FIND_ELEMENT.name()));

        //cambio DTO con le informazioni aggiornate
        utenteDTO.id = userId;
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
            propostaHelper.linkUtenteToProposte(userId, utenteDTO.proposte_id);
        if (!Objects.isNull(utenteDTO.pagamenti_id))
            pagamentoHelper.linkUtenteToPagamenti(userId, utenteDTO.pagamenti_id);
        if (!Objects.isNull(utenteDTO.prodotti_id))
            prodottoHelper.linkUtenteToProdotti(userId, utenteDTO.prodotti_id);
        if (!Objects.isNull(utenteDTO.feedbacks_id))
            feedbackHelper.linkUtenteToFeedbacks(userId, utenteDTO.feedbacks_id);
        utenteCrud.saveAndFlush(save);

        return Utente.to(utenteCrud.findById(utenteDTO.id).orElseThrow(() -> new UtenteException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Checks if there are duplicates in the DB
     *
     * @param utenteDTO DTO to control for
     * @throws UtenteException with {@link ExceptionPhrases#DUPLICATE DUPLICATE} message
     * @throws UtenteException with {@link ExceptionPhrases#INCONSISTENT_SIGLA INCONSISTENT_SIGLA} message
     * @throws UtenteException with {@link ExceptionPhrases#DATE_NOT_VALID DATE_NOT_VALID} message
     * @throws UtenteException with {@link ExceptionPhrases#MAIL_OR_PASSWORD_INCONSISTENT MAIL_OR_PASSWORD_INCONSISTENT} message
     */
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

    /**
     * Removes Utente related to the id
     *
     * @param id of the {@link Utente Utente}
     * @return HTTP 200 if deleted successfully, else 500
     * @throws UtenteException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public Boolean removeUtente(Long id) {
        if (Objects.isNull(id))
            throw new UtenteException(MISSING_PARAMETERS.name());

        if (!utenteCrud.existsById(id))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        utenteCrud.deleteById(id);

        return !utenteCrud.existsById(id);
    }

    /**
     * Retrieves all Utente
     * if findBy is
     * "" returns all users
     * "admin" returns all admins
     * "user" returns all users (non admin)
     *
     * @return List {@link UtenteDTO UtenteDTO}
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public List<UtenteDTO> getAll(String findby) {
        List<UtenteDTO> result = new ArrayList<>();

        switch (findby) {
            case "":

                utenteCrud.findAll().forEach(utente -> result.add(Utente.to(utente)));
                break;
            case "admin":

                utenteCrud.findAllByIsAdmin(true).forEach(utente -> result.add(Utente.to(utente)));
                break;
            case "user":

                utenteCrud.findAllByIsAdmin(false).forEach(utente -> result.add(Utente.to(utente)));

                break;
            default:
                throw new UtenteException(MISSING_PARAMETERS.name());
        }
        return result;
    }

    /**
     * Retrieves user related to the mail
     *
     * @param mail of the {@link Utente Utente}
     * @return retrieved Utente
     * @throws UtenteException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public UtenteDTO getByMail(String mail) {
        if (Objects.isNull(mail) || Strings.isBlank(mail) || Strings.isEmpty(mail))
            throw new UtenteException(MISSING_PARAMETERS.name());

        if (Objects.isNull(utenteCrud.findByMail(mail)))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return Utente.to(utenteCrud.findByMail(mail));
    }

    /**
     * Retrieves user related to the username
     *
     * @param username of the {@link Utente Utente}
     * @return retrieved Utente
     * @throws UtenteException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public UtenteDTO getByUser(String username) {
        if (Objects.isNull(username) || Strings.isBlank(username) || Strings.isEmpty(username))
            throw new UtenteException(MISSING_PARAMETERS.name());

        if (Objects.isNull(utenteCrud.findByUsername(username)))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return Utente.to(utenteCrud.findByUsername(username));
    }

    /**
     * Retrieves user related to the username
     *
     * @param userId of the {@link Utente Utente}
     * @return retrieved Utente
     * @throws UtenteException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public UtenteDTO getById(Long userId) {
        if (Objects.isNull(userId))
            throw new UtenteException(MISSING_PARAMETERS.name());

        if (!utenteCrud.existsById(userId))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return Utente.to(utenteCrud.findById(userId).orElseThrow(() -> new UtenteException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Login for the Utente
     *
     * @param username is the Username to search for
     * @param password is the Password related to the username
     * @return Logged Utente
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public UtenteDTO getLoginUtente(String username, String password) {
        if (Objects.isNull(username) || Objects.isNull(password) ||
                Strings.isEmpty(username) || Strings.isBlank(username) ||
                Strings.isEmpty(password) || Strings.isBlank(password))
            throw new UtenteException(MISSING_PARAMETERS.name());

        Utente u = utenteCrud.findByUsernameAndPassword(username, Utente.hashPassword(password));
        u.setLogged(Boolean.TRUE);

        return Utente.to(utenteCrud.saveAndFlush(u));
    }

    /**
     * Logout for the Utente
     *
     * @param userId of the {@link Utente Utente} to logout
     * @return HTTP status 200 if all went good, otherwise 500
     * @throws UtenteException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public Boolean getLogout(Long userId) {
        if (Objects.isNull(userId))
            throw new UtenteException(MISSING_PARAMETERS.name());

        Utente u = utenteCrud.findById(userId).orElseThrow(() -> new UtenteException(CANNOT_FIND_ELEMENT.name()));
        u.setLogged(Boolean.FALSE);
        utenteCrud.saveAndFlush(u);
        return true;
    }

    /**
     * Adds a Feedback to an Utente
     *
     * @param userId      of the {@link Utente Utente}
     * @param feedbackDTO {@link FeedbackDTO FeedbackDTO} to link to the Utente
     * @return Added Feedback
     * @throws UtenteException   with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException   with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @throws FeedbackException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public Feedback addFeedbackToUser(Long userId, FeedbackDTO feedbackDTO) {
        if (Objects.isNull(userId))
            throw new UtenteException(MISSING_PARAMETERS.name());
        if (Objects.isNull(feedbackDTO))
            throw new FeedbackException(MISSING_PARAMETERS.name());

        if (!utenteCrud.existsById(userId))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return feedbackHelper.addFeedbackToUser(userId, feedbackDTO);
    }

    /**
     * Adds a Proposta to an Utente
     *
     * @param userId      of the {@link Utente Utente}
     * @param propostaDTO {@link PropostaDTO PropostaDTO} to link to the Utente
     * @return Added Proposta
     * @throws UtenteException   with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException   with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @throws PropostaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public Proposta addPropostaToUser(Long userId, PropostaDTO propostaDTO) {
        if (Objects.isNull(userId))
            throw new UtenteException(MISSING_PARAMETERS.name());
        if (Objects.isNull(propostaDTO))
            throw new PropostaException(MISSING_PARAMETERS.name());

        if (!utenteCrud.existsById(userId))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return propostaHelper.addPropostaToUser(userId, propostaDTO);
    }

    /**
     * Adds a Prodotto to an Utente
     *
     * @param userId      of the {@link Utente Utente}
     * @param prodottoDTO {@link ProdottoDTO ProdottoDTO} to link to the Utente
     * @return Added Prodotto
     * @throws UtenteException   with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException   with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @throws ProdottoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public Prodotto addProdottoToUser(Long userId, ProdottoDTO prodottoDTO) {
        if (Objects.isNull(userId))
            throw new UtenteException(MISSING_PARAMETERS.name());
        if (Objects.isNull(prodottoDTO))
            throw new ProdottoException(MISSING_PARAMETERS.name());

        if (!utenteCrud.existsById(userId))
            throw new UtenteException(CANNOT_FIND_ELEMENT.name());
        return prodottoHelper.addProdottoToUser(userId, prodottoDTO);
    }

    /**
     * Adds a Pagamento to an Utente
     *
     * @param userId       of the {@link Utente Utente}
     * @param pagamentoDTO {@link PagamentoDTO PagamentoDTO} to link to the Utente
     * @return Added Pagamento
     * @throws UtenteException    with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws UtenteException    with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @throws PagamentoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public Pagamento addPagamentoToUser(Long userId, PagamentoDTO pagamentoDTO) {
        if (Objects.isNull(userId))
            throw new UtenteException(MISSING_PARAMETERS.name());
        if (Objects.isNull(pagamentoDTO))
            throw new PagamentoException(MISSING_PARAMETERS.name());

        if (utenteCrud.existsById(userId))
            return pagamentoHelper.addPagamentoToUser(userId, pagamentoDTO);

        throw new UtenteException(CANNOT_FIND_ELEMENT.name());
    }

}