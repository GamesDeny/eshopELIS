package com.elismarket.eshop.eshopelis;

import com.elismarket.eshop.eshopelis.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EshopElisApplicationTests {

    @BeforeAll
    void saveAllModels() {
        Utente admin = CreazioneModel.creaUtente("a", true, 1);
        Utente user = CreazioneModel.creaUtente("b", false, 2);

        Categoria c1 = CreazioneModel.creaCategoria("c1");
        Categoria c2 = CreazioneModel.creaCategoria("c2");
        Categoria c3 = CreazioneModel.creaCategoria("c3");

        Feedback f1 = CreazioneModel.creaFeedback("f1", 1L);
        Feedback f2 = CreazioneModel.creaFeedback("f2", 2L);
        Feedback f3 = CreazioneModel.creaFeedback("f3", 2L);

        TipoMetodo t = CreazioneModel.creaTipoMetodo("contanti");

        Pagamento pa1 = CreazioneModel.creaPagamento(1L);
        Pagamento pa2 = CreazioneModel.creaPagamento(1L);

        Prodotto prod1 = CreazioneModel.creaProdotto("prod1", null);
        Prodotto prod2 = CreazioneModel.creaProdotto("prod2", null);
        Prodotto prod3 = CreazioneModel.creaProdotto("prod3", null);
        Prodotto prod4 = CreazioneModel.creaProdotto("prod4", 2L);
        Prodotto prod5 = CreazioneModel.creaProdotto("prod5", 2L);

        Proposta prop1 = CreazioneModel.creaProposta("prop1", 2L, 1L);
        Proposta prop2 = CreazioneModel.creaProposta("prop2", 2L, 2L);
        Proposta prop3 = CreazioneModel.creaProposta("prop3", 2L, 3L);

        RigaOrdine r1 = CreazioneModel.creaRigaOrdine(1L);
    }

    @Test
    void contextLoads() {
    }


}
