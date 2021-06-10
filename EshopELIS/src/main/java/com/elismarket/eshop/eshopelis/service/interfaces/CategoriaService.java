package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.service.CategoriaServiceImpl;

import java.util.List;

/**
 * Service layer for the {@link Categoria Categoria} entity
 * For implementation of methods see {@link CategoriaServiceImpl CategoriaServiceImpl}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface CategoriaService {
    CategoriaDTO addCategoria(CategoriaDTO categoriaDTO);

    CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO);

    Boolean deleteCategoria(Long id);

     List<CategoriaDTO> getAll();

    CategoriaDTO getById(Long id);
}
