package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.FeedbackDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.exception.FeedbackException;
import com.elismarket.eshop.eshopelis.model.Feedback;
import com.elismarket.eshop.eshopelis.repository.FeedbackCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.FeedbackService;
import com.elismarket.eshop.eshopelis.service.interfaces.UtenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FeedbackControllerTest {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    FeedbackCrud feedbackCrud;

    @Autowired
    UtenteService utenteService;

    @Autowired
    UtenteCrud utenteCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        feedbackCrud.deleteAll();
        utenteCrud.deleteAll();
    }

    @Test
    public void testAddFeedback() {
        UtenteDTO utente = new UtenteDTO();
        utente.isAdmin = Boolean.TRUE;
        utente.nome = "z";
        utente.cognome = "z";
        utente.username = "z";
        utente.password = "Zzzzz1";
        utente.mail = "zzz@zzz.zzz";
        utente.siglaResidenza = 100;
        utente.dataNascita = LocalDate.of(2001, 1, 1);

        utente = utenteService.addUtente(utente);

        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.descrizione = "bello";
        feedbackDTO.oggetto = "descrizione";
        feedbackDTO.utente_id = utente.id;

        feedbackDTO = feedbackService.addFeedback(feedbackDTO);
        assertNotNull(feedbackDTO);
        assertNotNull(feedbackDTO.id);

        assertThrows(FeedbackException.class, () -> {
            feedbackService.addFeedback(null);
        });

        assertThrows(FeedbackException.class, () -> {
            feedbackService.addFeedback(new FeedbackDTO());
        });
    }

    @Test
    public void testUpdateFeedback() {
        UtenteDTO utente = new UtenteDTO();
        utente.isAdmin = Boolean.TRUE;
        utente.nome = "z";
        utente.cognome = "z";
        utente.username = "z";
        utente.password = "Zzzzz1";
        utente.mail = "zzz@zzz.zzz";
        utente.siglaResidenza = 100;
        utente.dataNascita = LocalDate.of(2001, 1, 1);

        utente = utenteService.addUtente(utente);

        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.descrizione = "bello";
        feedbackDTO.oggetto = "descrizione";
        feedbackDTO.utente_id = utente.id;

        feedbackDTO = feedbackService.addFeedback(feedbackDTO);
        final Long id = feedbackDTO.id;

        FeedbackDTO updated = feedbackDTO;
        updated.descrizione += " ancora";

        assertThrows(FeedbackException.class, () -> {
            feedbackService.updateFeedback(null, null);
        });

        assertThrows(FeedbackException.class, () -> {
            feedbackService.updateFeedback(id, null);
        });

        assertThrows(FeedbackException.class, () -> {
            feedbackService.updateFeedback(null, updated);
        });

        assertThrows(FeedbackException.class, () -> {
            feedbackService.updateFeedback(0L, updated);
        });

        assertThrows(FeedbackException.class, () -> {
            feedbackService.updateFeedback(id, new FeedbackDTO());
        });

        feedbackDTO = feedbackService.updateFeedback(id, updated);
        assertNotNull(feedbackDTO);
        assertEquals("bello ancora", feedbackDTO.descrizione);
    }

    @Test
    public void testDeleteFeedback() {
        UtenteDTO utente = new UtenteDTO();
        utente.isAdmin = Boolean.TRUE;
        utente.nome = "z";
        utente.cognome = "z";
        utente.username = "z";
        utente.password = "Zzzzz1";
        utente.mail = "zzz@zzz.zzz";
        utente.siglaResidenza = 100;
        utente.dataNascita = LocalDate.of(2001, 1, 1);

        utente = utenteService.addUtente(utente);

        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.descrizione = "bello";
        feedbackDTO.oggetto = "descrizione";
        feedbackDTO.utente_id = utente.id;

        feedbackDTO = feedbackService.addFeedback(feedbackDTO);
        final Long id = feedbackDTO.id;

        assertThrows(FeedbackException.class, () -> {
            feedbackService.deleteFeedback(null);
        });

        assertThrows(FeedbackException.class, () -> {
            feedbackService.deleteFeedback(0L);
        });

        assertFalse(feedbackService.deleteFeedback(id));
    }

    @Test
    public void testGetById() {
        UtenteDTO utente = new UtenteDTO();
        utente.isAdmin = Boolean.TRUE;
        utente.nome = "z";
        utente.cognome = "z";
        utente.username = "z";
        utente.password = "Zzzzz1";
        utente.mail = "zzz@zzz.zzz";
        utente.siglaResidenza = 100;
        utente.dataNascita = LocalDate.of(2001, 1, 1);

        utente = utenteService.addUtente(utente);

        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.descrizione = "bello";
        feedbackDTO.oggetto = "descrizione";
        feedbackDTO.utente_id = utente.id;

        feedbackDTO = feedbackService.addFeedback(feedbackDTO);
        final Long id = feedbackDTO.id;

        assertThrows(FeedbackException.class, () -> {
            feedbackService.getById(null);
        });

        assertThrows(FeedbackException.class, () -> {
            feedbackService.deleteFeedback(0L);
        });

        assertNotNull(feedbackService.getById(id));
        assertEquals(feedbackService.getById(id).descrizione, "bello");
    }

    @Test
    public void testGetAll() {
        UtenteDTO utente = new UtenteDTO();
        utente.isAdmin = Boolean.TRUE;
        utente.nome = "z";
        utente.cognome = "z";
        utente.username = "z";
        utente.password = "Zzzzz1";
        utente.mail = "zzz@zzz.zzz";
        utente.siglaResidenza = 100;
        utente.dataNascita = LocalDate.of(2001, 1, 1);

        utente = utenteService.addUtente(utente);

        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.oggetto = "descrizione";
        feedbackDTO.utente_id = utente.id;

        List<Feedback> feeds = new ArrayList<>();
        List<FeedbackDTO> feedsDTO = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            feedbackDTO.descrizione = "bello" + i;
            feedbackDTO = feedbackService.addFeedback(feedbackDTO);
        }

        feedsDTO = feedbackService.getAll();
        feeds = feedbackCrud.findAll();

        assertNotNull(feeds);
        assertNotNull(feedsDTO);
        assertEquals(feedsDTO.size(), feeds.size());
    }

    @Test
    public void testGetAllByUtente() {
        UtenteDTO utente = new UtenteDTO();
        utente.isAdmin = Boolean.TRUE;
        utente.nome = "z";
        utente.cognome = "z";
        utente.username = "z";
        utente.password = "Zzzzz1";
        utente.mail = "zzz@zzz.zzz";
        utente.siglaResidenza = 100;
        utente.dataNascita = LocalDate.of(2001, 1, 1);

        utente = utenteService.addUtente(utente);

        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.descrizione = "bello";
        feedbackDTO.oggetto = "descrizione";
        feedbackDTO.utente_id = utente.id;

        List<FeedbackDTO> feedsDTO = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            feedbackDTO.descrizione = "bello" + i;
            feedbackDTO = feedbackService.addFeedback(feedbackDTO);
        }

        feedsDTO = feedbackService.getAll();

        assertNotNull(feedsDTO);
        assertEquals(feedsDTO.size(), 5);
    }
}
