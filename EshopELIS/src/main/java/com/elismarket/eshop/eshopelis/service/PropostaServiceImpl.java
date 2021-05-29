package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.exception.PropostaException;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PropostaServiceImpl implements PropostaService {

    @Autowired
    private UtenteHelper utenteHelper;

    @Autowired
    private PropostaCrud propostaCrud;

    @Override
    public PropostaDTO addProposta(PropostaDTO propostaDTO) {
        Checkers.propostaFieldsChecker(propostaDTO);
        propostaCrud.save(Proposta.of(propostaDTO));
        return Proposta.to(propostaCrud.findById(propostaDTO.id).orElseThrow(() -> new PropostaException("Cannot find Proposta")));
    }

    @Override
    public PropostaDTO updateProposta(Long id, PropostaDTO propostaDTO) {
        if (!propostaCrud.existsById(id))
            throw new PropostaException("Not Found");

        Proposta p = propostaCrud.findById(id).orElseThrow(() -> new PropostaException("Cannot find Proposta"));

        propostaDTO.id = id;
        propostaDTO.prezzoProposto = Objects.isNull(propostaDTO.prezzoProposto) ? p.getPrezzoProposto() : propostaDTO.prezzoProposto;
        propostaDTO.nome = Objects.isNull(propostaDTO.nome) ? p.getNome() : propostaDTO.nome;
        propostaDTO.descrizione = Objects.isNull(propostaDTO.descrizione) ? p.getDescrizione() : propostaDTO.descrizione;
        propostaDTO.isAccettato = Objects.isNull(propostaDTO.isAccettato) ? p.getIsAccettato() : propostaDTO.isAccettato;
        propostaDTO.motivoRifiuto = Objects.isNull(propostaDTO.motivoRifiuto) ? p.getMotivoRifiuto() : propostaDTO.motivoRifiuto;
        propostaDTO.quantita = Objects.isNull(propostaDTO.quantita) ? p.getQuantita() : propostaDTO.quantita;
        propostaDTO.submissionDate = Objects.isNull(propostaDTO.submissionDate) ? p.getSubmissionDate() : propostaDTO.submissionDate;

        Checkers.propostaFieldsChecker(propostaDTO);

        return Proposta.to(propostaCrud.findById(id).orElseThrow(() -> new PropostaException("Cannot find Proposta")));
    }

    @Override
    public Boolean removeProposta(Long id) {
        if (!propostaCrud.existsById(id))
            throw new PropostaException("Not Found");

        propostaCrud.deleteById(id);
        return !propostaCrud.findById(id).isPresent();
    }

    @Override
    public List<PropostaDTO> findAllByIsAccettato(Boolean isAccettato) {
        if (propostaCrud.findAllByIsAccettato(isAccettato).isEmpty())
            throw new PropostaException("List is empty");

        List<PropostaDTO> result = new ArrayList<>();
        propostaCrud.findAllByIsAccettato(isAccettato).forEach(proposta -> result.add(Proposta.to(proposta)));
        return result;
    }

    @Override
    public List<PropostaDTO> findAllByUtente(Long id) {
        if (propostaCrud.findAllByUtente(id).isEmpty())
            throw new PropostaException("List is empty");

        List<PropostaDTO> result = new ArrayList<>();
        propostaCrud.findAllByUtente(id).forEach(proposta -> result.add(Proposta.to(proposta)));
        return result;
    }

    @Override
    public PropostaDTO getById(Long id) {
        if (!propostaCrud.findById(id).isPresent())
            throw new PropostaException("Not Found");

        return Proposta.to(propostaCrud.findById(id).orElseThrow(() -> new PropostaException("Cannot find Proposta")));
    }
}
