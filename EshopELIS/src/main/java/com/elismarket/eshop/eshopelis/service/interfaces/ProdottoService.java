package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;

import java.util.List;

public interface ProdottoService {
    ProdottoDTO addProdotto(ProdottoDTO prodottoDTO);

    ProdottoDTO updateProdotto(Long id, ProdottoDTO prodottoDTO);

    Boolean removeProdotto(Long id);

    List<ProdottoDTO> getAll();

    List<ProdottoDTO> findByQuantitaMinore(Integer quantita);

    ProdottoDTO getById(Long id);

    RigaOrdine addRigaOrdineToProdotto(Long prodId, RigaOrdineDTO rigaOrdineDTO);

    List<ProdottoDTO> getProdottoByCategoria(Long categoriaId);

    List<ProdottoDTO> findAllByUtente(Long userId);
}
