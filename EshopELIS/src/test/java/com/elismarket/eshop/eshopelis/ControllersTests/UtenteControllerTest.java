package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.UtenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtenteControllerTest {
    @Autowired
    UtenteService utenteService;

    @Autowired
    UtenteCrud utenteCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        utenteCrud.deleteAll();
    }

    @Test
    public void TestAddUtente() {

    }

    @Test
    public void TestUpdateUtente() {

    }

    @Test
    public void TestRemoveRigaOrdine() {

    }

    @Test
    public void TestGetAll() {

    }

    @Test
    public void TestGetAllAdmin() {

    }

    @Test
    public void TestGetAllUsers() {

    }

    @Test
    public void TestGetById() {

    }

    @Test
    public void TestGetByUsername() {

    }

    @Test
    public void TestGetByMail() {

    }

    @Test
    public void TestGetSiglaResidenza() {

    }

    @Test
    public void TestGetLoginUtente() {

    }

    @Test
    public void TestAddFeedbackToUser() {

    }

    @Test
    public void TestAddPropostaToUser() {

    }

    @Test
    public void TestAddProdottoToUser() {

    }

    @Test
    public void TestAddPagamentoToUser() {

    }

}
