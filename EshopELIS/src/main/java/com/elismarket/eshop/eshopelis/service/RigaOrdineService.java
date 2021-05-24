package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;

import java.util.List;

public interface RigaOrdineService {
    RigaOrdineDTO addRigaOrdine(RigaOrdineDTO rigaOrdineDTO);

    Boolean removeRigaOrdine(Long id);

    List<RigaOrdineDTO> getAll();

    RigaOrdineDTO getById(Long id);

    List<RigaOrdineDTO> getByQuantita(Integer quantita);

}
