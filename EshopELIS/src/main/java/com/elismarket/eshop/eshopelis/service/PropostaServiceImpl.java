package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.PropostaException;
import com.elismarket.eshop.eshopelis.helper.CategoriaHelper;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.model.Utente;
import com.elismarket.eshop.eshopelis.repository.PropostaCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.PropostaService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;
import static java.util.Objects.isNull;

/**
 * {@link Proposta Proposta} service class for interaction between DB and relative Controller
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Service
public class PropostaServiceImpl implements PropostaService {

    /**
     * @see PropostaCrud
     */
    @Autowired
    PropostaCrud propostaCrud;

    /**
     * @see UtenteHelper
     */
    @Autowired
    UtenteHelper utenteHelper;

    /**
     * @see ProdottoHelper
     */
    @Autowired
    ProdottoHelper prodottoHelper;

    /**
     * @see CategoriaHelper
     */
    @Autowired
    CategoriaHelper categoriaHelper;

    /**
     * Adds a new Proposta
     *
     * @param propostaDTO {@link PropostaDTO PropostaDTO} to add
     * @return Added Proposta
     * @throws PropostaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} phrase
     */
    @Override
    @Transactional
    public PropostaDTO addProposta(PropostaDTO propostaDTO) {
        Checkers.propostaFieldsChecker(propostaDTO);
        Proposta p = Proposta.of(propostaDTO);
        p.setUtente(utenteHelper.findById(propostaDTO.utente_id));
        p.setSubmissionDate(LocalDate.now());

        return Proposta.to(propostaCrud.saveAndFlush(p));
    }

    /**
     * Updates the Proposta related to the id with relative PropostaDTO
     *
     * @param id          id of the {@link Proposta Proposta}
     * @param propostaDTO {@link PropostaDTO PropostaDTO} with updated fields
     * @return updated Proposta
     * @throws PropostaException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     */
    @Override
    @Transactional
    public PropostaDTO updateProposta(Long id, PropostaDTO propostaDTO) {
        if (isNull(id) || isNull(propostaDTO))
            throw new PropostaException(MISSING_PARAMETERS.name());

        if (!propostaCrud.existsById(id))
            throw new PropostaException(CANNOT_FIND_ELEMENT.name());

        Proposta p = propostaCrud.findById(id).orElseThrow(() -> new PropostaException(CANNOT_FIND_ELEMENT.name()));

        p.setPrezzoProposto(isNull(propostaDTO.prezzoProposto) ? p.getPrezzoProposto() : propostaDTO.prezzoProposto);
        p.setNome(isNull(propostaDTO.nome) ? p.getNome() : propostaDTO.nome);
        p.setDescrizione(isNull(propostaDTO.descrizione) ? p.getDescrizione() : propostaDTO.descrizione);
        p.setIsAccettato(isNull(propostaDTO.isAccettato) ? p.getIsAccettato() : propostaDTO.isAccettato);
        p.setMotivoRifiuto(isNull(propostaDTO.motivoRifiuto) ? p.getMotivoRifiuto() : propostaDTO.motivoRifiuto);
        p.setQuantita(isNull(propostaDTO.quantita) ? p.getQuantita() : propostaDTO.quantita);
        p.setSubmissionDate(isNull(propostaDTO.submissionDate) ? p.getSubmissionDate() : propostaDTO.submissionDate);

        Checkers.propostaFieldsChecker(Proposta.to(p));

        p.setUtente(isNull(propostaDTO.utente_id) ? p.getUtente() : utenteHelper.findById(propostaDTO.utente_id));

        if (!isNull(propostaDTO.isAccettato) && propostaDTO.isAccettato)
            prodottoHelper.addProdotto(propostaDTO);

        return Proposta.to(propostaCrud.saveAndFlush(p));
    }

    /**
     * Remove the Proposta for the provided id
     *
     * @param id of the {@link Proposta Proposta} to remove
     * @return HTTP 200 if deleted successfully, else 500
     * @throws PropostaException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws PropostaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    @Transactional
    public Boolean removeProposta(Long id) {
        if (isNull(id))
            throw new PropostaException(MISSING_PARAMETERS.name());

        if (!propostaCrud.existsById(id))
            throw new PropostaException(CANNOT_FIND_ELEMENT.name());

        propostaCrud.deleteById(id);
        return propostaCrud.existsById(id);
    }

    /**
     * Retrieves Proposta for the id
     *
     * @param id of the {@link Proposta Proposta}
     * @return {@link PropostaDTO PropostaDTO}
     * @throws PropostaException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws PropostaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public PropostaDTO findById(Long id) {
        if (isNull(id))
            throw new PropostaException(MISSING_PARAMETERS.name());

        if (!propostaCrud.existsById(id))
            throw new PropostaException(CANNOT_FIND_ELEMENT.name());

        return Proposta.to(propostaCrud.findById(id).orElseThrow(() -> new PropostaException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Retrieves all Proposta
     *
     * @return List {@link PropostaDTO PropostaDTO}
     */
    @Override
    public List<PropostaDTO> findAll() {

        List<PropostaDTO> result = new ArrayList<>();
        propostaCrud.findAll().forEach(proposta -> result.add(Proposta.to(proposta)));
        return result;
    }

    /**
     * Retrieves all Proposta following isAccettato rules
     *
     * @return List {@link PropostaDTO PropostaDTO}
     */
    @Override
    public List<PropostaDTO> findAllByIsAccettato(Boolean isAccettato) {
        List<PropostaDTO> result = new ArrayList<>();
        //is not null? is accettato? true, else false else null
        int scelta = !isNull(isAccettato) ? isAccettato ? 1 : 2 : 3;

        switch (scelta) {
            case 1:
                propostaCrud.findAllByIsAccettato(true).forEach(proposta -> result.add(Proposta.to(proposta)));
                break;
            case 2:
                propostaCrud.findAllByIsAccettato(false).forEach(proposta -> result.add(Proposta.to(proposta)));
                break;
            case 3:
                propostaCrud.findAllByIsAccettatoIsNull().forEach(proposta -> result.add(Proposta.to(proposta)));
                break;
        }
        return result;
    }

    /**
     * Retrieves all Proposta for an Utente
     *
     * @param userId id of {@link Utente Utente}
     * @return List {@link PropostaDTO PropostaDTO}
     * @throws PropostaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public List<PropostaDTO> findAllByUtente(Long userId) {
        if (isNull(userId))
            throw new PropostaException(MISSING_PARAMETERS.name());

        List<PropostaDTO> result = new ArrayList<>();
        propostaCrud.findAllByUtente(utenteHelper.findById(userId)).forEach(proposta -> result.add(Proposta.to(proposta)));
        return result;
    }
}
