package com.elismarket.eshop.businessLogic.services;

import com.elismarket.eshop.businessLogic.cruderepos.PropostaCrud;
import com.elismarket.eshop.customExceptions.PropostaException;
import com.elismarket.eshop.model.dto.PropostaDTO;
import com.elismarket.eshop.model.entities.PropostaImpl;
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

    public Boolean removeProposta(Long id) {
        try {
            propostaCrud.deleteById(id);
            return true;
        } catch (Exception e) {
//            throw new PropostaException("Cannot find Proposta for provided item");
        }
        return false;
    }

    public Boolean addProposta(PropostaDTO propostaDTO) {
        try {
            propostaCrud.save(PropostaImpl.of(propostaDTO));
            return true;
        } catch (Exception e) {
//            throw new PropostaException("Cannot find Proposta for provided item");
        }
        return false;
    }

    public Proposta getById(Long id) {
        if (!propostaCrud.findById(id).isPresent())
            throw new PropostaException("Not Found");
        return propostaCrud.findById(id).get();
    }
}
