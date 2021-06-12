package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.CategoriaService;
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

    @Autowired
    CategoriaService categoriaService;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        prodottoCrud.deleteAll();
    }

    @Test
    public void TestAddProdotto() {
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
