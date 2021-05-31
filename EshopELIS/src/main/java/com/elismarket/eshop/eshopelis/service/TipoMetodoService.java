package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;

import java.util.List;

public interface TipoMetodoService {
    TipoMetodoDTO addTipoMetodo(TipoMetodoDTO tipoMetodoDTO);

    TipoMetodoDTO updateTipoMetodo(Long id, TipoMetodoDTO tipoMetodoDTO);

    Boolean deleteTipoMetodo(Long id);

    List<TipoMetodoDTO> getAll();

    TipoMetodoDTO getById(Long id);
}
