package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.PropostaService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropostaControllerTest {
    @Autowired
    PropostaService propostaService;

    @Autowired
    PropostaCrud propostaCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    @AfterEach
    public void deleteDb() {
        propostaCrud.deleteAll();
    }

    @Test
    public void TestAddProposta() {

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
