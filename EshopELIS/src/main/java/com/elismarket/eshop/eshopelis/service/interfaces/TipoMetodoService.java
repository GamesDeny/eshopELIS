package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import com.elismarket.eshop.eshopelis.model.TipoMetodo;
import com.elismarket.eshop.eshopelis.service.TipoMetodoServiceImpl;

import java.util.List;

/**
 * Service layer for the {@link TipoMetodo TipoMetodo} entity
 * For implementation of methods see {@link TipoMetodoServiceImpl TipoMetodoServiceImpl}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface TipoMetodoService {
    TipoMetodoDTO addTipoMetodo(TipoMetodoDTO tipoMetodoDTO);

    TipoMetodoDTO updateTipoMetodo(Long id, TipoMetodoDTO tipoMetodoDTO);

    Boolean deleteTipoMetodo(Long id);

    List<TipoMetodoDTO> getAll();

    TipoMetodoDTO getById(Long id);
}
