package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.exception.PagamentoException;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.repository.PagamentoCrud;
import com.elismarket.eshop.eshopelis.repository.TipoMetodoCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.PagamentoService;
import com.elismarket.eshop.eshopelis.service.interfaces.TipoMetodoService;
import com.elismarket.eshop.eshopelis.service.interfaces.UtenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PagamentoControllerTest {
    @Autowired
    PagamentoService pagamentoService;

    @Autowired
    PagamentoCrud pagamentoCrud;

    @Autowired
    TipoMetodoService tipoMetodoService;

    @Autowired
    TipoMetodoCrud tipoMetodoCrud;

    @Autowired
    UtenteService utenteService;

    @Autowired
    UtenteCrud utenteCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        pagamentoCrud.deleteAll();
        pagamentoCrud.deleteAll();
        utenteCrud.deleteAll();
        tipoMetodoCrud.deleteAll();
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

        PagamentoDTO updated = pagamentoDTO;
        pagamentoDTO = pagamentoService.addPagamento(pagamentoDTO);
        assertNotNull(pagamentoDTO);
        assertNotNull(pagamentoDTO.id);
        final Long id = pagamentoDTO.id;

        updated.descrizione = "updated";

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.updatePagamento(null, null);
        });

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.updatePagamento(null, updated);
        });

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.updatePagamento(id, null);
        });

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.updatePagamento(0L, updated);
        });

        pagamentoDTO = pagamentoService.updatePagamento(id, updated);
        assertNotNull(pagamentoDTO);
        assertEquals("updated", pagamentoDTO.descrizione);
    }

    @Test
    public void TestRemovePagamento() {
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

        pagamentoDTO = pagamentoService.addPagamento(pagamentoDTO);
        assertNotNull(pagamentoDTO);
        assertNotNull(pagamentoDTO.id);


        assertThrows(PagamentoException.class, () -> {
            pagamentoService.removePagamento(null);
        });

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.removePagamento(0L);
        });

        assertFalse(pagamentoService.removePagamento(pagamentoDTO.id));
    }

    @Test
    public void TestGetAll() {
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

        for (int i = 0; i < 5; i++)
            pagamentoService.addPagamento(pagamentoDTO);

        List<PagamentoDTO> all = pagamentoService.getAll();
        assertNotNull(all);
        assertEquals(5, all.size());
    }

    @Test
    public void TestGetById() {

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

        pagamentoDTO = pagamentoService.addPagamento(pagamentoDTO);
        assertNotNull(pagamentoDTO);
        assertNotNull(pagamentoDTO.id);

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.getById(null);
        });

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.getById(0L);
        });

        pagamentoDTO = pagamentoService.getById(pagamentoDTO.id);
        assertNotNull(pagamentoDTO);
        assertEquals("pagamento", pagamentoDTO.descrizione);

    }

    @Test
    public void TestAddOrdineToPagamento() {
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

        OrdineDTO ordineDTO = new OrdineDTO();
        ordineDTO.utente_id = utenteDTO.id;

        pagamentoDTO = pagamentoService.addPagamento(pagamentoDTO);
        assertNotNull(pagamentoDTO);
        assertNotNull(pagamentoDTO.id);
        final Long id = pagamentoDTO.id;

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.addOrdineToPagamento(null, null);
        });

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.addOrdineToPagamento(id, null);
        });

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.addOrdineToPagamento(null, ordineDTO);
        });

        assertThrows(PagamentoException.class, () -> {
            pagamentoService.addOrdineToPagamento(0L, ordineDTO);
        });

        //linkare sia ordine a pagamento che pagamento ad ordine (ONE TO ONE)
        Ordine ordine = pagamentoService.addOrdineToPagamento(pagamentoDTO.id, ordineDTO);
        assertNotNull(ordine);
        assertNotNull(ordine.getId());
        assertEquals(ordine.getPagamento().getDescrizione(), pagamentoDTO.descrizione);
    }

}
