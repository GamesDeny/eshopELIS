package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.helper.CategoriaHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.helper.RigaOrdineHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.ProdottoService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.*;

/*
 *
 * Service class for CRUD operations and control of Product
 *
 */

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    ProdottoCrud prodottoCrud;

    @Autowired
    UtenteHelper utenteHelper;

    @Autowired
    RigaOrdineHelper rigaOrdineHelper;

    @Autowired
    CategoriaHelper categoriaHelper;

    @Autowired
    ProdottoHelper prodottoHelper;


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

    @Override
    public Boolean removeProdotto(Long id) {
        if (!prodottoCrud.existsById(id))
            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

        prodottoCrud.deleteById(id);
        return prodottoCrud.existsById(id);
    }

    @Override
    public List<ProdottoDTO> getAll() {
        if (prodottoCrud.findAll().isEmpty())
            throw new ProdottoException(LIST_IS_EMPTY.name());

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAll().forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

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

    @Override
    public List<ProdottoDTO> findByQuantitaMinore(Integer quantita) {
        if (prodottoCrud.findAllByQuantitaLessThanEqual(quantita).isEmpty())
            throw new ProdottoException(LIST_IS_EMPTY.name());

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAllByQuantitaLessThanEqual(quantita).forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    @Override
    public ProdottoDTO getById(Long id) {
        if (!prodottoCrud.existsById(id))
            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

        return Prodotto.to(prodottoCrud.findById(id).orElseThrow(() -> new ProdottoException(CANNOT_FIND_ELEMENT.name())));
    }

    @Override
    public RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO) {
        if (!prodottoCrud.existsById(prodId))
            throw new ProdottoException(CANNOT_FIND_ELEMENT.name());

        return rigaOrdineHelper.addRigaOrdineToProdotto(prodId, rigaOrdineDTO);
    }

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
}
