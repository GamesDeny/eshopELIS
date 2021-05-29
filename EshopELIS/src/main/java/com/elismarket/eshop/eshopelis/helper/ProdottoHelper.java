package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import com.elismarket.eshop.eshopelis.repository.RigaOrdineCrud;
import com.elismarket.eshop.eshopelis.repository.UtenteCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdottoHelper {
    @Autowired
    private ProdottoCrud prodottoCrud;

    @Autowired
    private UtenteCrud utenteCrud;

    @Autowired
    private RigaOrdineCrud rigaOrdineCrud;

    public Prodotto addProdottoToUser(Long userId, ProdottoDTO prodottoDTO) {
        Prodotto p = Prodotto.of(prodottoDTO);
        Utente u = utenteCrud.findById(userId).orElseThrow(() -> new UtenteException("Cannot find Utente"));

        p.setUtente(u);
        return p;
    }
}
