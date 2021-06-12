package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import com.elismarket.eshop.eshopelis.exception.TipoMetodoException;
import com.elismarket.eshop.eshopelis.repository.TipoMetodoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.TipoMetodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    }

    @Test
    public void TestUpdateTipoMetodo() {

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

    }

    @Test
    public void TestGetTipoMetodo() {

    }

}
