package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.service.RigaOrdineServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * Service layer for the {@link RigaOrdine RigaOrdine} entity
 * For implementation of methods see {@link RigaOrdineServiceImpl RigaOrdineServiceImpl}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface RigaOrdineService {
    RigaOrdineDTO addRigaOrdine(RigaOrdineDTO rigaOrdineDTO);

    RigaOrdineDTO updateRigaOrdine(Long id, RigaOrdineDTO rigaOrdineDTO);

    Boolean removeRigaOrdine(Long id);

    List<RigaOrdineDTO> getAll();

    RigaOrdineDTO getById(Long id);

    Map<Long, Integer> getProdottoStatistics();
}
