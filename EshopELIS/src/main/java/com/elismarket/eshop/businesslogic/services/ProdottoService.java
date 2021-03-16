package com.elismarket.eshop.businesslogic.services;

import com.elismarket.eshop.businesslogic.crudrepos.ProdottoCrud;
import com.elismarket.eshop.interfaces.Prodotto;
import com.elismarket.eshop.model.ProdottoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoCrud prodottoCrud;

    public List<Prodotto> findAllByNome(String nome) {
        return prodottoCrud.findAllByNome(nome);
    }

    public boolean saveProdotto(ProdottoImpl prodotto) {
        try {
            prodottoCrud.save(prodotto);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
