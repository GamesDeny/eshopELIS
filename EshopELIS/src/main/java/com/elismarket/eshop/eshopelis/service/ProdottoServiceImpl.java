package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.helper.CategoriaHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.*;

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
     * @see CategoriaHelper
     */
    @Autowired
    CategoriaHelper categoriaHelper;

    /**
     * @see ProdottoHelper
     */
    @Autowired
    ProdottoHelper prodottoHelper;

    /**
     * Adds a Prodotto
     *
     * @param prodottoDTO {@link ProdottoDTO ProdottoDTO} to add
     * @return Added Prodotto
     */
    @Override
    public ProdottoDTO addProdotto(ProdottoDTO prodottoDTO) {
        Checkers.prodottoFieldsChecker(prodottoDTO);
        Prodotto p = Prodotto.of(prodottoDTO);
        CategoriaDTO c = categoriaHelper.findById(prodottoDTO.categoria_id);
        p.setCategoria(Categoria.of(c));

        if (!Objects.isNull(prodottoDTO.utente_id))
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
    public ProdottoDTO updateProdotto(Long prodottoId, ProdottoDTO prodottoDTO) {
        if (!prodottoCrud.existsById(prodottoId))
            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

        Prodotto p = prodottoCrud.findById(prodottoId).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name()));

        prodottoDTO.id = prodottoId;
        prodottoDTO.descrizione = Objects.isNull(prodottoDTO.descrizione) ? p.getDescrizione() : prodottoDTO.descrizione;
        prodottoDTO.nome = Objects.isNull(prodottoDTO.nome) ? p.getNome() : prodottoDTO.nome;
        prodottoDTO.quantita = Objects.isNull(prodottoDTO.quantita) ? p.getQuantita() : prodottoDTO.quantita;
        prodottoDTO.image = Objects.isNull(prodottoDTO.image) ? p.getImage() : prodottoDTO.image;
        prodottoDTO.maxOrd = Objects.isNull(prodottoDTO.maxOrd) ? p.getMaxOrd() : prodottoDTO.maxOrd;
        prodottoDTO.minOrd = Objects.isNull(prodottoDTO.minOrd) ? p.getMinOrd() : prodottoDTO.minOrd;
        prodottoDTO.prezzo = Objects.isNull(prodottoDTO.prezzo) ? p.getPrezzo() : prodottoDTO.prezzo;
        prodottoDTO.sconto = Objects.isNull(prodottoDTO.sconto) ? p.getSconto() : prodottoDTO.sconto;

        Checkers.prodottoFieldsChecker(prodottoDTO);

        Prodotto save = Prodotto.of(prodottoDTO);
        if (!Objects.isNull(p.getUtente()))
            save.setUtente(Objects.isNull(prodottoDTO.utente_id) ? p.getUtente() : utenteHelper.findById(prodottoDTO.utente_id));

        save.setCategoria(Categoria.of(categoriaHelper.findById(prodottoDTO.categoria_id)));

        if (!Objects.isNull(prodottoDTO.righeOrdine_id))
            rigaOrdineHelper.linkRigheToProdotto(prodottoId, prodottoDTO.righeOrdine_id);
        prodottoCrud.saveAndFlush(save);

        return Prodotto.to(prodottoCrud.findById(prodottoId).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name())));

    }

    /**
     * Deletes the Prodotto with the provided id
     *
     * @param id of the {@link Prodotto Prodotto} to remove
     * @return HTTP 200 if deleted successfully, else 500
     * @throws ProdottoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     */
    @Override
    public Boolean removeProdotto(Long id) {
        if (!prodottoCrud.existsById(id))
            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

        prodottoCrud.deleteById(id);
        return prodottoCrud.existsById(id);
    }

    /**
     * Retrieves all Prodotto
     *
     * @return List {@link ProdottoDTO ProdottoDTO}
     * @throws ProdottoException with {@link ExceptionPhrases#LIST_IS_EMPTY LIST_IS_EMPTY} message
     */
    @Override
    public List<ProdottoDTO> getAll() {
        if (prodottoCrud.findAll().isEmpty())
            throw new ProdottoException(LIST_IS_EMPTY.name());

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAll().forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    /**
     * Retrieves Prodotto for provided id
     *
     * @return {@link ProdottoDTO ProdottoDTO}
     * @throws ProdottoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     */
    @Override
    public ProdottoDTO getById(Long id) {
        if (!prodottoCrud.existsById(id))
            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

        return Prodotto.to(prodottoCrud.findById(id).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Retrieves all Prodotto added by a Utente
     *
     * @param userId id of {@link Utente Utente} that has an accepted Proposta
     * @return List {@link ProdottoDTO ProdottoDTO} of Proposta that became a Prodotto
     * @throws ProdottoException with {@link ExceptionPhrases#LIST_IS_EMPTY LIST_IS_EMPTY} message
     * @throws ProdottoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public List<ProdottoDTO> findAllByUtente(Long userId) {
        if (Objects.isNull(userId))
            throw new ProdottoException(MISSING_PARAMETERS.name());

        if (prodottoCrud.findAllByUtente(utenteHelper.findById(userId)).isEmpty())
            throw new ProdottoException(LIST_IS_EMPTY.name());

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAllByUtente(utenteHelper.findById(userId)).forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    /**
     * Retrieves all Prodotto where quantita less than the value
     *
     * @param quantita value to compare
     * @return List {@link ProdottoDTO ProdottoDTO}
     * @throws ProdottoException with {@link ExceptionPhrases#LIST_IS_EMPTY LIST_IS_EMPTY} message
     * @see Prodotto#getQuantita()
     */
    @Override
    public List<ProdottoDTO> findByQuantitaMinore(Integer quantita) {
        if (prodottoCrud.findAllByQuantitaLessThanEqual(quantita).isEmpty())
            throw new ProdottoException(LIST_IS_EMPTY.name());

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAllByQuantitaLessThanEqual(quantita).forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    /**
     * Retrieves all Prodotto for a {@link Categoria Categoria}
     *
     * @param categoriaId id of the Categoria to search
     * @return List {@link ProdottoDTO ProdottoDTO}
     * @throws ProdottoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public List<ProdottoDTO> getProdottoByCategoria(Long categoriaId) {
        if (Objects.isNull(categoriaId))
            throw new ProdottoException(MISSING_PARAMETERS.name());
        if (prodottoCrud.findAllByCategoria(Categoria.of(categoriaHelper.findById(categoriaId))).isEmpty())
            throw new ProdottoException(MISSING_PARAMETERS.name());

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAllByCategoria(Categoria.of(categoriaHelper.findById(categoriaId))).forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    /**
     * Adds a {@link RigaOrdine RigaOrdine} to a Prodotto
     *
     * @param prodId        id of the {@link Prodotto Prodotto} to link to the RigaOrdine
     * @param rigaOrdineDTO {@link RigaOrdineDTO RigaOrdineDTO}
     * @return added RigaOrdine
     * @throws ProdottoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     */
    @Override
    public RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO) {
        if (!prodottoCrud.existsById(prodId))
            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

        return rigaOrdineHelper.addRigaOrdineToProdotto(prodId, rigaOrdineDTO);
    }
}
