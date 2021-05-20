package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import com.elismarket.eshop.eshopelis.exception.PropostaException;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropostaServiceImpl implements PropostaService {

    @Autowired
    private PropostaCrud propostaCrud;

    public List<PropostaDTO> findAllByIsAccettato(Boolean isAccettato) {
        List<PropostaDTO> result = new ArrayList<>();

        propostaCrud.findAllByIsAccettato(isAccettato).forEach(proposta -> result.add(Proposta.to(proposta)));

        return result;
    }

    public List<PropostaDTO> findAllByUtente(Long id) {
        List<PropostaDTO> result = new ArrayList<>();

        propostaCrud.findAllByUtente(id).forEach(proposta -> result.add(Proposta.to(proposta)));

        return result;
    }


    public List<PropostaDTO> findAllByUtente(UtenteDTO utenteDTO) {
        List<PropostaDTO> result = new ArrayList<>();

        propostaCrud.findAllByUtente(Utente.of(utenteDTO)).forEach(proposta -> result.add(Proposta.to(proposta)));

        return result;
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

    public PropostaDTO getById(Long id) {
        if (!propostaCrud.findById(id).isPresent())
            throw new PropostaException("Not Found");
        return Proposta.to(propostaCrud.findById(id).get());
    }
}
