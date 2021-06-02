package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.exception.PropostaException;
import com.elismarket.eshop.eshopelis.helper.CategoriaHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.PropostaService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.*;

@Service
public class PropostaServiceImpl implements PropostaService {

    @Autowired
    PropostaCrud propostaCrud;

    @Autowired
    UtenteHelper utenteHelper;

    @Autowired
    ProdottoHelper prodottoHelper;

    @Autowired
    CategoriaHelper categoriaHelper;

    @Override
    public PropostaDTO addProposta(Long userId, PropostaDTO propostaDTO) {
        Checkers.propostaFieldsChecker(propostaDTO);
        Proposta p = Proposta.of(propostaDTO);
        p.setUtente(utenteHelper.findById(userId));
        p.setCategoria(Categoria.of(categoriaHelper.findById(propostaDTO.categoria_id)));
        p.setSubmissionDate(LocalDate.now());

        return Proposta.to(propostaCrud.saveAndFlush(Proposta.of(propostaDTO)));
    }

    /**
     * @param id          of the user
     * @param propostaDTO DTO of the Proposta to add
     * @return proposta added and linked with user
     */
    @Override
    public PropostaDTO updateProposta(Long id, PropostaDTO propostaDTO) {
        if (!propostaCrud.existsById(id))
            throw new PropostaException(CANNOT_FIND_ELEMENT.name());

        Proposta p = propostaCrud.findById(id).orElseThrow(() -> new PropostaException(CANNOT_FIND_ELEMENT.name()));

        propostaDTO.id = id;
        propostaDTO.prezzoProposto = Objects.isNull(propostaDTO.prezzoProposto) ? p.getPrezzoProposto() : propostaDTO.prezzoProposto;
        propostaDTO.nome = Objects.isNull(propostaDTO.nome) ? p.getNome() : propostaDTO.nome;
        propostaDTO.descrizione = Objects.isNull(propostaDTO.descrizione) ? p.getDescrizione() : propostaDTO.descrizione;
        propostaDTO.isAccettato = Objects.isNull(propostaDTO.isAccettato) ? p.getIsAccettato() : propostaDTO.isAccettato;
        propostaDTO.motivoRifiuto = Objects.isNull(propostaDTO.motivoRifiuto) ? p.getMotivoRifiuto() : propostaDTO.motivoRifiuto;
        propostaDTO.quantita = Objects.isNull(propostaDTO.quantita) ? p.getQuantita() : propostaDTO.quantita;
        propostaDTO.submissionDate = Objects.isNull(propostaDTO.submissionDate) ? p.getSubmissionDate() : propostaDTO.submissionDate;

        Checkers.propostaFieldsChecker(propostaDTO);

        Proposta save = Proposta.of(propostaDTO);
        save.setUtente(Objects.isNull(propostaDTO.utente_id) ? p.getUtente() : utenteHelper.findById(propostaDTO.utente_id));
        propostaCrud.saveAndFlush(save);

        if (propostaDTO.isAccettato)
            prodottoHelper.addProdotto(propostaDTO);

        return Proposta.to(propostaCrud.findById(id).orElseThrow(() -> new PropostaException(CANNOT_FIND_ELEMENT.name())));
    }

    @Override
    public Boolean removeProposta(Long id) {
        if (!propostaCrud.existsById(id))
            throw new PropostaException(CANNOT_FIND_ELEMENT.name());

        propostaCrud.deleteById(id);
        return !propostaCrud.existsById(id);
    }

    @Override
    public List<PropostaDTO> findAllByIsAccettato(Boolean isAccettato) {
        if (propostaCrud.findAllByIsAccettato(isAccettato).isEmpty())
            throw new PropostaException(LIST_IS_EMPTY.name());

        List<PropostaDTO> result = new ArrayList<>();
        propostaCrud.findAllByIsAccettato(isAccettato).forEach(proposta -> result.add(Proposta.to(proposta)));
        return result;
    }

    @Override
    public List<PropostaDTO> findAllByUtente(Long id) {
        if (Objects.isNull(id))
            throw new PropostaException(MISSING_PARAMETERS.name());

        if (propostaCrud.findAllByUtente(id).isEmpty())
            throw new PropostaException(LIST_IS_EMPTY.name());

        List<PropostaDTO> result = new ArrayList<>();
        propostaCrud.findAllByUtente(id).forEach(proposta -> result.add(Proposta.to(proposta)));
        return result;
    }

    @Override
    public List<PropostaDTO> findAll() {

        if (propostaCrud.findAll().isEmpty())
            throw new PropostaException(LIST_IS_EMPTY.name());

        List<PropostaDTO> result = new ArrayList<>();
        propostaCrud.findAll().forEach(proposta -> result.add(Proposta.to(proposta)));
        return result;
    }

    @Override
    public PropostaDTO findById(Long id) {
        if (!propostaCrud.findById(id).isPresent())
            throw new PropostaException(CANNOT_FIND_ELEMENT.name());

        return Proposta.to(propostaCrud.findById(id).orElseThrow(() -> new PropostaException(CANNOT_FIND_ELEMENT.name())));
    }
}
