package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.RigaOrdineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RigaOrdineControllerTest {
    @Autowired
    RigaOrdineService rigaOrdineService;

    @Autowired
    RigaOrdineCrud rigaOrdineCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        rigaOrdineCrud.deleteAll();
    }

    @Test
    public void TestAddRigaOrdine() {

    }

    @Test
    public void TestUpdateRigaOrdine() {

    }

    @Test
    public void TestRemoveRigaOrdine() {

    }

    @Test
    public void TestGetAll() {

    }

    @Test
    public void TestGetById() {

    }


}
