package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.exception.ProdottoException;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.repository.ProdottoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *
 * Service class for CRUD operations and control of Product
 *
 */

@Service
public class ProdottoServiceImpl {

    @Autowired
    private ProdottoCrud prodottoCrud;

    public void removeById(Long id) {
        prodottoCrud.delete(prodottoCrud.findAllById(id));
    }

    public List<Prodotto> getAll() {
        return prodottoCrud.findAll();
    }

    public List<Prodotto> findAllByNome(String nome) {
        return prodottoCrud.findAllByNomeLike(nome);
    }

    public List<Prodotto> findAllByCategoria(String categoria) {
        return prodottoCrud.findAllByNomeCategoriaLike(categoria);
    }

    public List<Prodotto> findByQuantitaMaggiore(Integer quantita) {
        return prodottoCrud.findAllByQuantitaGreaterThanEqual(quantita);
    }

    public List<Prodotto> findByQuantitaMinore(Integer quantita) {
        return prodottoCrud.findAllByQuantitaLessThanEqual(quantita);
    }

    public List<Prodotto> getProdottoByCategoria(String categoria) {
        return prodottoCrud.findAllByNomeCategoriaLike(categoria);
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
            e.printStackTrace();
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

    public Prodotto getById(Long id) {
        if (!prodottoCrud.findById(id).isPresent())
            throw new ProdottoException("Not found");
        return prodottoCrud.findById(id).get();
    }
}
