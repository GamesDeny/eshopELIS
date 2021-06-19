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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;
import static java.util.Objects.isNull;

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
    @Transactional
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
    @Transactional
    public TipoMetodoDTO updateTipoMetodo(Long tipoMetodoId, TipoMetodoDTO tipoMetodoDTO) {
        if (isNull(tipoMetodoId) || isNull(tipoMetodoDTO))
            throw new TipoMetodoException(MISSING_PARAMETERS.name());

        if (!tipoMetodoCrud.existsById(tipoMetodoId))
            throw new TipoMetodoException(CANNOT_FIND_ELEMENT.name());

        TipoMetodo t = tipoMetodoCrud.findById(tipoMetodoId).orElseThrow(() -> new TipoMetodoException(CANNOT_FIND_ELEMENT.name()));

        t.setNome(isNull(tipoMetodoDTO.nome) ? t.getNome() : tipoMetodoDTO.nome);

        Checkers.tipoMetodoFieldsChecker(TipoMetodo.to(t));

//        pagamentoHelper.linkMetodoToPagamento(tipoMetodoId, tipoMetodoDTO.pagamenti_id);

        return TipoMetodo.to(tipoMetodoCrud.saveAndFlush(t));
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
    @Transactional
    public Boolean deleteTipoMetodo(Long id) {
        if (isNull(id))
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
        if (isNull(id))
            throw new TipoMetodoException(MISSING_PARAMETERS.name());

        return TipoMetodo.to(tipoMetodoCrud.findById(id).orElseThrow(() -> new TipoMetodoException(CANNOT_FIND_ELEMENT.name())));
    }

}
