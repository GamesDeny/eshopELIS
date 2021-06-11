package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.INSUFFICIENT_QUANTITA;

/**
 * Helper class for {@link Prodotto Prodotto} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Component
public class ProdottoHelper {
    /**
     * @see ProdottoCrud
     */
    @Autowired
    ProdottoCrud prodottoCrud;

    /**
     * @see UtenteHelper
     */
    @Autowired
    UtenteHelper utenteHelper;

    /**
     * @see RigaOrdineHelper
     */
    @Autowired
    RigaOrdineHelper rigaOrdineHelper;

    /**
     * @see CategoriaHelper
     */
    @Autowired
    CategoriaHelper categoriaHelper;

    /**
     * Adds a Prodotto related to an Utente after his Proposta is accepted
     *
     * @param utenteId    is of the {@link Utente Utente} that created the {@link Proposta Proposta} transformed into Prodotto
     * @param prodottoDTO {@link ProdottoDTO ProdottoDTO} transformed to Prodotto
     * @return {@link Prodotto Prodotto} linked to user
     */
    public Prodotto addProdottoToUser(Long utenteId, ProdottoDTO prodottoDTO) {
        Prodotto p = Prodotto.of(prodottoDTO);
        Utente u = utenteHelper.findById(utenteId);

        p.setUtente(u);
        return prodottoCrud.saveAndFlush(p);
    }

    /**
     * Retrieves a Prodotto for the relative id
     *
     * @param prodottoId id for the {@link Prodotto Prodotto} to find
     * @return Prodotto retrieved
     * @throws ProdottoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     */
    public Prodotto findById(Long prodottoId) {
        return prodottoCrud.findById(prodottoId).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name()));
    }

    /**
     * links Categoria to relative Prodotto
     *
     * @param utenteId    if for the {@link Utente Utente} to retrieve
     * @param prodotti_id list of {@link Prodotto Prodotto} to be linked
     */
    public void linkUtenteToProdotti(Long utenteId, List<Long> prodotti_id) {
        List<Prodotto> result = prodottoCrud.findAllById(prodotti_id);

        result.forEach(prodotto -> prodotto.setUtente(utenteHelper.findById(utenteId)));
        prodottoCrud.saveAll(result);
    }

    /**
     * links Categoria to relative Prodotto
     *
     * @param categoriaId if for the {@link Categoria Categoria} to retrieve
     * @param prodotti    list of {@link Prodotto Prodotto} to be linked
     */
    public void linkCategoriaToProdotto(Long categoriaId, List<Long> prodotti) {
        List<Prodotto> result = prodottoCrud.findAllById(prodotti);
        result.forEach(prodotto -> prodotto.setCategoria(categoriaHelper.findById(categoriaId)));
        prodottoCrud.saveAll(result);
    }

    /**
     * adds {@link Prodotto Prodotto} for the relative approved {@link Proposta Proposta}
     *
     * @param propostaDTO {@link PropostaDTO PropostaDTO}
     */
    public void addProdotto(PropostaDTO propostaDTO) {
        Prodotto p = new Prodotto();

        p.setNome(propostaDTO.nome);
        p.setDescrizione(propostaDTO.descrizione);
        p.setMinOrd(1);
        p.setMaxOrd(propostaDTO.quantita);
        p.setPrezzo(propostaDTO.prezzoProposto);
        p.setQuantita(propostaDTO.quantita);
        p.setSconto(0);
        p.setImage(propostaDTO.image);
        p.setUtente(utenteHelper.findById(propostaDTO.utente_id));
        p.setCategoria(categoriaHelper.findById(propostaDTO.categoria_id));

        prodottoCrud.saveAndFlush(p);
    }

    /**
     * Decrease available quantita for a Prodotto
     *
     * @param prodotto_id      id of the {@link Prodotto Prodotto} bought
     * @param quantitaProdotto quantita to remove from the Prodotto
     * @throws ProdottoException with {@link ExceptionPhrases#INSUFFICIENT_QUANTITA INSUFFICIENT_QUANTITA} message
     * @throws ProdottoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     */
    public void decreaseQuantita(Long prodotto_id, Integer quantitaProdotto) {
        Prodotto p = prodottoCrud.findById(prodotto_id).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name()));
        if (p.getQuantita() - quantitaProdotto < 0)
            throw new ProdottoException(INSUFFICIENT_QUANTITA.name());

        p.setQuantita(p.getQuantita() - quantitaProdotto);
        prodottoCrud.saveAndFlush(p);
    }
}
