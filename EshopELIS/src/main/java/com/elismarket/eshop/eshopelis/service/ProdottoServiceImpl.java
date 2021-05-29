package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.helper.RigaOrdineHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
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
    private UtenteHelper utenteHelper;

    @Autowired
    private RigaOrdineHelper rigaOrdineHelper;

    @Autowired
    private ProdottoCrud prodottoCrud;

    @Override
    public ProdottoDTO saveProdotto(ProdottoDTO prodottoDTO) {
        if (prodottoDTO.minOrd > prodottoDTO.maxOrd)
            throw new ProdottoException("Inconsistent ord");

        try {
            prodottoCrud.save(Prodotto.of(prodottoDTO));
        } catch (Exception e) {
            throw new ProdottoException("Cannot save Prodotto");
        }
        return prodottoCrud.findById(prodottoDTO.id).isPresent() ? Prodotto.to(prodottoCrud.findById(prodottoDTO.id).orElseThrow(() -> new ProdottoException("Cannot find Prodotto"))) : null;
    }

    @Override
    public ProdottoDTO updateProdotto(Long id, ProdottoDTO prodottoDTO) {
        if (prodottoDTO.minOrd > prodottoDTO.maxOrd)
            throw new ProdottoException("Inconsistent ord");
        if (!prodottoCrud.existsById(id))
            throw new ProdottoException("Not Found");

        Prodotto p = prodottoCrud.findById(id).orElseThrow(() -> new ProdottoException("Cannot find Prodotto"));

        prodottoDTO.id = id;
        prodottoDTO.descrizione = Objects.isNull(prodottoDTO.descrizione) ? p.getDescrizione() : prodottoDTO.descrizione;
        prodottoDTO.nome = Objects.isNull(prodottoDTO.nome) ? p.getNome() : prodottoDTO.nome;
        prodottoDTO.quantita = Objects.isNull(prodottoDTO.quantita) ? p.getQuantita() : prodottoDTO.quantita;
        prodottoDTO.image = Objects.isNull(prodottoDTO.image) ? p.getImage() : prodottoDTO.image;
        prodottoDTO.maxOrd = Objects.isNull(prodottoDTO.maxOrd) ? p.getMaxOrd() : prodottoDTO.maxOrd;
        prodottoDTO.minOrd = Objects.isNull(prodottoDTO.minOrd) ? p.getMinOrd() : prodottoDTO.minOrd;
        prodottoDTO.nomeCategoria = Objects.isNull(prodottoDTO.nomeCategoria) ? p.getNomeCategoria() : prodottoDTO.nomeCategoria;
        prodottoDTO.prezzo = Objects.isNull(prodottoDTO.prezzo) ? p.getPrezzo() : prodottoDTO.prezzo;
        prodottoDTO.sconto = Objects.isNull(prodottoDTO.sconto) ? p.getSconto() : prodottoDTO.sconto;


        return Prodotto.to(prodottoCrud.findById(id).orElseThrow(() -> new ProdottoException("Cannot find Prodotto")));

    }

    @Override
    public Boolean removeProdotto(Long id) {
        try {
            prodottoCrud.deleteById(id);
        } catch (Exception e) {
            throw new ProdottoException("Cannot find Prodotto for provided item");
        }
        return prodottoCrud.findById(id).isPresent();
    }

    @Override
    public List<ProdottoDTO> getAll() {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAll().forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    @Override
    public List<ProdottoDTO> findAllByCategoria(String categoria) {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAllByNomeCategoriaLike(categoria).forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    @Override
    public List<ProdottoDTO> findByQuantitaMaggiore(Integer quantita) {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAllByQuantitaGreaterThanEqual(quantita).forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    @Override
    public List<ProdottoDTO> findByQuantitaMinore(Integer quantita) {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAllByQuantitaLessThanEqual(quantita).forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    @Override
    public List<ProdottoDTO> getProdottoByCategoria(String categoria) {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAllByNomeCategoriaLike(categoria).forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    @Override
    public List<String> getAllCategoria() {
        if (prodottoCrud.findAllCategoria().size() == 0)
            throw new ProdottoException("Error in DB");
        return prodottoCrud.findAllCategoria();
    }

    @Override
    public ProdottoDTO getById(Long id) {
        if (!prodottoCrud.findById(id).isPresent())
            throw new ProdottoException("Not found");
        return Prodotto.to(prodottoCrud.findById(id).orElseThrow(() -> new ProdottoException("Cannot find Prodotto")));
    }

    @Override
    public RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO) {
        return rigaOrdineHelper.addRigaOrdineToProdotto(prodId, rigaOrdineDTO);
    }
}
