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

    public void removeById(Long id) {
        prodottoCrud.delete(prodottoCrud.findAllById(id));
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
        Prodotto prod = Prodotto.of(prodotto);
        try {
            prodottoCrud.save(prod);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean updateProdotto(ProdottoDTO prodotto) {
        Prodotto prod = Prodotto.of(prodotto);
        try {
            prodottoCrud.save(prod);
            return true;
        } catch (Exception e) {
//            throw new ProdottoException("Unsuccessfull");
        }
        return false;
    }

    public void removeProdotto(Long id) {
        try {
            prodottoCrud.deleteById(id);
        } catch (Exception e) {
            throw new ProdottoException("Cannot find Prodotto for provided item");
        }
    }

    public ProdottoDTO getById(Long id) {
        if (!prodottoCrud.findById(id).isPresent())
            throw new ProdottoException("Not found");
        return Prodotto.to(prodottoCrud.findById(id).get());
    }
}
