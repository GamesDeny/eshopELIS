package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.exception.PropostaException;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
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

    public PropostaDTO addProposta(PropostaDTO propostaDTO) {
        try {
            propostaCrud.save(Proposta.of(propostaDTO));
        } catch (Exception e) {
            throw new PropostaException("Cannot find Proposta for provided item");
        }
        return propostaCrud.findById(propostaDTO.id).isPresent() ? Proposta.to(propostaCrud.findById(propostaDTO.id).get()) : null;
    }

    @Override
    public PropostaDTO updateProposta(Long id, PropostaDTO propostaDTO) {
        if (!propostaCrud.existsById(id))
            throw new PropostaException("Not Found");

        Proposta p = propostaCrud.findById(id).get();

        propostaDTO.id = id;
        propostaDTO.prezzoProposto = Objects.isNull(propostaDTO.prezzoProposto) ? p.getPrezzoProposto() : propostaDTO.prezzoProposto;
        propostaDTO.nome = Objects.isNull(propostaDTO.nome) ? p.getNome() : propostaDTO.nome;
        propostaDTO.descrizione = Objects.isNull(propostaDTO.descrizione) ? p.getDescrizione() : propostaDTO.descrizione;
        propostaDTO.isAccettato = Objects.isNull(propostaDTO.isAccettato) ? p.getIsAccettato() : propostaDTO.isAccettato;
        propostaDTO.motivoRifiuto = Objects.isNull(propostaDTO.motivoRifiuto) ? p.getMotivoRifiuto() : propostaDTO.motivoRifiuto;
        propostaDTO.quantita = Objects.isNull(propostaDTO.quantita) ? p.getQuantita() : propostaDTO.quantita;
        propostaDTO.submissionDate = Objects.isNull(propostaDTO.submissionDate) ? p.getSubmissionDate() : propostaDTO.submissionDate;

        return Proposta.to(propostaCrud.findById(id).get());
    }

    public Boolean removeProposta(Long id) {
        try {
            propostaCrud.deleteById(id);
        } catch (Exception e) {
            throw new PropostaException("Cannot find Proposta for provided item");
        }
        return propostaCrud.findById(id).isPresent();
    }

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

    public PropostaDTO getById(Long id) {
        if (!propostaCrud.findById(id).isPresent())
            throw new PropostaException("Not Found");
        return Proposta.to(propostaCrud.findById(id).get());
    }
}
