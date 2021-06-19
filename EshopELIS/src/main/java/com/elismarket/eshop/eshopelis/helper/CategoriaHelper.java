package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.exception.ExceptionPhrases;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;
import static java.util.Objects.isNull;

/**
 * Helper class for {@link Categoria Categoria} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Component
public class CategoriaHelper {
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
     * retrieves the Categoria for the provided id
     *
     * @param categoriaId id of the {@link Categoria Categoria} to retrieve
     * @return Categoria retrieved
     * @throws CategoriaException with {@link ExceptionPhrases#MISSING_PARAMETERS MISSING_PARAMETERS} message
     */
    public Categoria findById(Long categoriaId) {
        if (isNull(categoriaId))
            throw new CategoriaException(MISSING_PARAMETERS.name());

        return categoriaCrud.findById(categoriaId).orElseThrow(() -> new CategoriaException(CANNOT_FIND_ELEMENT.name()));
    }

}
