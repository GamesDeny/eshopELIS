package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.exception.UtenteException;
import com.elismarket.eshop.eshopelis.helper.CategoriaHelper;
import com.elismarket.eshop.eshopelis.helper.OrdineHelper;
import com.elismarket.eshop.eshopelis.helper.RigaOrdineHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.ProdottoService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;
import static java.util.Objects.isNull;

/**
 * {@link Prodotto Prodotto} service class for interaction between DB and relative Controller
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Service
public class ProdottoServiceImpl implements ProdottoService {

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
     * @see OrdineHelper
     */
    @Autowired
    OrdineHelper ordineHelper;

    /**
     * @see CategoriaHelper
     */
    @Autowired
    CategoriaHelper categoriaHelper;

    /**
     * Adds a Prodotto
     *
     * @param prodottoDTO {@link ProdottoDTO ProdottoDTO} to add
     * @return Added Prodotto
     */
    @Override
    @Transactional
    public ProdottoDTO addProdotto(ProdottoDTO prodottoDTO) {
        Checkers.prodottoFieldsChecker(prodottoDTO);
        Prodotto p = Prodotto.of(prodottoDTO);

        p.setCategoria(categoriaHelper.findById(prodottoDTO.categoria_id));
        p.setIsDeleted(Boolean.FALSE);

        if (!isNull(prodottoDTO.utente_id))
            p.setUtente(utenteHelper.findById(prodottoDTO.utente_id));

        return Prodotto.to(prodottoCrud.saveAndFlush(p));
    }

    /**
     * Updates Prodotto relative to the id
     *
     * @param prodottoId  of {@link Prodotto Prodotto}
     * @param prodottoDTO {@link ProdottoDTO ProdottoDTO} with updated fields
     * @return updated Prodotto
     * @throws ProdottoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     */
    @Override
    @Transactional
    public ProdottoDTO updateProdotto(Long prodottoId, ProdottoDTO prodottoDTO) {
        if (isNull(prodottoId) || isNull(prodottoDTO))
            throw new ProdottoException(MISSING_PARAMETERS.name());

        if (!prodottoCrud.existsById(prodottoId))
            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

        Prodotto p = prodottoCrud.findById(prodottoId).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name()));

        p.setDescrizione(isNull(prodottoDTO.descrizione) ? p.getDescrizione() : prodottoDTO.descrizione);
        p.setNome(isNull(prodottoDTO.nome) ? p.getNome() : prodottoDTO.nome);
        p.setQuantita(isNull(prodottoDTO.quantita) ? p.getQuantita() : prodottoDTO.quantita);
        p.setImage(isNull(prodottoDTO.image) ? p.getImage() : prodottoDTO.image);
        p.setMaxOrd(isNull(prodottoDTO.maxOrd) ? p.getMaxOrd() : prodottoDTO.maxOrd);
        p.setMinOrd(isNull(prodottoDTO.minOrd) ? p.getMinOrd() : prodottoDTO.minOrd);
        p.setPrezzo(isNull(prodottoDTO.prezzo) ? p.getPrezzo() : prodottoDTO.prezzo);
        p.setSconto(isNull(prodottoDTO.sconto) ? p.getSconto() : prodottoDTO.sconto);
        p.setIsDeleted(isNull(prodottoDTO.isDeleted) ? p.getIsDeleted() : prodottoDTO.isDeleted);

        Checkers.prodottoFieldsChecker(Prodotto.to(p));

        p.setCategoria(categoriaHelper.findById(prodottoDTO.categoria_id));

        if (!isNull(p.getUtente()))
            p.setUtente(isNull(prodottoDTO.utente_id) ? p.getUtente() : utenteHelper.findById(prodottoDTO.utente_id));

        if (!isNull(prodottoDTO.righeOrdine_id))
            rigaOrdineHelper.linkRigheToProdotto(prodottoId, prodottoDTO.righeOrdine_id);

        return Prodotto.to(prodottoCrud.saveAndFlush(p));

    }

    /**
     * Deletes the Prodotto for the provided id
     *
     * @param id of the {@link Prodotto Prodotto} to remove
     * @return HTTP 200 if deleted successfully, else 500
     * @throws ProdottoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws ProdottoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    @Transactional
    public Boolean removeProdotto(Long id) {
        if (isNull(id))
            throw new ProdottoException(MISSING_PARAMETERS.name());

//        if (!prodottoCrud.existsById(id))
//            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

//        prodottoCrud.deleteById(id);
//        return prodottoCrud.existsById(id);

        Prodotto p = prodottoCrud.findById(id).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name()));
        p.setIsDeleted(Boolean.TRUE);
        prodottoCrud.saveAndFlush(p);

        return !prodottoCrud.findById(id).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name())).getIsDeleted();
    }

    /**
     * Retrieves all Prodotto
     *
     * @return List {@link ProdottoDTO ProdottoDTO}
     */
    @Override
    public List<ProdottoDTO> getAll() {
        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAll().forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    /**
     * Retrieves Prodotto for provided id
     *
     * @return {@link ProdottoDTO ProdottoDTO}
     * @throws ProdottoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws ProdottoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public ProdottoDTO getById(Long id) {
        if (isNull(id))
            throw new ProdottoException(MISSING_PARAMETERS.name());

        if (!prodottoCrud.existsById(id))
            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

        return Prodotto.to(prodottoCrud.findById(id).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Retrieves all Prodotto added by a Utente
     *
     * @param userId id of {@link Utente Utente} that has an accepted Proposta
     * @return List {@link ProdottoDTO ProdottoDTO} of Proposta that became a Prodotto
     * @throws UtenteException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public List<ProdottoDTO> findAllByUtente(Long userId) {
        if (isNull(userId))
            throw new UtenteException(MISSING_PARAMETERS.name());

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAllByUtente(utenteHelper.findById(userId)).forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    /**
     * Retrieves all Prodotto for a {@link Categoria Categoria}
     *
     * @param categoriaId id of the Categoria to search
     * @return List {@link ProdottoDTO ProdottoDTO}
     * @throws CategoriaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public List<ProdottoDTO> getProdottoByCategoria(Long categoriaId) {
        if (isNull(categoriaId))
            throw new CategoriaException(MISSING_PARAMETERS.name());

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAllByCategoria(categoriaHelper.findById(categoriaId)).forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    /**
     * Adds a {@link RigaOrdine RigaOrdine} to a Prodotto
     *
     * @param prodId        id of the {@link Prodotto Prodotto} to link to the RigaOrdine
     * @param rigaOrdineDTO {@link RigaOrdineDTO RigaOrdineDTO}
     * @return added RigaOrdine
     * @throws ProdottoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws ProdottoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @see Checkers#rigaOrdineFieldsChecker(RigaOrdineDTO)
     */
    @Override
    public RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO) {
        if (isNull(prodId))
            throw new ProdottoException(MISSING_PARAMETERS.name());
        Checkers.rigaOrdineFieldsChecker(rigaOrdineDTO);

        if (!prodottoCrud.existsById(prodId))
            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

        return rigaOrdineHelper.addRigaOrdineToProdotto(prodId, rigaOrdineDTO);
    }

    /**
     * Returns all {@link Prodotto Prodotto} related to an Order
     *
     * @param ordineId id of the {@link Utente Utente}
     * @return List of the Prodotti related to the user
     */
    @Override
    public List<ProdottoDTO> getProdottoOfOrdine(Long ordineId) {
        if (isNull(ordineId))
            throw new ProdottoException(MISSING_PARAMETERS.name());

        List<ProdottoDTO> prodotti = new ArrayList<>();
        List<RigaOrdine> righe = rigaOrdineHelper.getByOrdine(ordineId);
        righe.forEach(riga -> prodotti.add(Prodotto.to(prodottoCrud.findById(riga.getProdotto().getId()).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name())))));

        return prodotti;
    }
}
