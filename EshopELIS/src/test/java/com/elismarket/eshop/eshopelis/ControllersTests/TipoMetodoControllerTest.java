package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.repository.TipoMetodoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.TipoMetodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    }

    @Test
    public void TestUpdateTipoMetodo() {

    }

    @Test
    public void TestDeleteTipoMetodo() {

    }

    @Test
    public void TestGetAllTipoMetodo() {

    }

    @Test
    public void TestGetTipoMetodo() {

    }

}
