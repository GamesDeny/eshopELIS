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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    }

    @Test
    public void TestRemoveProposta() {

    }

    @Test
    public void TestFindById() {

    }

    @Test
    public void TestFindAll() {

    }

    @Test
    public void TestFindAllByIsAccettato() {

    }

    @Test
    public void TestFindAllByUtente() {

    }


}
