package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PagamentoControllerTest {
    @Autowired
    PagamentoService pagamentoService;

    @Autowired
    PagamentoCrud pagamentoCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        pagamentoCrud.deleteAll();
    }

    @Test
    public void TestAddPagamento() {

    }

    @Test
    public void TestupdatePagamento() {

    }

    @Test
    public void TestRemovePagamento() {

    }

    @Test
    public void TestGetAll() {

    }

    @Test
    public void TestGetById() {

    }

    @Test
    public void TestGetByContanti() {

    }

    @Test
    public void TestGetByPaypalMail() {

    }

    @Test
    public void TestAddOrdineToPagamento() {

    }


}
