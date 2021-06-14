package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.RigaOrdineException;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.CategoriaService;
import com.elismarket.eshop.eshopelis.service.interfaces.ProdottoService;
import com.elismarket.eshop.eshopelis.service.interfaces.RigaOrdineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RigaOrdineControllerTest {
    @Autowired
    RigaOrdineService rigaOrdineService;

    @Autowired
    RigaOrdineCrud rigaOrdineCrud;

    @Autowired
    ProdottoService prodottoService;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    ProdottoCrud prodottoCrud;

    @Autowired
    CategoriaCrud categoriaCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        rigaOrdineCrud.deleteAll();
        categoriaCrud.deleteAll();
        prodottoCrud.deleteAll();
    }

    @Test
    public void TestAddRigaOrdine() {
        RigaOrdineDTO rigaOrdineDTO = new RigaOrdineDTO();
        rigaOrdineDTO.quantitaProdotto = 1;
        rigaOrdineDTO.prezzoTotale = 1F;
        rigaOrdineDTO.scontoApplicato = 0F;

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

        rigaOrdineDTO.prodotto_id = prodottoDTO.id;
        rigaOrdineDTO = rigaOrdineService.addRigaOrdine(rigaOrdineDTO);

        assertNotNull(rigaOrdineDTO);
        assertNotNull(rigaOrdineDTO.id);

        //null object
        assertThrows(RigaOrdineException.class, () -> {
            rigaOrdineService.addRigaOrdine(null);
        });

        //missing parameters
        assertThrows(RigaOrdineException.class, () -> {
            RigaOrdineDTO temp = new RigaOrdineDTO();
            temp.prezzoTotale = 0F;
            rigaOrdineService.addRigaOrdine(temp);
        });
    }

    @Test
    public void TestUpdateRigaOrdine() {
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

        RigaOrdineDTO rigaOrdineDTO = new RigaOrdineDTO();
        rigaOrdineDTO.quantitaProdotto = 1;
        rigaOrdineDTO.prezzoTotale = 1F;
        rigaOrdineDTO.scontoApplicato = 0F;
        rigaOrdineDTO.prodotto_id = prodottoDTO.id;
        rigaOrdineDTO = rigaOrdineService.addRigaOrdine(rigaOrdineDTO);
        final Long id = rigaOrdineDTO.id;

        assertNotNull(rigaOrdineService.getById(id));

        RigaOrdineDTO updated = rigaOrdineDTO;
        updated.quantitaProdotto = 100;

        assertThrows(RigaOrdineException.class, () -> {
            rigaOrdineService.updateRigaOrdine(null, null);
        });

        assertThrows(RigaOrdineException.class, () -> {
            rigaOrdineService.updateRigaOrdine(null, updated);
        });

        assertThrows(RigaOrdineException.class, () -> {
            rigaOrdineService.updateRigaOrdine(id, null);
        });

        assertThrows(RigaOrdineException.class, () -> {
            rigaOrdineService.updateRigaOrdine(0L, updated);
        });

        rigaOrdineDTO = rigaOrdineService.updateRigaOrdine(id, updated);
        assertNotNull(rigaOrdineDTO);
        assertEquals(rigaOrdineDTO.quantitaProdotto, 100);
    }

    @Test
    public void TestRemoveRigaOrdine() {
        RigaOrdineDTO rigaOrdineDTO = new RigaOrdineDTO();
        rigaOrdineDTO.quantitaProdotto = 1;
        rigaOrdineDTO.prezzoTotale = 1F;
        rigaOrdineDTO.scontoApplicato = 0F;

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

        rigaOrdineDTO.prodotto_id = prodottoDTO.id;

        rigaOrdineDTO = rigaOrdineService.addRigaOrdine(rigaOrdineDTO);
        assertNotNull(rigaOrdineDTO);
        assertNotNull(rigaOrdineDTO.id);
        final Long id = rigaOrdineDTO.id;

        assertThrows(RigaOrdineException.class, () -> {
            rigaOrdineService.removeRigaOrdine(null);
        });

        assertThrows(RigaOrdineException.class, () -> {
            rigaOrdineService.removeRigaOrdine(5L);
        });

        assertFalse(rigaOrdineService.removeRigaOrdine(id));
    }

    @Test
    public void TestGetAll() {

        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.nome = "IT";

        categoriaDTO = categoriaService.addCategoria(categoriaDTO);

        ProdottoDTO prodottoDTO = new ProdottoDTO();
        prodottoDTO.nome = "VPN";
        prodottoDTO.descrizione = "servizio VPN";
        prodottoDTO.quantita = 1000;
        prodottoDTO.maxOrd = prodottoDTO.quantita;
        prodottoDTO.prezzo = 1F;
        prodottoDTO.minOrd = 1;
        prodottoDTO.sconto = 0;
        prodottoDTO.image = ".";
        prodottoDTO.categoria_id = categoriaDTO.id;

        prodottoDTO = prodottoService.addProdotto(prodottoDTO);

        RigaOrdineDTO rigaOrdineDTO = new RigaOrdineDTO();
        rigaOrdineDTO.prezzoTotale = 1F;
        rigaOrdineDTO.scontoApplicato = 0F;
        rigaOrdineDTO.prodotto_id = prodottoDTO.id;

        List<RigaOrdineDTO> righeDTO = new ArrayList<>();
        List<RigaOrdine> righe;
        for (int i = 0; i < 5; i++) {
            rigaOrdineDTO.quantitaProdotto = 1;
            righeDTO.add(rigaOrdineService.addRigaOrdine(rigaOrdineDTO));
            assertNotNull(righeDTO.get(i));
        }

        righeDTO = rigaOrdineService.getAll();
        righe = rigaOrdineCrud.findAll();

        assertNotNull(righe);
        assertNotNull(righeDTO);
        assertEquals(righe.size(), righeDTO.size());
    }

    @Test
    public void TestGetById() {
        RigaOrdineDTO rigaOrdineDTO = new RigaOrdineDTO();
        rigaOrdineDTO.quantitaProdotto = 1;
        rigaOrdineDTO.prezzoTotale = 1F;
        rigaOrdineDTO.scontoApplicato = 0F;

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

        rigaOrdineDTO.prodotto_id = prodottoDTO.id;
        rigaOrdineDTO = rigaOrdineService.addRigaOrdine(rigaOrdineDTO);

        assertNotNull(rigaOrdineDTO);
        assertNotNull(rigaOrdineDTO.id);
        final Long id = rigaOrdineDTO.id;

        //testo che lanci l'eccezione in caso non gli passi nulla
        assertThrows(RigaOrdineException.class, () -> {
            rigaOrdineService.getById(null);
        });

        //testo che lanci l'eccezione in caso non gli passi un id non presente
        assertThrows(RigaOrdineException.class, () -> {
            rigaOrdineService.getById(0L);
        });

        //testo che ritorni un oggetto
        assertNotNull(rigaOrdineService.getById(id));
    }


}
