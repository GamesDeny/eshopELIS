package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.exception.*;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.repository.*;
import com.elismarket.eshop.eshopelis.service.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UtenteControllerTest {
    @Autowired
    UtenteService utenteService;

    @Autowired
    UtenteCrud utenteCrud;

    @Autowired
    TipoMetodoService tipoMetodoService;

    @Autowired
    TipoMetodoCrud tipoMetodoCrud;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaCrud categoriaCrud;

    @Autowired
    PropostaService propostaService;

    @Autowired
    PropostaCrud propostaCrud;

    @Autowired
    ProdottoService prodottoService;

    @Autowired
    ProdottoCrud prodottoCrud;

    @Autowired
    PagamentoService pagamentoService;

    @Autowired
    PagamentoCrud pagamentoCrud;

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    FeedbackCrud feedbackCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        feedbackCrud.deleteAll();
        propostaCrud.deleteAll();
        categoriaCrud.deleteAll();
        prodottoCrud.deleteAll();
        pagamentoCrud.deleteAll();
        tipoMetodoCrud.deleteAll();
        utenteCrud.deleteAll();
    }

    private UtenteDTO creaUtente() {
        UtenteDTO utente = new UtenteDTO();
        utente.isAdmin = Boolean.TRUE;
        utente.nome = "z";
        utente.cognome = "z";
        utente.username = "z";
        utente.password = "Zzzzz1";
        utente.mail = "zzz@zzz.zz";
        utente.siglaResidenza = 100;
        utente.dataNascita = LocalDate.of(2001, 1, 1);

        return utenteService.addUtente(utente);
    }

    @Test
    public void TestAddUtente() {
        assertThrows(UtenteException.class, () -> {
            utenteService.addUtente(null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addUtente(new UtenteDTO());
        });

        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO);
        assertNotNull(utenteDTO.id);
    }

    @Test
    public void TestUpdateUtente() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO);
        assertNotNull(utenteDTO.id);
        final Long id = utenteDTO.id;

        UtenteDTO updated = new UtenteDTO();
        updated.nome = "nuovo";

        assertThrows(UtenteException.class, () -> {
            utenteService.updateUtente(null, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.updateUtente(id, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.updateUtente(null, updated);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.updateUtente(0L, updated);
        });

        //wrong parameter
        assertThrows(UtenteException.class, () -> {
            updated.siglaResidenza = 205;
            utenteService.updateUtente(id, updated);
        });

        updated.siglaResidenza = 150;
        updated.password = "Zzzzz1";
        utenteDTO = utenteService.updateUtente(id, updated);
        assertEquals("nuovo", utenteDTO.nome);
        assertEquals(150, utenteDTO.siglaResidenza);
    }

    @Test
    public void TestDeleteUtente() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO);
        assertNotNull(utenteDTO.id);
        final long id = utenteDTO.id;

        assertThrows(UtenteException.class, () -> {
            utenteService.removeUtente(null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.removeUtente(0L);
        });

        assertFalse(utenteService.removeUtente(id));
    }

    @Test
    public void TestGetAll() {
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.isAdmin = Boolean.TRUE;
        utenteDTO.nome = "z";
        utenteDTO.cognome = "z";
        utenteDTO.username = "z";
        utenteDTO.password = "Zzzzz1";
        utenteDTO.mail = "zzz@zzz.zz";
        utenteDTO.siglaResidenza = 100;
        utenteDTO.dataNascita = LocalDate.of(2001, 1, 1);

        for (int i = 0; i < 5; i++) {
            utenteService.addUtente(utenteDTO);
            utenteDTO.username += "z";
            utenteDTO.mail += "z";
            utenteDTO.siglaResidenza += i;
        }

        assertThrows(UtenteException.class, () -> {
            utenteService.getAll(null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getAll("a");
        });

        List<UtenteDTO> utenti = utenteService.getAll("");
        assertNotNull(utenti);
        assertEquals(5, utenti.size());
    }

    @Test
    public void TestGetAllAdmin() {
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.isAdmin = Boolean.TRUE;
        utenteDTO.nome = "z";
        utenteDTO.cognome = "z";
        utenteDTO.username = "z";
        utenteDTO.password = "Zzzzz1";
        utenteDTO.mail = "zzz@zzz.zz";
        utenteDTO.siglaResidenza = 100;
        utenteDTO.dataNascita = LocalDate.of(2001, 1, 1);

        for (int i = 0; i < 5; i++) {
            utenteService.addUtente(utenteDTO);
            utenteDTO.username += "z";
            utenteDTO.mail += "z";
            utenteDTO.siglaResidenza += i;
        }

        assertThrows(UtenteException.class, () -> {
            utenteService.getAll(null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getAll("a");
        });

        List<UtenteDTO> utenti = utenteService.getAll("admin");
        assertNotNull(utenti);
        assertEquals(5, utenti.size());
    }

    @Test
    public void TestGetAllUsers() {
        UtenteDTO utenteDTO = new UtenteDTO();
        utenteDTO.isAdmin = Boolean.FALSE;
        utenteDTO.nome = "z";
        utenteDTO.cognome = "z";
        utenteDTO.username = "z";
        utenteDTO.password = "Zzzzz1";
        utenteDTO.mail = "zzz@zzz.zz";
        utenteDTO.siglaResidenza = 100;
        utenteDTO.dataNascita = LocalDate.of(2001, 1, 1);

        for (int i = 0; i < 5; i++) {
            utenteService.addUtente(utenteDTO);
            utenteDTO.username += "z";
            utenteDTO.mail += "z";
            utenteDTO.siglaResidenza += i;
        }

        assertThrows(UtenteException.class, () -> {
            utenteService.getAll(null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getAll("a");
        });

        List<UtenteDTO> utenti = utenteService.getAll("user");
        assertNotNull(utenti);
        assertEquals(5, utenti.size());
    }

    @Test
    public void TestGetById() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO);
        assertNotNull(utenteDTO.id);

        assertThrows(UtenteException.class, () -> {
            utenteService.getById(null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getById(0L);
        });

        UtenteDTO gotById = utenteService.getById(utenteDTO.id);
        assertNotNull(gotById);
        assertNotNull(gotById.id);
        assertEquals(gotById.id, utenteDTO.id);
    }

    @Test
    public void TestGetByUsername() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO);
        assertNotNull(utenteDTO.id);

        assertThrows(UtenteException.class, () -> {
            utenteService.getByUser(null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getByUser("");
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getByUser("a");
        });

        UtenteDTO gotById = utenteService.getByUser(utenteDTO.username);
        assertNotNull(gotById);
        assertNotNull(gotById.username);
        assertEquals(gotById.username, utenteDTO.username);

    }

    @Test
    public void TestGetByMail() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO);
        assertNotNull(utenteDTO.id);

        assertThrows(UtenteException.class, () -> {
            utenteService.getByMail(null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getByMail("");
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getByMail("a");
        });

        UtenteDTO gotById = utenteService.getByMail(utenteDTO.mail);
        assertNotNull(gotById);
        assertNotNull(gotById.mail);
        assertEquals(gotById.mail, utenteDTO.mail);
    }

    @Test
    public void TestGetLoginUtente() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO);
        assertNotNull(utenteDTO.username);
        final String pass = "Zzzzz1", user = utenteDTO.username;

        assertThrows(UtenteException.class, () -> {
            utenteService.getLoginUtente(null, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getLoginUtente(null, pass);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getLoginUtente(user, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getLoginUtente(user, "aa");
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getLoginUtente(user, "");
        });

        UtenteDTO logged = utenteService.getLoginUtente(user, pass);
        assertNotNull(logged);
        assertEquals(Boolean.TRUE, logged.logged);
    }

    @Test
    public void TestGetLogout() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO);
        assertNotNull(utenteDTO.id);
        final String user = utenteDTO.username, pass = "Zzzzz1";

        utenteDTO = utenteService.getLoginUtente(user, pass);
        assertNotNull(utenteDTO);
        assertEquals(Boolean.TRUE, utenteDTO.logged);

        assertThrows(UtenteException.class, () -> {
            utenteService.getLogout(null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.getLogout(0L);
        });

        assertTrue(utenteService.getLogout(utenteDTO.id));
    }

    @Test
    public void TestAddFeedbackToUser() {
        UtenteDTO utenteDTO = creaUtente();

        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.descrizione = "bello";
        feedbackDTO.oggetto = "descrizione";

        assertThrows(UtenteException.class, () -> {
            utenteService.addFeedbackToUser(null, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addFeedbackToUser(null, feedbackDTO);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addFeedbackToUser(utenteDTO.id, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addFeedbackToUser(0L, feedbackDTO);
        });

        //missing parameter
        assertThrows(FeedbackException.class, () -> {
            utenteService.addFeedbackToUser(utenteDTO.id, feedbackDTO);
        });

        feedbackDTO.utente_id = utenteDTO.id;
        Feedback feedback = utenteService.addFeedbackToUser(utenteDTO.id, feedbackDTO);
        assertNotNull(feedback);
        assertNotNull(feedback.getId());
        assertNotNull(feedback.getUtente().getId());
    }

    @Test
    public void TestAddPropostaToUser() {
        UtenteDTO utenteDTO = creaUtente();
        PropostaDTO propostaDTO = new PropostaDTO();
        propostaDTO.image = "image";
        propostaDTO.nome = "pinguino";
        propostaDTO.descrizione = "molto grasso";
        propostaDTO.prezzoProposto = 1F;
        propostaDTO.quantita = 100;

        assertThrows(UtenteException.class, () -> {
            utenteService.addPropostaToUser(null, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addPropostaToUser(null, propostaDTO);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addPropostaToUser(utenteDTO.id, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addPropostaToUser(0L, propostaDTO);
        });

        //missing parameter
        assertThrows(PropostaException.class, () -> {
            utenteService.addPropostaToUser(utenteDTO.id, propostaDTO);
        });

        propostaDTO.utente_id = utenteDTO.id;
        Proposta proposta = utenteService.addPropostaToUser(utenteDTO.id, propostaDTO);
        assertNotNull(proposta);
        assertNotNull(proposta.getId());
        assertEquals(utenteDTO.id, proposta.getUtente().getId());
    }

    @Test
    public void TestAddProdottoToUser() {
        UtenteDTO utenteDTO = creaUtente();
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.nome = "IT";

        categoriaDTO = categoriaService.addCategoria(categoriaDTO);

        ProdottoDTO prodottoDTO = new ProdottoDTO();
        prodottoDTO.nome = "VPN";
        prodottoDTO.descrizione = "servizio VPN";
        prodottoDTO.quantita = 10;
        prodottoDTO.maxOrd = prodottoDTO.quantita;
        prodottoDTO.prezzo = 1F;
        prodottoDTO.minOrd = 1;
        prodottoDTO.sconto = 0;
        prodottoDTO.image = ".";

        assertThrows(UtenteException.class, () -> {
            utenteService.addProdottoToUser(null, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addProdottoToUser(null, prodottoDTO);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addProdottoToUser(utenteDTO.id, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addProdottoToUser(0L, prodottoDTO);
        });

        //missing parameter
        assertThrows(ProdottoException.class, () -> {
            utenteService.addProdottoToUser(utenteDTO.id, prodottoDTO);
        });

        prodottoDTO.categoria_id = categoriaDTO.id;
        prodottoDTO.utente_id = utenteDTO.id;
        Prodotto prodotto = utenteService.addProdottoToUser(utenteDTO.id, prodottoDTO);
        assertNotNull(prodotto);
        assertNotNull(prodotto.getId());
        assertEquals(utenteDTO.id, prodotto.getUtente().getId());
    }

    @Test
    public void TestAddPagamentoToUser() {
        UtenteDTO utenteDTO = creaUtente();

        TipoMetodoDTO tipoMetodoDTO = new TipoMetodoDTO();
        tipoMetodoDTO.nome = "contanti";
        tipoMetodoDTO = tipoMetodoService.addTipoMetodo(tipoMetodoDTO);

        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.descrizione = "pagamento";
        pagamentoDTO.tipoMetodo_id = tipoMetodoDTO.id;

        assertThrows(UtenteException.class, () -> {
            utenteService.addPagamentoToUser(null, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addPagamentoToUser(null, pagamentoDTO);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addPagamentoToUser(utenteDTO.id, null);
        });

        assertThrows(UtenteException.class, () -> {
            utenteService.addPagamentoToUser(0L, pagamentoDTO);
        });

        //missing parameter
        assertThrows(PagamentoException.class, () -> {
            utenteService.addPagamentoToUser(utenteDTO.id, pagamentoDTO);
        });

        pagamentoDTO.utente_id = utenteDTO.id;
        Pagamento pagamento = utenteService.addPagamentoToUser(utenteDTO.id, pagamentoDTO);

        assertNotNull(pagamento);
        assertNotNull(pagamento.getId());
        assertEquals(pagamento.getUtente().getId(), utenteDTO.id);
    }

}
