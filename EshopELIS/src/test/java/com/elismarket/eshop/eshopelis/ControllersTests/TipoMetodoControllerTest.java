package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import com.elismarket.eshop.eshopelis.exception.TipoMetodoException;
import com.elismarket.eshop.eshopelis.repository.TipoMetodoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.TipoMetodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TipoMetodoControllerTest {

    @Autowired
    TipoMetodoCrud tipoMetodoCrud;

    @Autowired
    TipoMetodoService tipoMetodoService;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        tipoMetodoCrud.deleteAll();
    }

    @Test
    public void TestAddTipoMetodo() {
        //Creo oggetto da aggiungere
        TipoMetodoDTO tipoMetodoDTO = new TipoMetodoDTO();
        tipoMetodoDTO.nome = "contanti";

        tipoMetodoDTO = tipoMetodoService.addTipoMetodo(tipoMetodoDTO);
        assertNotNull(tipoMetodoDTO);
        assertNotNull(tipoMetodoDTO.id);

        final Long id = tipoMetodoDTO.id;

        assertEquals("contanti", tipoMetodoService.getById(id).nome);

        assertThrows(TipoMetodoException.class, () -> {
            tipoMetodoService.addTipoMetodo(null);
        });

        assertThrows(TipoMetodoException.class, () -> {
            tipoMetodoService.addTipoMetodo(new TipoMetodoDTO());
        });
    }

    @Test
    public void TestUpdateTipoMetodo() {
        //Creo oggetto da aggiungere
        TipoMetodoDTO tipoMetodoDTO = new TipoMetodoDTO();
        tipoMetodoDTO.nome = "contanti";

        tipoMetodoDTO = tipoMetodoService.addTipoMetodo(tipoMetodoDTO);
        assertNotNull(tipoMetodoDTO);
        assertNotNull(tipoMetodoDTO.id);

        final Long id = tipoMetodoDTO.id;

        assertEquals("contanti", tipoMetodoService.getById(id).nome);

        TipoMetodoDTO updated = new TipoMetodoDTO();
        updated.nome = "carta";

        assertThrows(TipoMetodoException.class, () -> {
            tipoMetodoService.updateTipoMetodo(null, null);
        });

        TipoMetodoDTO finalUpdated = updated;
        assertThrows(TipoMetodoException.class, () -> {
            tipoMetodoService.updateTipoMetodo(null, finalUpdated);
        });

        updated = tipoMetodoService.updateTipoMetodo(id, updated);
        assertNotNull(updated);
        assertEquals("carta", tipoMetodoService.getById(id).nome);
    }

    @Test
    public void TestDeleteTipoMetodo() {
        //Creo oggetto da aggiungere
        TipoMetodoDTO tipoMetodoDTO = new TipoMetodoDTO();
        tipoMetodoDTO.nome = "contanti";

        tipoMetodoDTO = tipoMetodoService.addTipoMetodo(tipoMetodoDTO);
        assertNotNull(tipoMetodoDTO);
        assertNotNull(tipoMetodoDTO.id);

        final Long id = tipoMetodoDTO.id;

        assertThrows(TipoMetodoException.class, () -> {
            tipoMetodoService.deleteTipoMetodo(null);
        });

        assertFalse(tipoMetodoService.deleteTipoMetodo(id));
    }

    @Test
    public void TestGetAllTipoMetodo() {
        //Creo oggetti da aggiungere
        for (int i = 0; i < 5; i++) {
            TipoMetodoDTO tipoMetodoDTO = new TipoMetodoDTO();
            tipoMetodoDTO.nome = "contanti";

            tipoMetodoDTO = tipoMetodoService.addTipoMetodo(tipoMetodoDTO);
            assertNotNull(tipoMetodoDTO);
            assertNotNull(tipoMetodoDTO.id);
        }

        List<TipoMetodoDTO> tipoMetodi = tipoMetodoService.getAll();
        assertNotNull(tipoMetodi);
        assertEquals(5, tipoMetodi.size());
    }

    @Test
    public void TestGetTipoMetodo() {

        //Creo oggetto da aggiungere
        TipoMetodoDTO tipoMetodoDTO = new TipoMetodoDTO();
        tipoMetodoDTO.nome = "contanti";

        tipoMetodoDTO = tipoMetodoService.addTipoMetodo(tipoMetodoDTO);
        assertNotNull(tipoMetodoDTO);
        assertNotNull(tipoMetodoDTO.id);

        final Long id = tipoMetodoDTO.id;

        assertThrows(TipoMetodoException.class, () -> {
            tipoMetodoService.getById(null);
        });

        assertNotNull(tipoMetodoService.getById(id));
    }

}
