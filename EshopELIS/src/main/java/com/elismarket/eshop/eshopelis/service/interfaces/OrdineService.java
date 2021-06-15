package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.service.OrdineServiceImpl;

import java.util.List;

/**
 * Service layer for the {@link Ordine Ordine} entity
 * For implementation of methods see {@link OrdineServiceImpl OrdineServiceImpl}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface OrdineService {

    OrdineDTO saveOrdine(Long userId, Long pagamentoId, List<RigaOrdineDTO> righe);

    OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO);

    Boolean removeOrdine(Long id);

    List<OrdineDTO> getAll();

    List<OrdineDTO> getEvaso(Boolean evaso);

    OrdineDTO getById(Long id);

    RigaOrdine addRigaOrdineToOrdine(Long ordineId, RigaOrdineDTO rigaOrdineDTO);

    List<OrdineDTO> getAllByUtente(Long userId);

    OrdineDTO evadiOrdine(Long id, OrdineDTO ordineDTO);
}
