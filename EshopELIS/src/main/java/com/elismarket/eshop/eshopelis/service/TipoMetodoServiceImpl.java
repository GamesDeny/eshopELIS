package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.exception.TipoMetodoException;
import com.elismarket.eshop.eshopelis.helper.PagamentoHelper;
import com.elismarket.eshop.eshopelis.model.TipoMetodo;
import com.elismarket.eshop.eshopelis.repository.TipoMetodoCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.TipoMetodoService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;

/**
 * {@link TipoMetodo TipoMetodo} service class for interaction between DB and relative Controller
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Service
public class TipoMetodoServiceImpl implements TipoMetodoService {

    /**
     * @see TipoMetodoCrud
     */
    @Autowired
    TipoMetodoCrud tipoMetodoCrud;

    /**
     * @see PagamentoHelper
     */
    @Autowired
    PagamentoHelper pagamentoHelper;

    /**
     * Adds a new TipoMetodo
     *
     * @param tipoMetodoDTO {@link TipoMetodoDTO TipoMetodoDTO}
     * @return added TipoMetodo
     */
    @Override
    public TipoMetodoDTO addTipoMetodo(TipoMetodoDTO tipoMetodoDTO) {
        Checkers.tipoMetodoFieldsChecker(tipoMetodoDTO);
        return TipoMetodo.to(tipoMetodoCrud.saveAndFlush(TipoMetodo.of(tipoMetodoDTO)));
    }

    /**
     * Updates TipoMetodo related to the id with updated fields in TipoMetodoDTO
     *
     * @param tipoMetodoId  of the {@link TipoMetodo TipoMetodo}
     * @param tipoMetodoDTO {@link TipoMetodoDTO TipoMetodoDTO} with updated fields
     * @return updated TipoMetodo
     * @throws TipoMetodoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws TipoMetodoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public TipoMetodoDTO updateTipoMetodo(Long tipoMetodoId, TipoMetodoDTO tipoMetodoDTO) {
        if (Objects.isNull(tipoMetodoId))
            throw new TipoMetodoException(MISSING_PARAMETERS.name());

        if (!tipoMetodoCrud.existsById(tipoMetodoId))
            throw new TipoMetodoException(CANNOT_FIND_ELEMENT.name());

        Checkers.tipoMetodoFieldsChecker(tipoMetodoDTO);

        TipoMetodo c = tipoMetodoCrud.findById(tipoMetodoId).orElseThrow(() -> new TipoMetodoException(CANNOT_FIND_ELEMENT.name()));

        tipoMetodoDTO.id = tipoMetodoId;
        tipoMetodoDTO.nome = Objects.isNull(tipoMetodoDTO.nome) ? c.getNome() : tipoMetodoDTO.nome;

        Checkers.tipoMetodoFieldsChecker(tipoMetodoDTO);

        return TipoMetodo.to(tipoMetodoCrud.saveAndFlush(TipoMetodo.of(tipoMetodoDTO)));
    }

    /**
     * Removes the TipoMetodo related to the id
     *
     * @param id of the {@link TipoMetodo TipoMetodo}
     * @return HTTP 200 if deleted successfully, else 500
     * @throws TipoMetodoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws TipoMetodoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public Boolean deleteTipoMetodo(Long id) {
        if (Objects.isNull(id))
            throw new TipoMetodoException(MISSING_PARAMETERS.name());

        if (!tipoMetodoCrud.existsById(id))
            throw new TipoMetodoException(CANNOT_FIND_ELEMENT.name());

        tipoMetodoCrud.deleteById(id);
        return tipoMetodoCrud.existsById(id);
    }

    /**
     * Retrieves all TipoMetodo in DB
     *
     * @return List {@link TipoMetodoDTO TipoMetodoDTO}
     */
    @Override
    public List<TipoMetodoDTO> getAll() {
        List<TipoMetodoDTO> result = new ArrayList<>();
        tipoMetodoCrud.findAll().forEach(tipoMetodo -> result.add(TipoMetodo.to(tipoMetodo)));
        return result;
    }

    /**
     * Retrieves the TipoMetodo related to the id
     *
     * @param id of the {@link TipoMetodo TipoMetodo}
     * @return retrieved TipoMetodo
     * @throws TipoMetodoException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws TipoMetodoException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public TipoMetodoDTO getById(Long id) {
        if (Objects.isNull(id))
            throw new TipoMetodoException(MISSING_PARAMETERS.name());

        return TipoMetodo.to(tipoMetodoCrud.findById(id).orElseThrow(() -> new TipoMetodoException(CANNOT_FIND_ELEMENT.name())));
    }

}
