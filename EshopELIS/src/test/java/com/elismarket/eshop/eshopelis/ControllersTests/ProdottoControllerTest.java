package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.exception.RigaOrdineException;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.CategoriaService;
import com.elismarket.eshop.eshopelis.service.interfaces.ProdottoService;
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
public class ProdottoControllerTest {
    @Autowired
    ProdottoService prodottoService;

    @Autowired
    ProdottoCrud prodottoCrud;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    UtenteService utenteService;

    @Autowired
    UtenteCrud utenteCrud;

    @Autowired
    CategoriaCrud categoriaCrud;

    @Autowired
    RigaOrdineCrud rigaOrdineCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        prodottoCrud.deleteAll();
        utenteCrud.deleteAll();
        categoriaCrud.deleteAll();
        rigaOrdineCrud.deleteAll();
    }

    @Test
    public void TestAddProdotto() {
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
        prodottoDTO.categoria_id = categoriaDTO.id;

        assertThrows(ProdottoException.class, () -> {
            prodottoService.addProdotto(null);
        });

        assertThrows(ProdottoException.class, () -> {
            prodottoService.addProdotto(new ProdottoDTO());
        });

        prodottoDTO = prodottoService.addProdotto(prodottoDTO);
        assertNotNull(prodottoDTO);
        assertNotNull(prodottoDTO.id);

    }

    @Test
    public void TestUpdateProdotto() {
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
        prodottoDTO.categoria_id = categoriaDTO.id;

        prodottoDTO = prodottoService.addProdotto(prodottoDTO);
        final Long id = prodottoDTO.id;

        ProdottoDTO updated = prodottoDTO;
        updated.nome = "updated";

        //testo che lanci l'eccezione in caso non gli passi nulla
        assertThrows(ProdottoException.class, () -> {
            prodottoService.updateProdotto(null, null);
        });

        //testo che lanci l'eccezione in caso non gli passi il DTO aggiornato
        assertThrows(ProdottoException.class, () -> {
            prodottoService.updateProdotto(id, null);
        });

        //testo che lanci l'eccezione in caso non gli passi l'id
        assertThrows(ProdottoException.class, () -> {
            prodottoService.updateProdotto(null, updated);
        });

        //testo che lanci l'eccezione in caso gli passi un id non presente
        assertThrows(ProdottoException.class, () -> {
            prodottoService.updateProdotto(0L, updated);
        });

        //testo che lanci l'eccezione con parametri mancanti
        assertThrows(ProdottoException.class, () -> {
            prodottoService.updateProdotto(id, new ProdottoDTO());
        });

        //testo che lanci l'eccezione in caso non gli passi un oggetto nullo come categoriaDTO
        prodottoDTO = prodottoService.updateProdotto(id, updated);
        assertNotNull(prodottoDTO);
        assertEquals("updated", prodottoDTO.nome);

    }

    @Test
    public void TestRemoveProdotto() {
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
        prodottoDTO.categoria_id = categoriaDTO.id;

        prodottoDTO = prodottoService.addProdotto(prodottoDTO);
        final Long id = prodottoDTO.id;

        assertThrows(ProdottoException.class, () -> {
            prodottoService.removeProdotto(null);
        });

        assertThrows(ProdottoException.class, () -> {
            prodottoService.removeProdotto(0L);
        });

        assertFalse(prodottoService.removeProdotto(id));
    }

    @Test
    public void TestGetAll() {
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
        prodottoDTO.categoria_id = categoriaDTO.id;

        List<ProdottoDTO> prodsDTO = new ArrayList<>();
        List<Prodotto> prods;

        for (int i = 0; i < 5; i++)
            prodsDTO.add(prodottoService.addProdotto(prodottoDTO));

        prodsDTO = prodottoService.getAll();
        prods = prodottoCrud.findAll();

        assertNotNull(prods);
        assertNotNull(prodsDTO);
        assertEquals(prods.size(), prodsDTO.size());
    }

    @Test
    public void TestGetById() {
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
        prodottoDTO.categoria_id = categoriaDTO.id;

        prodottoDTO = prodottoService.addProdotto(prodottoDTO);
        final Long id = prodottoDTO.id;

        assertNotNull(id);

        assertThrows(ProdottoException.class, () -> {
            prodottoService.getById(null);
        });

        assertThrows(ProdottoException.class, () -> {
            prodottoService.getById(0L);
        });

        assertNotNull(prodottoService.getById(id));
    }

    @Test
    public void TestFindAllByUtente() {
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
        prodottoDTO.categoria_id = categoriaDTO.id;

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
        assertNotNull(utente.id);
        prodottoDTO.utente_id = utente.id;

        for (int i = 0; i < 5; i++)
            prodottoService.addProdotto(prodottoDTO);

        assertThrows(UtenteException.class, () -> {
            prodottoService.findAllByUtente(0L);
        });

        assertThrows(UtenteException.class, () -> {
            prodottoService.findAllByUtente(null);
        });

        List<ProdottoDTO> prods = prodottoService.findAllByUtente(utente.id);
        assertNotNull(prods);
        assertEquals(5, prods.size());
    }

    @Test
    public void TestGetProdottoByCategoria() {
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
        prodottoDTO.categoria_id = categoriaDTO.id;

        for (int i = 0; i < 5; i++)
            prodottoService.addProdotto(prodottoDTO);


        assertThrows(CategoriaException.class, () -> {
            prodottoService.getProdottoByCategoria(null);
        });

        assertThrows(CategoriaException.class, () -> {
            prodottoService.getProdottoByCategoria(0L);
        });

        List<ProdottoDTO> prods = prodottoService.getProdottoByCategoria(categoriaDTO.id);
        assertNotNull(prods);
        assertEquals(5, prods.size());
    }

    @Test
    public void TestAddRigaOrdineToProdotto() {
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
        prodottoDTO.categoria_id = categoriaDTO.id;

        prodottoDTO = prodottoService.addProdotto(prodottoDTO);
        assertNotNull(prodottoDTO.id);
        final Long id = prodottoDTO.id;

        RigaOrdineDTO rigaOrdineDTO = new RigaOrdineDTO();
        rigaOrdineDTO.quantitaProdotto = 1;
        rigaOrdineDTO.prezzoTotale = 1F;
        rigaOrdineDTO.scontoApplicato = 0F;
        rigaOrdineDTO.prodotto_id = id;

        assertThrows(ProdottoException.class, () -> {
            prodottoService.addRigaOrdineToProdotto(null, null);
        });

        assertThrows(ProdottoException.class, () -> {
            prodottoService.addRigaOrdineToProdotto(null, rigaOrdineDTO);
        });

        assertThrows(RigaOrdineException.class, () -> {
            prodottoService.addRigaOrdineToProdotto(id, null);
        });

        assertThrows(ProdottoException.class, () -> {
            prodottoService.addRigaOrdineToProdotto(0L, rigaOrdineDTO);
        });

        RigaOrdine rigaOrdine = prodottoService.addRigaOrdineToProdotto(id, rigaOrdineDTO);
        assertNotNull(rigaOrdine);
        assertEquals(rigaOrdine.getProdotto().getId(), id);
    }

    @Test
    public void TestGetProdottoOfOrdine() {

    }
}
