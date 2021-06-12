package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.ProdottoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProdottoControllerTest {
    @Autowired
    ProdottoService prodottoService;

    @Autowired
    ProdottoCrud prodottoCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        prodottoCrud.deleteAll();
    }

    @Test
    public void TestAddProdotto() {

    }

    @Test
    public void TestUpdateProdotto() {

    }

    @Test
    public void TestRemoveProdotto() {

    }

    @Test
    public void TestGetAll() {

    }

    @Test
    public void TestGetById() {

    }

    @Test
    public void TestFindAllByUtente() {

    }

    @Test
    public void TestFindByQuantitaMinore() {

    }

    @Test
    public void TestGetProdottoByCategoria() {

    }

    @Test
    public void TestAddRigaOrdineToProdotto() {

    }


}
