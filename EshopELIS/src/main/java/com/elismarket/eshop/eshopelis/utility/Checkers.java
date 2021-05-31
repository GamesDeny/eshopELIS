package com.elismarket.eshop.eshopelis.utility;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.exception.*;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

//checkers for mail and password
public class Checkers {

    private final String NUMBERS = "1234567890", LETTERS = "qwertyuiopasdfghjklmnbvcxz";

    public static Boolean mailChecker(String email) {
        if (email == null)
            return false;

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\" +
                "[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\" +
                ".)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        return pat.matcher(email).matches();
    }

    public static Boolean passwordChecker(String password) {
        if (password == null)
            throw new RuntimeException("Missing parameter");

        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";

        Pattern pat = Pattern.compile(passwordRegex);

        return pat.matcher(password).matches();
    }

    public static Boolean siglaChecker(Integer siglaResidenza) {
        return siglaResidenza < 201 && siglaResidenza > 0;
    }

    //range 16-90 years to register
    public static Boolean birthDateChecker(LocalDate birthDate) {
        if (birthDate.getYear() - LocalDate.now().getYear() < 16)
            return false;
        if (birthDate.getYear() - LocalDate.now().getYear() > 90)
            throw new UtenteException("Sei troppo vecchio");
        return true;
    }

    public static void utenteFieldsChecker(UtenteDTO utenteDTO) {
        if (Objects.isNull(utenteDTO))
            throw new UtenteException("Missing object");

        if (Objects.isNull(utenteDTO.nome) || Strings.isBlank(utenteDTO.nome) ||
                Objects.isNull(utenteDTO.cognome) || Strings.isBlank(utenteDTO.cognome) ||
                Objects.isNull(utenteDTO.siglaResidenza) || Objects.isNull(utenteDTO.mail) ||
                Objects.isNull(utenteDTO.username) || Strings.isBlank(utenteDTO.username) ||
                Objects.isNull(utenteDTO.password) || Objects.isNull(utenteDTO.dataNascita) ||
                Objects.isNull(utenteDTO.logged) || Objects.isNull(utenteDTO.isAdmin))
            throw new UtenteException("Missing required parameters");

        if (!mailChecker(utenteDTO.mail))
            throw new UtenteException("Invalid mail");
        if (!passwordChecker(utenteDTO.password))
            throw new UtenteException("Invalid password");
        if (!siglaChecker(utenteDTO.siglaResidenza))
            throw new UtenteException("Invalid sigla");
    }

    public static void feedbackFieldsChecker(FeedbackDTO feedbackDTO) {
        if (Objects.isNull(feedbackDTO))
            throw new FeedbackException("Missing object");

        if (Objects.isNull(feedbackDTO.oggetto) || Strings.isBlank(feedbackDTO.oggetto) ||
                Objects.isNull(feedbackDTO.subscriptionDate) || Objects.isNull(feedbackDTO.utente_id) ||
                Objects.isNull(feedbackDTO.descrizione) || Strings.isBlank(feedbackDTO.descrizione))
            throw new FeedbackException("Missing parameters");
    }

    public static void pagamentoFieldsChecker(PagamentoDTO pagamentoDTO) {
        if (Objects.isNull(pagamentoDTO))
            throw new PagamentoException("Missing object");

        if (Objects.isNull(pagamentoDTO.tipoMetodo_id))
            throw new PagamentoException("Missing parameter");
    }

    public static void prodottoFieldsChecker(ProdottoDTO prodottoDTO) {
        if (Objects.isNull(prodottoDTO))
            throw new ProdottoException("Missing object");

        if (Objects.isNull(prodottoDTO.descrizione) || Strings.isBlank(prodottoDTO.descrizione) ||
                Objects.isNull(prodottoDTO.nome) || Strings.isBlank(prodottoDTO.nome) ||
                Objects.isNull(prodottoDTO.prezzo) || prodottoDTO.prezzo < 0 ||
                Objects.isNull(prodottoDTO.nomeCategoria) || Strings.isBlank(prodottoDTO.nomeCategoria) ||
                Objects.isNull(prodottoDTO.quantita) || prodottoDTO.quantita < 0 ||
                prodottoDTO.maxOrd < prodottoDTO.minOrd || prodottoDTO.maxOrd > prodottoDTO.quantita ||
                prodottoDTO.minOrd < 1 || Objects.isNull(prodottoDTO.image) || Strings.isBlank(prodottoDTO.image))
            throw new ProdottoException("Missing parameters");
    }

    public static void propostaFieldsChecker(PropostaDTO propostaDTO) {
        if (Objects.isNull(propostaDTO))
            throw new PropostaException("Missing object");

        if (Objects.isNull(propostaDTO.nome) || Strings.isBlank(propostaDTO.nome) ||
                Objects.isNull(propostaDTO.descrizione) || Strings.isBlank(propostaDTO.descrizione) ||
                Objects.isNull(propostaDTO.prezzoProposto) || propostaDTO.prezzoProposto < 0 ||
                Objects.isNull(propostaDTO.quantita) || propostaDTO.quantita < 0)
            throw new PropostaException("Missing parameters");
    }

    public static void rigaOrdineFieldsChecker(RigaOrdineDTO rigaOrdineDTO) {
        if (Objects.isNull(rigaOrdineDTO))
            throw new RigaOrdineException("Missing object");

        if (Objects.isNull(rigaOrdineDTO.prezzoTotale) || rigaOrdineDTO.prezzoTotale < 0 ||
                Objects.isNull(rigaOrdineDTO.scontoApplicato) || rigaOrdineDTO.scontoApplicato < 0)
            throw new RigaOrdineException("Missing parameters");
    }

    public static void categoriaFieldsChecker(CategoriaDTO categoriaDTO) {
        if (Objects.isNull(categoriaDTO))
            throw new CategoriaException("Missing object");

        if (Objects.isNull(categoriaDTO.nome) || Strings.isBlank(categoriaDTO.nome))
            throw new CategoriaException("Missing parameters");
    }

    public static void tipoMetodoFieldsChecker(TipoMetodoDTO tipoMetodoDTO) {
        if (Objects.isNull(tipoMetodoDTO))
            throw new TipoMetodoException("Missing object");

        if (Objects.isNull(tipoMetodoDTO.nome) || Strings.isBlank(tipoMetodoDTO.nome))
            throw new TipoMetodoException("Missing parameters");
    }
}
