package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;

import java.util.List;

public interface ProdottoService {
    ProdottoDTO addProdotto(ProdottoDTO prodottoDTO);

    ProdottoDTO updateProdotto(Long id, ProdottoDTO prodottoDTO);

    Boolean removeProdotto(Long id);

    List<ProdottoDTO> getAll();

    List<ProdottoDTO> findAllByCategoria(CategoriaDTO categoriaDTO);

    List<ProdottoDTO> findByQuantitaMaggiore(Integer quantita);

    List<ProdottoDTO> findByQuantitaMinore(Integer quantita);

    ProdottoDTO getById(Long id);

    RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO);

    List<ProdottoDTO> getProdottoByCategoria(Long categoriaId);
}
