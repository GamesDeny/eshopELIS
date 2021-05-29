package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;

import java.util.List;

public interface ProdottoService {
    ProdottoDTO saveProdotto(ProdottoDTO prodottoDTO);

    ProdottoDTO updateProdotto(Long id, ProdottoDTO prodottoDTO);

    Boolean removeProdotto(Long id);

    List<ProdottoDTO> getAll();

    List<ProdottoDTO> findAllByCategoria(String categoria);

    List<ProdottoDTO> findByQuantitaMaggiore(Integer quantita);

    List<ProdottoDTO> findByQuantitaMinore(Integer quantita);

    List<ProdottoDTO> getProdottoByCategoria(String categoria);

    List<String> getAllCategoria();

    ProdottoDTO getById(Long id);

    RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO);
}
