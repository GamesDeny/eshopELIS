package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.CategoriaService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;
import static java.util.Objects.isNull;

/**
 * {@link Categoria Categoria} service class for interaction between DB and relative Controller
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    /**
     * @see CategoriaCrud
     */
    @Autowired
    CategoriaCrud categoriaCrud;

    /**
     * @see ProdottoHelper
     */
    @Autowired
    ProdottoHelper prodottoHelper;

    /**
     * Adds a Categoria to the DB
     *
     * @param categoriaDTO {@link CategoriaDTO CategoriaDTO} with required fields
     * @return the {@link Categoria Categoria} created
     * @see Checkers#categoriaFieldsChecker(CategoriaDTO)
     */
    @Override
    @Transactional
    public CategoriaDTO addCategoria(CategoriaDTO categoriaDTO) {
        Checkers.categoriaFieldsChecker(categoriaDTO);
        return Categoria.to(categoriaCrud.saveAndFlush(Categoria.of(categoriaDTO)));
    }

    /**
     * Updates with HTTP Patch for a Categoria
     *
     * @param categoriaId  of the {@link Categoria Categoria} to Update
     * @param categoriaDTO {@link CategoriaDTO CategoriaDTO} with the informations to update
     * @return Updated CategoriaDTO
     * @throws CategoriaException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws CategoriaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     * @see Checkers#categoriaFieldsChecker(CategoriaDTO)
     */
    @Override
    @Transactional
    public CategoriaDTO updateCategoria(Long categoriaId, CategoriaDTO categoriaDTO) {
        if (isNull(categoriaId) || isNull(categoriaDTO))
            throw new CategoriaException(MISSING_PARAMETERS.name());

        if (!categoriaCrud.existsById(categoriaId))
            throw new CategoriaException(CANNOT_FIND_ELEMENT.name());

        Categoria c = categoriaCrud.findById(categoriaId).orElseThrow(() -> new CategoriaException(CANNOT_FIND_ELEMENT.name()));

        c.setNome(isNull(categoriaDTO.nome) ? c.getNome() : categoriaDTO.nome);

        Checkers.categoriaFieldsChecker(Categoria.to(c));

        return Categoria.to(categoriaCrud.saveAndFlush(c));
    }

    /**
     * Delete of a {@link Categoria Categoria}
     *
     * @param id of the {@link Categoria Categoria} to delete
     * @return HTTP 200 if deleted successfully, else 500
     * @throws CategoriaException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws CategoriaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    @Transactional
    public Boolean deleteCategoria(Long id) {
        if (isNull(id))
            throw new CategoriaException(MISSING_PARAMETERS.name());

        if (!categoriaCrud.existsById(id))
            throw new CategoriaException(CANNOT_FIND_ELEMENT.name());

        categoriaCrud.deleteById(id);
        return categoriaCrud.existsById(id);
    }

    /**
     * returns all Categoria in the DB
     *
     * @return List {@link Categoria Categoria}
     */
    @Override
    public List<CategoriaDTO> getAll() {

        List<CategoriaDTO> result = new ArrayList<>();
        categoriaCrud.findAll().forEach(categoria -> result.add(Categoria.to(categoria)));
        return result;
    }

    /**
     * Retrieves a Categoria for the provided item if exists
     *
     * @param id of the {@link Categoria Categoria} to retrieve
     * @return {@link Categoria Categoria} for provided id
     * @throws CategoriaException with {@link ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} message
     * @throws CategoriaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public CategoriaDTO getById(Long id) {
        if (isNull(id))
            throw new CategoriaException(MISSING_PARAMETERS.name());
        return Categoria.to(categoriaCrud.findById(id).orElseThrow(() -> new CategoriaException(CANNOT_FIND_ELEMENT.name())));
    }

    /**
     * Retrieves a Categoria for the provided name if exists
     *
     * @param name of the {@link Categoria Categoria} to retrieve
     * @return {@link Categoria Categoria} for provided id
     * @throws CategoriaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    @Override
    public CategoriaDTO getByName(String name) {
        if (isNull(name) || Strings.isBlank(name) || Strings.isEmpty(name))
            throw new CategoriaException(MISSING_PARAMETERS.name());

        return Categoria.to(categoriaCrud.findByNome(name));
    }
}
