package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;
import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.MISSING_PARAMETERS;

@Component
public class CategoriaHelper {
    @Autowired
    CategoriaCrud categoriaCrud;

    @Autowired
    ProdottoHelper prodottoHelper;

    public CategoriaDTO findById(Long categoriaId) {
        if (Objects.isNull(categoriaId))
            throw new CategoriaException(MISSING_PARAMETERS.name());

        return Categoria.to(categoriaCrud.findById(categoriaId).orElseThrow(() -> new CategoriaException(CANNOT_FIND_ELEMENT.name())));
    }

}
