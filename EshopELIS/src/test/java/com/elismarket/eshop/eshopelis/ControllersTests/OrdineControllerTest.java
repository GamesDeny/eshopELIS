package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.OrdineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrdineControllerTest {

    @Autowired
    OrdineService ordineService;

    @Autowired
    OrdineCrud ordineCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        ordineCrud.deleteAll();
    }

    @Test
    public void TestSaveOrdine() {

    }

    @Test
    public void TestUpdateOrdine() {

    }

    @Test
    public void TestRemoveOrdine() {

    }

    @Test
    public void TestGetAll() {

    }

    @Test
    public void TestGetById() {

    }

    @Test
    public void TestgetAllByUtente() {

    }

    @Test
    public void TestGetEvaso() {

    }

    @Test
    public void TestGetDataPrima() {

    }

    @Test
    public void TestGetDataDopo() {

    }

    @Test
    public void TestAddRigaOrdineToOrdine() {

    }


}
