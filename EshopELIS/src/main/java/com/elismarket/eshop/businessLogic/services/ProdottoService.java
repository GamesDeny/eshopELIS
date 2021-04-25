package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.crudrepos.ProdottoCrud;
import com.elismarket.eshop.model.dto.ProdottoDTO;
import com.elismarket.eshop.model.entities.ProdottoImpl;
import com.elismarket.eshop.model.interfaces.Prodotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *
 * Service class for CRUD operations and control of Product
 *
 */

@Service
public class ProdottoService {

    @Autowired
    private ProdottoCrud prodottoCrud;

    public void removeById(Long id) {
        prodottoCrud.delete(prodottoCrud.findAllById(id));
    }

    public List<ProdottoImpl> getAll() {
        return (List<ProdottoImpl>) prodottoCrud.findAll();
    }

    public List<Prodotto> findAllByNome(String nome) {
        return prodottoCrud.findAllByNome(nome);
    }

    public List<Prodotto> findAllByCategoria(String categoria) {
        return prodottoCrud.findAllByNomeCategoria(categoria);
    }

    public List<Prodotto> findByQuantitaMaggiore(Integer quantita) {
        return prodottoCrud.findAllByQuantitaGreaterThanEqual(quantita);
    }

    public List<Prodotto> findByQuantitaMinore(Integer quantita) {
        return prodottoCrud.findAllByQuantitaLessThanEqual(quantita);
    }

    public List<Prodotto> getProdottoByCategoria(ProdottoDTO prodottoDTO) {
        return prodottoCrud.findAllByNomeCategoria(prodottoDTO.nomeCategoria);
    }

    public boolean saveProdotto(ProdottoDTO prodotto) {
        ProdottoImpl prod = ProdottoImpl.of(prodotto);
        try {
            prodottoCrud.save(prod);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
