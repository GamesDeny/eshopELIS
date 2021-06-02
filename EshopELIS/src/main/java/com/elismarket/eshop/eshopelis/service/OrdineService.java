package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;

import java.time.LocalDate;
import java.util.List;

public interface OrdineService {

    OrdineDTO saveOrdine(Long userId, Long pagamentoId, List<RigaOrdineDTO> righe);

    OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO);

    Boolean removeOrdine(Long id);

    List<OrdineDTO> getAll();

    List<OrdineDTO> getEvaso(Boolean evaso);

    List<OrdineDTO> getDataPrima(LocalDate dataEvasione);

    List<OrdineDTO> getDataTra(LocalDate dataEvasione1, LocalDate dataEvasione2);

    List<OrdineDTO> getDataDopo(LocalDate dataEvasione);

    OrdineDTO getById(Long id);

    RigaOrdine addRigaOrdineToOrdine(Long ordineId, RigaOrdineDTO rigaOrdineDTO);
}
