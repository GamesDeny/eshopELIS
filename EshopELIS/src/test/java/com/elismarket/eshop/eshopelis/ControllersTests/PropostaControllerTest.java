package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.exception.PropostaException;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;
import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.CategoriaService;
import com.elismarket.eshop.eshopelis.service.interfaces.PropostaService;
import com.elismarket.eshop.eshopelis.service.interfaces.UtenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PropostaControllerTest {
    @Autowired
    PropostaService propostaService;

    @Autowired
    PropostaCrud propostaCrud;

    @Autowired
    UtenteService utenteService;

    @Autowired
    UtenteCrud utenteCrud;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaCrud categoriaCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        propostaCrud.deleteAll();
        utenteCrud.deleteAll();
        categoriaCrud.deleteAll();
    }

    private UtenteDTO creaUtente() {
        UtenteDTO utente = new UtenteDTO();
        utente.isAdmin = Boolean.TRUE;
        utente.nome = "z";
        utente.cognome = "z";
        utente.username = "z";
        utente.password = "Zzzzz1";
        utente.mail = "zzz@zzz.zzz";
        utente.siglaResidenza = 100;
        utente.dataNascita = LocalDate.of(2001, 1, 1);

        return utenteService.addUtente(utente);
    }

    @Test
    public void TestAddProposta() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO.id);

        PropostaDTO propostaDTO = new PropostaDTO();
        propostaDTO.image = "image";
        propostaDTO.nome = "pinguino";
        propostaDTO.descrizione = "molto grasso";
        propostaDTO.prezzoProposto = 1F;
        propostaDTO.quantita = 100;

        assertThrows(PropostaException.class, () -> {
            propostaService.addProposta(null);
        });

        assertThrows(PropostaException.class, () -> {
            propostaService.addProposta(new PropostaDTO());
        });

        //utente doesn't exist
        PropostaDTO finalPropostaDTO = propostaDTO;
        finalPropostaDTO.utente_id = 0L;
        assertThrows(UtenteException.class, () -> {
            propostaService.addProposta(finalPropostaDTO);
        });

        propostaDTO.utente_id = utenteDTO.id;
        propostaDTO = propostaService.addProposta(propostaDTO);
        assertNotNull(propostaDTO);
        assertNotNull(propostaDTO.id);
    }

    @Test
    public void TestUpdateProposta() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO.id);

        PropostaDTO propostaDTO = new PropostaDTO();
        propostaDTO.image = "image";
        propostaDTO.nome = "pinguino";
        propostaDTO.descrizione = "molto grasso";
        propostaDTO.prezzoProposto = 1F;
        propostaDTO.quantita = 100;
        propostaDTO.utente_id = utenteDTO.id;

        propostaDTO = propostaService.addProposta(propostaDTO);
        assertNotNull(propostaDTO);
        assertNotNull(propostaDTO.id);
        final Long id = propostaDTO.id;

        PropostaDTO updated = propostaDTO;
        updated.prezzoProposto = 2F;


        assertThrows(PropostaException.class, () -> {
            propostaService.updateProposta(null, null);
        });

        assertThrows(PropostaException.class, () -> {
            propostaService.updateProposta(null, updated);
        });

        assertThrows(PropostaException.class, () -> {
            propostaService.updateProposta(id, null);
        });

        assertThrows(PropostaException.class, () -> {
            propostaService.updateProposta(0L, updated);
        });

        assertThrows(PropostaException.class, () -> {
            propostaService.updateProposta(0L, new PropostaDTO());
        });

        propostaDTO = propostaService.updateProposta(id, updated);
        assertNotNull(propostaDTO);
        assertEquals(2F, propostaDTO.prezzoProposto);
    }

    @Test
    public void TestRemoveProposta() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO.id);

        PropostaDTO propostaDTO = new PropostaDTO();
        propostaDTO.image = "image";
        propostaDTO.nome = "pinguino";
        propostaDTO.descrizione = "molto grasso";
        propostaDTO.prezzoProposto = 1F;
        propostaDTO.quantita = 100;
        propostaDTO.utente_id = utenteDTO.id;

        propostaDTO = propostaService.addProposta(propostaDTO);
        assertNotNull(propostaDTO);
        assertNotNull(propostaDTO.id);
        final Long id = propostaDTO.id;
    }

    @Test
    public void TestFindById() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO.id);

        PropostaDTO propostaDTO = new PropostaDTO();
        propostaDTO.image = "image";
        propostaDTO.nome = "pinguino";
        propostaDTO.descrizione = "molto grasso";
        propostaDTO.prezzoProposto = 1F;
        propostaDTO.quantita = 100;
        propostaDTO.utente_id = utenteDTO.id;

        propostaDTO = propostaService.addProposta(propostaDTO);
        assertNotNull(propostaDTO);
        assertNotNull(propostaDTO.id);
        final Long id = propostaDTO.id;
    }

    @Test
    public void TestFindAll() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO.id);

        PropostaDTO propostaDTO = new PropostaDTO();
        propostaDTO.image = "image";
        propostaDTO.nome = "pinguino";
        propostaDTO.descrizione = "molto grasso";
        propostaDTO.prezzoProposto = 1F;
        propostaDTO.quantita = 100;
        propostaDTO.utente_id = utenteDTO.id;

        for (int i = 0; i < 5; i++) {
            propostaDTO.quantita += i;
            propostaDTO.nome += i;
            propostaService.addProposta(propostaDTO);
        }

        List<PropostaDTO> all = propostaService.findAll();
        assertNotNull(all);
        assertEquals(5, all.size());
    }

    @Test
    public void TestFindAllByIsAccettato() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO.id);

        PropostaDTO propostaDTO = new PropostaDTO();
        propostaDTO.image = "image";
        propostaDTO.nome = "pinguino";
        propostaDTO.descrizione = "molto grasso";
        propostaDTO.prezzoProposto = 1F;
        propostaDTO.quantita = 100;
        propostaDTO.utente_id = utenteDTO.id;
        propostaDTO.isAccettato = Boolean.TRUE;

        propostaDTO = propostaService.addProposta(propostaDTO);
        assertNotNull(propostaDTO);
        assertNotNull(propostaDTO.id);

        PropostaDTO propostaDTO1 = new PropostaDTO();
        propostaDTO1.image = "image";
        propostaDTO1.nome = "pinguino";
        propostaDTO1.descrizione = "molto grasso";
        propostaDTO1.prezzoProposto = 1F;
        propostaDTO1.quantita = 100;
        propostaDTO1.utente_id = utenteDTO.id;
        propostaDTO1.isAccettato = Boolean.FALSE;

        propostaDTO1 = propostaService.addProposta(propostaDTO1);
        assertNotNull(propostaDTO1);
        assertNotNull(propostaDTO1.id);

        PropostaDTO propostaDTO2 = new PropostaDTO();
        propostaDTO2.image = "image";
        propostaDTO2.nome = "pinguino";
        propostaDTO2.descrizione = "molto grasso";
        propostaDTO2.prezzoProposto = 1F;
        propostaDTO2.quantita = 100;
        propostaDTO2.utente_id = utenteDTO.id;

        propostaDTO2 = propostaService.addProposta(propostaDTO2);
        assertNotNull(propostaDTO2);
        assertNotNull(propostaDTO2.id);

        List<PropostaDTO> accettato = propostaService.findAllByIsAccettato(true);
        List<PropostaDTO> notAccettato = propostaService.findAllByIsAccettato(false);
        List<PropostaDTO> notProcessato = propostaService.findAllByIsAccettato(null);

        assertNotNull(accettato);
        assertNotNull(notAccettato);
        assertNotNull(notProcessato);

        assertEquals(1, accettato.size());
        assertEquals(1, notAccettato.size());
        assertEquals(1, notProcessato.size());

    }

    @Test
    public void TestFindAllByUtente() {
        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO.id);

        PropostaDTO propostaDTO = new PropostaDTO();
        propostaDTO.image = "image";
        propostaDTO.nome = "pinguino";
        propostaDTO.descrizione = "molto grasso";
        propostaDTO.prezzoProposto = 1F;
        propostaDTO.quantita = 100;
        propostaDTO.utente_id = utenteDTO.id;

        for (int i = 0; i < 5; i++) {
            propostaDTO.quantita += i;
            propostaDTO.nome += i;
            propostaService.addProposta(propostaDTO);
        }

        final Long id = utenteDTO.id;


        assertThrows(UtenteException.class, () -> {
            propostaService.findAllByUtente(0L);
        });

        assertThrows(PropostaException.class, () -> {
            propostaService.findAllByUtente(null);
        });

        List<PropostaDTO> all = propostaService.findAllByUtente(id);
        assertNotNull(all);
        assertEquals(5, all.size());
    }

}
