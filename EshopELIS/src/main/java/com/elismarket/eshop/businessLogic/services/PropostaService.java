package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.cruderepos.PropostaCrud;
import com.elismarket.eshop.customExceptions.PropostaException;
import com.elismarket.eshop.model.interfaces.Proposta;
import com.elismarket.eshop.model.interfaces.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    @Autowired
    private PropostaCrud propostaCrud;

    public List<Proposta> findAllByIsAccettato(Boolean isAccettato) {
        return propostaCrud.findAllByIsAccettato(isAccettato);
    }

    public List<Proposta> findAllByUtente(Long id) {
        return propostaCrud.findAllByUtente(id);
    }

    public List<Proposta> findAllByUtente(Utente utente) {
        return propostaCrud.findAllByUtente(utente);
    }

    public void removeProposta(Long id) {
        try {
            propostaCrud.deleteById(id);
        } catch (Exception e) {
            throw new PropostaException("Cannot find Proposta for provided item");
        }
    }
}
