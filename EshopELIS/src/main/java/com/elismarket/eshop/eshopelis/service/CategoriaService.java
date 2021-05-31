package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {
    CategoriaDTO addCategoria(CategoriaDTO categoriaDTO);

    CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO);

    Boolean deleteCategoria(Long id);

    List<CategoriaDTO> getAll();

    CategoriaDTO getById(Long id);
}
