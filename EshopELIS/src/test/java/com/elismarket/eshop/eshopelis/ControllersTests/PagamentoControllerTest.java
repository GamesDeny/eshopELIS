package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.exception.PagamentoException;
import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.PagamentoService;
import com.elismarket.eshop.eshopelis.service.interfaces.TipoMetodoService;
import com.elismarket.eshop.eshopelis.service.interfaces.UtenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PagamentoControllerTest {
    @Autowired
    PagamentoService pagamentoService;

    @Autowired
    PagamentoCrud pagamentoCrud;

    @Autowired
    TipoMetodoService tipoMetodoService;

    @Autowired
    UtenteService utenteService;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        pagamentoCrud.deleteAll();
    }

    private TipoMetodoDTO creaMetodo() {
        TipoMetodoDTO tipoMetodoDTO = new TipoMetodoDTO();
        tipoMetodoDTO.nome = "contanti";
        return tipoMetodoService.addTipoMetodo(tipoMetodoDTO);
    }

    private UtenteDTO creaUtente() {
        UtenteDTO utente = new UtenteDTO();
        utente.isAdmin = Boolean.TRUE;
        utente.nome = "z";
        utente.cognome = "z";
        utente.username = "z";
        utente.password = "Zzzzz1";
        utente.mail = "zzz@zzz.zzz";
        utente.siglaResidenza = 100;
        utente.dataNascita = LocalDate.of(2001, 1, 1);

        return utenteService.addUtente(utente);
    }

    @Test
    public void TestAddPagamento() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.descrizione = "pagamento";

        UtenteDTO utenteDTO = creaUtente();
        assertNotNull(utenteDTO);
        assertNotNull(utenteDTO.id);
        pagamentoDTO.utente_id = utenteDTO.id;

        TipoMetodoDTO tipoMetodoDTO = creaMetodo();
        assertNotNull(tipoMetodoDTO);
        assertNotNull(tipoMetodoDTO.id);
        pagamentoDTO.tipoMetodo_id = tipoMetodoDTO.id;


        assertThrows(PagamentoException.class, () -> {
            pagamentoService.addPagamento(null);
        });

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.addPagamento(new PagamentoDTO());
        });

        pagamentoDTO = pagamentoService.addPagamento(pagamentoDTO);
        assertNotNull(pagamentoDTO);
        assertNotNull(pagamentoDTO.id);
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
    public void TestAddOrdineToPagamento() {

    }

}
