package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.exception.OrdineException;
import com.elismarket.eshop.eshopelis.exception.PagamentoException;
import com.elismarket.eshop.eshopelis.exception.RigaOrdineException;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.*;
import com.elismarket.eshop.eshopelis.service.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class OrdineControllerTest {

    @Autowired
    OrdineService ordineService;

    @Autowired
    OrdineCrud ordineCrud;

    @Autowired
    UtenteService utenteService;

    @Autowired
    UtenteCrud utenteCrud;

    @Autowired
    PagamentoService pagamentoService;

    @Autowired
    PagamentoCrud pagamentoCrud;

    @Autowired
    TipoMetodoService tipoMetodoService;

    @Autowired
    TipoMetodoCrud tipoMetodoCrud;

    @Autowired
    RigaOrdineService rigaOrdineService;

    @Autowired
    RigaOrdineCrud rigaOrdineCrud;

    @Autowired
    ProdottoService prodottoService;

    @Autowired
    ProdottoCrud prodottoCrud;

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaCrud categoriaCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        categoriaCrud.deleteAll();
        prodottoCrud.deleteAll();
        utenteCrud.deleteAll();
        tipoMetodoCrud.deleteAll();
        pagamentoCrud.deleteAll();
        rigaOrdineCrud.deleteAll();
        ordineCrud.deleteAll();
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

    private PagamentoDTO creaPagamento() {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.descrizione = "pagamento";
        pagamentoDTO.utente_id = creaUtente().id;
        pagamentoDTO.tipoMetodo_id = creaMetodo().id;
        return pagamentoService.addPagamento(pagamentoDTO);
    }

    private List<RigaOrdineDTO> creaRigheOrdine() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.nome = "IT";
        categoriaDTO = categoriaService.addCategoria(categoriaDTO);

        ProdottoDTO prodottoDTO = new ProdottoDTO();
        prodottoDTO.nome = "VPN";
        prodottoDTO.descrizione = "servizio VPN";
        prodottoDTO.quantita = 1000;
        prodottoDTO.maxOrd = prodottoDTO.quantita;
        prodottoDTO.prezzo = 1F;
        prodottoDTO.minOrd = 1;
        prodottoDTO.sconto = 0;
        prodottoDTO.image = ".";
        prodottoDTO.categoria_id = categoriaDTO.id;
        prodottoDTO = prodottoService.addProdotto(prodottoDTO);

        RigaOrdineDTO rigaOrdineDTO = new RigaOrdineDTO();
        rigaOrdineDTO.quantitaProdotto = 1;
        rigaOrdineDTO.prezzoTotale = 10F;
        rigaOrdineDTO.scontoApplicato = 0F;
        rigaOrdineDTO.prodotto_id = prodottoDTO.id;

        for (int i = 0; i < 5; i++)
            rigaOrdineService.addRigaOrdine(rigaOrdineDTO);

        return rigaOrdineService.getAll();
    }

    @Test
    public void TestSaveOrdine() {
        OrdineDTO ordineDTO = new OrdineDTO();
        PagamentoDTO pagamentoDTO = creaPagamento();
        ordineDTO.pagamento_id = pagamentoDTO.id;
        ordineDTO.utente_id = pagamentoDTO.utente_id;
        List<RigaOrdineDTO> righe = creaRigheOrdine();

        assertThrows(OrdineException.class, () -> {
            ordineService.saveOrdine(null, null, null);
        });

        assertThrows(OrdineException.class, () -> {
            ordineService.saveOrdine(pagamentoDTO.utente_id, pagamentoDTO.id, new ArrayList<>());
        });

        assertThrows(PagamentoException.class, () -> {
            ordineService.saveOrdine(pagamentoDTO.utente_id, 0L, righe);
        });

        assertThrows(UtenteException.class, () -> {
            ordineService.saveOrdine(0L, pagamentoDTO.id, righe);
        });

        ordineDTO = ordineService.saveOrdine(ordineDTO.utente_id, ordineDTO.pagamento_id, righe);
        assertNotNull(ordineDTO);
        assertNotNull(ordineDTO.id);
    }

    @Test
    public void TestUpdateOrdine() {
        OrdineDTO ordineDTO = new OrdineDTO();
        PagamentoDTO pagamentoDTO = creaPagamento();
        ordineDTO.pagamento_id = pagamentoDTO.id;
        ordineDTO.utente_id = pagamentoDTO.utente_id;
        ordineDTO = ordineService.saveOrdine(pagamentoDTO.utente_id, pagamentoDTO.id, creaRigheOrdine());
        assertNotNull(ordineDTO);
        assertNotNull(ordineDTO.id);
        final Long id = ordineDTO.id;

        OrdineDTO updated = ordineDTO;
        updated.id = null;
        updated.evaso = Boolean.TRUE;

        assertThrows(OrdineException.class, () -> {
            ordineService.updateOrdine(null, null);
        });

        assertThrows(OrdineException.class, () -> {
            ordineService.updateOrdine(id, null);
        });

        assertThrows(OrdineException.class, () -> {
            ordineService.updateOrdine(null, updated);
        });

        assertThrows(OrdineException.class, () -> {
            ordineService.updateOrdine(0L, updated);
        });

        ordineDTO = ordineService.updateOrdine(id, updated);
        assertNotNull(ordineDTO);
        assertEquals(ordineDTO.evaso, Boolean.TRUE);

    }

    //@Test
    //gli ordini piazzati non possono essere rimossi
    public void TestRemoveOrdine() {
        OrdineDTO ordineDTO = new OrdineDTO();
        PagamentoDTO pagamentoDTO = creaPagamento();
        ordineDTO.pagamento_id = pagamentoDTO.id;
        ordineDTO.utente_id = pagamentoDTO.utente_id;
        ordineDTO = ordineService.saveOrdine(pagamentoDTO.utente_id, pagamentoDTO.id, creaRigheOrdine());
        assertNotNull(ordineDTO);
        assertNotNull(ordineDTO.id);
        final Long id = ordineDTO.id;

        assertThrows(OrdineException.class, () -> {
            ordineService.removeOrdine(null);
        });

        assertThrows(OrdineException.class, () -> {
            ordineService.removeOrdine(0L);
        });

        assertFalse(ordineService.removeOrdine(id));
    }

    @Test
    public void TestGetAll() {
        OrdineDTO ordineDTO = new OrdineDTO();
        PagamentoDTO pagamentoDTO = creaPagamento();
        ordineDTO.pagamento_id = pagamentoDTO.id;
        ordineDTO.utente_id = pagamentoDTO.utente_id;
        List<RigaOrdineDTO> righe = creaRigheOrdine();

        for (int i = 0; i < 5; i++)
            ordineService.saveOrdine(pagamentoDTO.utente_id, pagamentoDTO.id, righe);

        List<OrdineDTO> ordini = ordineService.getAll();
        assertNotNull(ordini);
        assertEquals(5, ordini.size());
    }

    @Test
    public void TestGetById() {
        OrdineDTO ordineDTO = new OrdineDTO();
        PagamentoDTO pagamentoDTO = creaPagamento();
        ordineDTO.pagamento_id = pagamentoDTO.id;
        ordineDTO.utente_id = pagamentoDTO.utente_id;
        ordineDTO = ordineService.saveOrdine(pagamentoDTO.utente_id, pagamentoDTO.id, creaRigheOrdine());
        assertNotNull(ordineDTO);
        assertNotNull(ordineDTO.id);

        assertThrows(OrdineException.class, () -> {
            ordineService.getById(null);
        });

        assertThrows(OrdineException.class, () -> {
            ordineService.getById(0L);
        });

        OrdineDTO o = ordineService.getById(ordineDTO.id);
        assertNotNull(o);
        assertEquals(o.id, ordineDTO.id);
    }

    @Test
    public void TestgetAllByUtente() {
        OrdineDTO ordineDTO = new OrdineDTO();
        PagamentoDTO pagamentoDTO = creaPagamento();
        ordineDTO.pagamento_id = pagamentoDTO.id;
        ordineDTO.utente_id = pagamentoDTO.utente_id;
        List<RigaOrdineDTO> righe = creaRigheOrdine();

        for (int i = 0; i < 5; i++)
            ordineService.saveOrdine(pagamentoDTO.utente_id, pagamentoDTO.id, righe);

        List<OrdineDTO> ordini = ordineService.getAllByUtente(ordineDTO.utente_id);
        assertNotNull(ordini);
        assertEquals(5, ordini.size());
    }

    @Test
    public void TestGetEvaso() {
        OrdineDTO ordineDTO = new OrdineDTO();
        PagamentoDTO pagamentoDTO = creaPagamento();
        ordineDTO.pagamento_id = pagamentoDTO.id;
        ordineDTO.utente_id = pagamentoDTO.utente_id;
        List<RigaOrdineDTO> righe = creaRigheOrdine();

        for (int i = 0; i < 5; i++)
            ordineService.saveOrdine(pagamentoDTO.utente_id, pagamentoDTO.id, righe);

        assertThrows(OrdineException.class, () -> {
            ordineService.getEvaso(null);
        });

        List<OrdineDTO> ordiniNotEvasi = ordineService.getEvaso(Boolean.FALSE);
        List<OrdineDTO> ordiniEvasi = ordineService.getEvaso(Boolean.TRUE);
        assertNotNull(ordiniNotEvasi);
        assertNotNull(ordiniEvasi);
        assertEquals(5, ordiniNotEvasi.size());
        assertEquals(0, ordiniEvasi.size());

        OrdineDTO temp = new OrdineDTO();
        for (int i = 0; i < 5; i++) {
            temp = ordineService.saveOrdine(pagamentoDTO.utente_id, pagamentoDTO.id, righe);
            temp.evaso = Boolean.TRUE;
            ordineService.updateOrdine(temp.id, temp);
        }

        ordiniEvasi = ordineService.getEvaso(Boolean.TRUE);
        assertNotNull(ordiniEvasi);
        assertEquals(5, ordiniEvasi.size());
    }

    @Test
    public void TestSetEvadiFalse() {
        OrdineDTO ordineDTO = new OrdineDTO();
        PagamentoDTO pagamentoDTO = creaPagamento();
        ordineDTO.pagamento_id = pagamentoDTO.id;
        ordineDTO.utente_id = pagamentoDTO.utente_id;
        ordineDTO = ordineService.saveOrdine(pagamentoDTO.utente_id, pagamentoDTO.id, creaRigheOrdine());
        assertNotNull(ordineDTO);
        assertNotNull(ordineDTO.id);
        prodottoService.getProdottoOfOrdine(ordineDTO.id).forEach(prodottoDTO -> assertNotEquals(1000, prodottoDTO.quantita));
        final Long id = ordineDTO.id;

        assertThrows(OrdineException.class, () -> {
            ordineService.setEvadiFalse(null);
        });

        assertThrows(OrdineException.class, () -> {
            ordineService.setEvadiFalse(0L);
        });

        ordineDTO = ordineService.setEvadiFalse(id);
        assertNotNull(ordineDTO);
        assertNotNull(ordineDTO.dataEvasione);
        assertEquals(ordineDTO.evaso, Boolean.FALSE);
        prodottoService.getProdottoOfOrdine(ordineDTO.id).forEach(prodottoDTO -> assertEquals(1000, prodottoDTO.quantita));
    }

    @Test
    public void TestAddRigaOrdineToOrdine() {
        List<RigaOrdineDTO> righe = creaRigheOrdine();
        OrdineDTO ordineDTO = new OrdineDTO();
        PagamentoDTO pagamentoDTO = creaPagamento();
        ordineDTO.pagamento_id = pagamentoDTO.id;
        ordineDTO.utente_id = pagamentoDTO.utente_id;
        ordineDTO = ordineService.saveOrdine(pagamentoDTO.utente_id, pagamentoDTO.id, righe);
        assertNotNull(ordineDTO);
        assertNotNull(ordineDTO.id);
        final Long id = ordineDTO.id;

        assertThrows(OrdineException.class, () -> {
            ordineService.addRigaOrdineToOrdine(null, null);
        });

        assertThrows(RigaOrdineException.class, () -> {
            ordineService.addRigaOrdineToOrdine(id, null);
        });

        assertThrows(OrdineException.class, () -> {
            ordineService.addRigaOrdineToOrdine(null, righe.get(0));
        });

        RigaOrdine rigaOrdine;

        for (RigaOrdineDTO rigaOrdineDTO : righe) {
            rigaOrdine = ordineService.addRigaOrdineToOrdine(id, rigaOrdineDTO);
            assertNotNull(rigaOrdineDTO);
            assertEquals(rigaOrdine.getOrdine().getId(), ordineDTO.id);
        }
    }
}
