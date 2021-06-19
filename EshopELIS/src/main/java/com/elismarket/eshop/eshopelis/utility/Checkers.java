package com.elismarket.eshop.eshopelis.utility;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.exception.*;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.*;
import static java.util.Objects.isNull;

/**
 * Checker class for various operations
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public class Checkers {

    /**
     * Email checker that controls the email passed as parameter. If the regex confirms it, function returns true
     * the password should have at least:
     * 1 character before @
     * a @
     * 1 character after @
     * a . after @
     * 2 characters after .
     *
     * @param email email to control with regex
     * @return true if mail format is correct else false
     */
    public static Boolean mailChecker(String email) {
        if (email == null)
            return false;

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\" +
                "[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\" +
                ".)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(email).matches();
    }

    /**
     * Password checker that controls the email passed as parameter. If the regex confirms it, function returns true
     * the password should have at least:
     * 1 uppercase letter
     * 1 lowercase letter
     * 1 number
     * 6 characters in total
     * no symbols
     *
     * @param password password to control with regex
     * @return true if mail format is correct else false
     */
    public static Boolean passwordChecker(String password) {
        if (password == null)
            throw new RuntimeException(MISSING_PARAMETERS.name());

        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";

        Pattern pat = Pattern.compile(passwordRegex);

        return pat.matcher(password).matches();
    }

    /**
     * Controls if siglaResidenza is between 1 and 200
     *
     * @param siglaResidenza sigla to control
     * @return true if sigla is correct otherwise false
     */
    public static Boolean siglaChecker(Integer siglaResidenza) {
        return siglaResidenza < 201 && siglaResidenza > 0;
    }

    /**
     * Controls if the age of the user is between 16 and 90 otherwise
     *
     * @param birthDate birthdate to control
     * @return true if the age of the subscriber between 16 and 90
     * @throws UtenteException with {@link ExceptionPhrases#TOO_OLD TOO_OLD} exception if age greater than 90
     * @throws UtenteException with {@link ExceptionPhrases#TOO_YOUNG TOO_YOUNG} exception if age less than 16
     */
    public static Boolean birthDateChecker(LocalDate birthDate) {
        if (LocalDate.now().getYear() - birthDate.getYear() < 16)
            throw new UtenteException(TOO_YOUNG.name());
        if (LocalDate.now().getYear() - birthDate.getYear() > 90)
            throw new UtenteException(TOO_OLD.name());
        return true;
    }

    /**
     * Checks if the param is not null and the needed fields are valid, otherwise
     *
     * @param utenteDTO to control
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} exception
     * @throws UtenteException with {@link ExceptionPhrases#INVALID_MAIL INVALID_MAIL} if mail is not valid
     * @throws UtenteException with {@link ExceptionPhrases#INVALID_PASSWORD INVALID_PASSWORD} if password is not valid
     * @throws UtenteException with {@link ExceptionPhrases#INCONSISTENT_SIGLA INCONSISTENT_SIGLA} if sigla is not valid
     * @see #mailChecker(String)
     * @see #passwordChecker(String)
     * @see #siglaChecker(Integer)
     */
    public static void utenteFieldsChecker(UtenteDTO utenteDTO) {
        if (isNull(utenteDTO))
            throw new UtenteException(MISSING_PARAMETERS.name());

        if (isNull(utenteDTO.nome) || Strings.isBlank(utenteDTO.nome) ||
                isNull(utenteDTO.cognome) || Strings.isBlank(utenteDTO.cognome) ||
                isNull(utenteDTO.siglaResidenza) || isNull(utenteDTO.dataNascita) ||
                isNull(utenteDTO.username) || Strings.isBlank(utenteDTO.username))
            throw new UtenteException(MISSING_PARAMETERS.name());

        if (!mailChecker(utenteDTO.mail))
            throw new UtenteException(INVALID_MAIL.name());
        if (!passwordChecker(utenteDTO.password))
            throw new UtenteException(INVALID_PASSWORD.name());
        if (!siglaChecker(utenteDTO.siglaResidenza))
            throw new UtenteException(INCONSISTENT_SIGLA.name());
    }

    /**
     * Checks if the param is not null and the needed fields are valid, otherwise
     *
     * @param feedbackDTO to control
     * @throws FeedbackException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} exception
     */
    public static void feedbackFieldsChecker(FeedbackDTO feedbackDTO) {
        if (isNull(feedbackDTO))
            throw new FeedbackException(MISSING_PARAMETERS.name());

        if (isNull(feedbackDTO.oggetto) || Strings.isBlank(feedbackDTO.oggetto) ||
                isNull(feedbackDTO.utente_id) ||
                isNull(feedbackDTO.descrizione) || Strings.isBlank(feedbackDTO.descrizione))
            throw new FeedbackException(MISSING_PARAMETERS.name());
    }

    /**
     * Checks if the param is not null and the needed fields are valid, otherwise
     *
     * @param pagamentoDTO to control
     * @throws PagamentoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} exception
     */
    public static void pagamentoFieldsChecker(PagamentoDTO pagamentoDTO) {
        if (isNull(pagamentoDTO))
            throw new PagamentoException(MISSING_PARAMETERS.name());

        if (isNull(pagamentoDTO.tipoMetodo_id) || isNull(pagamentoDTO.utente_id))
            throw new PagamentoException(MISSING_PARAMETERS.name());
    }

    /**
     * Checks if the param is not null and the needed fields are valid, otherwise
     *
     * @param prodottoDTO to control
     * @throws ProdottoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} exception
     */
    public static void prodottoFieldsChecker(ProdottoDTO prodottoDTO) {
        if (isNull(prodottoDTO))
            throw new ProdottoException(MISSING_PARAMETERS.name());

        if (isNull(prodottoDTO.descrizione) || Strings.isBlank(prodottoDTO.descrizione) ||
                isNull(prodottoDTO.nome) || Strings.isBlank(prodottoDTO.nome) ||
                isNull(prodottoDTO.prezzo) || prodottoDTO.prezzo < 0 ||
                isNull(prodottoDTO.quantita) || prodottoDTO.quantita < 0 ||
                prodottoDTO.maxOrd < prodottoDTO.minOrd || prodottoDTO.maxOrd > prodottoDTO.quantita ||
                prodottoDTO.minOrd < 1 || isNull(prodottoDTO.image) || Strings.isBlank(prodottoDTO.image) ||
                isNull(prodottoDTO.categoria_id))
            throw new ProdottoException(MISSING_PARAMETERS.name());
    }

    /**
     * Checks if the param is not null and the needed fields are valid, otherwise
     *
     * @param propostaDTO to control
     * @throws PropostaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} exception
     */
    public static void propostaFieldsChecker(PropostaDTO propostaDTO) {
        if (isNull(propostaDTO))
            throw new PropostaException(MISSING_PARAMETERS.name());

        if (isNull(propostaDTO.nome) || Strings.isBlank(propostaDTO.nome) ||
                isNull(propostaDTO.descrizione) || Strings.isBlank(propostaDTO.descrizione) ||
                isNull(propostaDTO.prezzoProposto) || propostaDTO.prezzoProposto < 0 ||
                isNull(propostaDTO.quantita) || propostaDTO.quantita < 0 ||
                isNull(propostaDTO.utente_id))
            throw new PropostaException(MISSING_PARAMETERS.name());
    }

    /**
     * Checks if the param is not null and the needed fields are valid, otherwise
     *
     * @param rigaOrdineDTO {@link RigaOrdineDTO RigaOrdineDTO} to control
     * @throws RigaOrdineException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} exception
     */
    public static void rigaOrdineFieldsChecker(RigaOrdineDTO rigaOrdineDTO) {
        if (isNull(rigaOrdineDTO))
            throw new RigaOrdineException(MISSING_PARAMETERS.name());

        if (isNull(rigaOrdineDTO.prezzoTotale) || rigaOrdineDTO.prezzoTotale < 0 ||
                isNull(rigaOrdineDTO.scontoApplicato) || rigaOrdineDTO.scontoApplicato < 0)
            throw new RigaOrdineException(MISSING_PARAMETERS.name());
    }

    /**
     * Checks if the param is not null and the needed fields are valid, otherwise
     *
     * @param categoriaDTO to control
     * @throws CategoriaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} exception
     */
    public static void categoriaFieldsChecker(CategoriaDTO categoriaDTO) {
        if (isNull(categoriaDTO))
            throw new CategoriaException(MISSING_PARAMETERS.name());

        if (isNull(categoriaDTO.nome) || Strings.isBlank(categoriaDTO.nome))
            throw new CategoriaException(MISSING_PARAMETERS.name());
    }

    /**
     * Checks if the param is not null and the needed fields are valid, otherwise
     *
     * @param tipoMetodoDTO to control
     * @throws TipoMetodoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} exception
     */
    public static void tipoMetodoFieldsChecker(TipoMetodoDTO tipoMetodoDTO) {
        if (isNull(tipoMetodoDTO))
            throw new TipoMetodoException(MISSING_PARAMETERS.name());

        if (isNull(tipoMetodoDTO.nome) || Strings.isBlank(tipoMetodoDTO.nome))
            throw new TipoMetodoException(MISSING_PARAMETERS.name());
    }
}
