package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.CategoriaService;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;

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
    public CategoriaDTO addCategoria(CategoriaDTO categoriaDTO) {
        Checkers.categoriaFieldsChecker(categoriaDTO);
        Categoria categoria = categoriaCrud.saveAndFlush(Categoria.of(categoriaDTO));
        return Categoria.to(categoria);
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
    public CategoriaDTO updateCategoria(Long categoriaId, CategoriaDTO categoriaDTO) {
        if (Objects.isNull(categoriaId) || Objects.isNull(categoriaDTO))
            throw new CategoriaException(MISSING_PARAMETERS.name());

        if (!categoriaCrud.existsById(categoriaId))
            throw new CategoriaException(CANNOT_FIND_ELEMENT.name());

        Categoria c = categoriaCrud.findById(categoriaId).orElseThrow(() -> new CategoriaException(CANNOT_FIND_ELEMENT.name()));

        categoriaDTO.id = categoriaId;
        categoriaDTO.nome = Objects.isNull(categoriaDTO.nome) ? c.getNome() : categoriaDTO.nome;

        Checkers.categoriaFieldsChecker(categoriaDTO);
        prodottoHelper.linkCategoriaToProdotto(categoriaId, categoriaDTO.prodotti);
        Categoria save = Categoria.of(categoriaDTO);

        categoriaCrud.saveAndFlush(save);

        return Categoria.to(categoriaCrud.findById(categoriaId).orElseThrow(() -> new CategoriaException(CANNOT_FIND_ELEMENT.name())));
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
    public Boolean deleteCategoria(Long id) {
        if (Objects.isNull(id))
            throw new CategoriaException(MISSING_PARAMETERS.name());

        if (!categoriaCrud.existsById(id))
            throw new CategoriaException(CANNOT_FIND_ELEMENT.name());

        categoriaCrud.deleteById(id);
        return !categoriaCrud.existsById(id);
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
        if (Objects.isNull(id))
            throw new CategoriaException(MISSING_PARAMETERS.name());
        return Categoria.to(categoriaCrud.findById(id).orElseThrow(() -> new CategoriaException(CANNOT_FIND_ELEMENT.name())));
    }
}
