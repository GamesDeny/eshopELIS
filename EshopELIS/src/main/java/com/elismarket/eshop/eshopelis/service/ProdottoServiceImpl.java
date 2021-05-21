package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * Service class for CRUD operations and control of Product
 *
 */

@Service
public class ProdottoServiceImpl implements ProdottoService {

    @Autowired
    private ProdottoCrud prodottoCrud;

    public Boolean removeById(Long id) {
        try {
            prodottoCrud.delete(prodottoCrud.findAllById(id));
            return true;
        } catch (Exception e) {
//            throw new ProdottoException("Cannot remove for given id");
        }
        return false;
    }

    public List<ProdottoDTO> getAll() {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAll().forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    public List<ProdottoDTO> findAllByNome(String nome) {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAllByNomeLike(nome).forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    public List<ProdottoDTO> findAllByCategoria(String categoria) {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAllByNomeCategoriaLike(categoria).forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    public List<ProdottoDTO> findByQuantitaMaggiore(Integer quantita) {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAllByQuantitaGreaterThanEqual(quantita).forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    public List<ProdottoDTO> findByQuantitaMinore(Integer quantita) {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAllByQuantitaLessThanEqual(quantita).forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    public List<ProdottoDTO> getProdottoByCategoria(String categoria) {
        List<ProdottoDTO> result = new ArrayList<>();

        prodottoCrud.findAllByNomeCategoriaLike(categoria).forEach(prodotto -> result.add(Prodotto.to(prodotto)));

        return result;
    }

    public List<String> getAllCategoria() {
        return prodottoCrud.findAllCategoria();
    }

    public Boolean saveProdotto(ProdottoDTO prodotto) {
        try {
            prodottoCrud.save(Prodotto.of(prodotto));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean removeProdotto(Long id) {
        try {
            prodottoCrud.deleteById(id);
            return true;
        } catch (Exception e) {
//            throw new ProdottoException("Cannot find Prodotto for provided item");
        }
        return false;
    }

    public ProdottoDTO getById(Long id) {
        if (!prodottoCrud.findById(id).isPresent())
            throw new ProdottoException("Not found");
        return Prodotto.to(prodottoCrud.findById(id).get());
    }
}
