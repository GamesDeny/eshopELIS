package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.exception.PropostaException;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaServiceImpl {

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
            propostaCrud.save(Proposta.of(propostaDTO));
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
