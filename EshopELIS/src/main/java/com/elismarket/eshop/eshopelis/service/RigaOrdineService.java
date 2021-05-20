package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;

import java.util.List;

public interface RigaOrdineService {

    List<RigaOrdineDTO> getAll();

    RigaOrdineDTO getById(Long id);

    List<RigaOrdineDTO> getByQuantita(Integer quantita);

    Boolean addRigaOrdine(RigaOrdineDTO rigaOrdineDTO);

    Boolean removeRigaOrdine(Long id);

}
