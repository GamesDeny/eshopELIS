package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdottoHelper {
    @Autowired
    ProdottoCrud prodottoCrud;

    @Autowired
    UtenteHelper utenteHelper;

    @Autowired
    RigaOrdineHelper rigaOrdineHelper;

    @Autowired
    CategoriaHelper categoriaHelper;

    public Prodotto addProdottoToUser(Long userId, ProdottoDTO prodottoDTO) {
        Prodotto p = Prodotto.of(prodottoDTO);
        Utente u = utenteHelper.findById(userId);

        p.setUtente(u);
        return prodottoCrud.saveAndFlush(p);
    }

    public Prodotto findById(Long prodottoId) {
        return prodottoCrud.findById(prodottoId).orElseThrow(() -> new ProdottoException("Cannot find Prodotto"));
    }

    public void linkUtenteToProdotti(Long utenteId, List<Long> prodotti_id) {
        List<Prodotto> result = prodottoCrud.findAllById(prodotti_id);
        if (result.isEmpty())
            throw new ProdottoException("List is empty");

        result.forEach(prodotto -> prodotto.setUtente(utenteHelper.findById(utenteId)));
        prodottoCrud.saveAll(result);
    }

    public void linkCategoriaToProdotto(Long categoriaId, List<Long> prodotti) {
        List<Prodotto> result = prodottoCrud.findAllById(prodotti);
        if (result.isEmpty())
            throw new CategoriaException("List is empty");

        result.forEach(prodotto -> prodotto.setCategoria(categoriaHelper.findById(categoriaId)));
        prodottoCrud.saveAll(result);
    }
}
