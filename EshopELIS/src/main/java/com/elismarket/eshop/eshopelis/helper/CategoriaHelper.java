package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoriaHelper {
    @Autowired
    CategoriaCrud categoriaCrud;

    public Categoria findById(Long categoriaId) {
        if (Objects.isNull(categoriaId))
            throw new CategoriaException("Missing parameter");

        return categoriaCrud.findById(categoriaId).orElseThrow(() -> new CategoriaException("Cannot find Categoria"));
    }
}
