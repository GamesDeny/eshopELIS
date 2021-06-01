package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.helper.CategoriaHelper;
import com.elismarket.eshop.eshopelis.helper.RigaOrdineHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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


    @Override
    public ProdottoDTO addProdotto(ProdottoDTO prodottoDTO) {
        Checkers.prodottoFieldsChecker(prodottoDTO);
        Prodotto p = Prodotto.of(prodottoDTO);
        p.setCategoria(Categoria.of(categoriaHelper.findById(prodottoDTO.categoria_id)));

        return Prodotto.to(prodottoCrud.saveAndFlush(p));
    }

    @Override
    public ProdottoDTO updateProdotto(Long prodottoId, ProdottoDTO prodottoDTO) {
        if (!prodottoCrud.existsById(prodottoId))
            throw new ProdottoException("Not Found");

        Prodotto p = prodottoCrud.findById(prodottoId).orElseThrow(() -> new ProdottoException("Cannot find Prodotto"));

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

        return Prodotto.to(prodottoCrud.findById(prodottoId).orElseThrow(() -> new ProdottoException("Cannot find Prodotto")));

    }

    @Override
    public Boolean removeProdotto(Long id) {
        if (!prodottoCrud.existsById(id))
            throw new ProdottoException("Cannot find Prodotto for provided item");

        prodottoCrud.deleteById(id);
        return prodottoCrud.existsById(id);
    }

    @Override
    public List<ProdottoDTO> getAll() {
        if (prodottoCrud.findAll().isEmpty())
            throw new ProdottoException("List is empty");

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAll().forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    @Override
    public List<ProdottoDTO> findAllByCategoria(CategoriaDTO categoriaDTO) {
        if (prodottoCrud.findAllByCategoria(Categoria.of(categoriaDTO)).isEmpty())
            throw new ProdottoException("List is empty");

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAllByCategoria(Categoria.of(categoriaDTO)).forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    @Override
    public List<ProdottoDTO> findByQuantitaMaggiore(Integer quantita) {
        if (prodottoCrud.findAllByQuantitaGreaterThanEqual(quantita).isEmpty())
            throw new ProdottoException("List is empty");

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAllByQuantitaGreaterThanEqual(quantita).forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    @Override
    public List<ProdottoDTO> findByQuantitaMinore(Integer quantita) {
        if (prodottoCrud.findAllByQuantitaLessThanEqual(quantita).isEmpty())
            throw new ProdottoException("List is empty");

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAllByQuantitaLessThanEqual(quantita).forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }

    @Override
    public ProdottoDTO getById(Long id) {
        if (!prodottoCrud.existsById(id))
            throw new ProdottoException("Not found");

        return Prodotto.to(prodottoCrud.findById(id).orElseThrow(() -> new ProdottoException("Cannot find Prodotto")));
    }

    @Override
    public RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO) {
        if (!prodottoCrud.existsById(prodId))
            throw new ProdottoException("Cannot find Prodotto");

        return rigaOrdineHelper.addRigaOrdineToProdotto(prodId, rigaOrdineDTO);
    }

    @Override
    public List<ProdottoDTO> getProdottoByCategoria(Long categoriaId) {
        if (Objects.isNull(categoriaId))
            throw new ProdottoException("Missing parameter");
        if (prodottoCrud.findAllByCategoria(Categoria.of(categoriaHelper.findById(categoriaId))).isEmpty())
            throw new ProdottoException("Missing categories");

        List<ProdottoDTO> result = new ArrayList<>();
        prodottoCrud.findAllByCategoria(Categoria.of(categoriaHelper.findById(categoriaId))).forEach(prodotto -> result.add(Prodotto.to(prodotto)));
        return result;
    }
}
