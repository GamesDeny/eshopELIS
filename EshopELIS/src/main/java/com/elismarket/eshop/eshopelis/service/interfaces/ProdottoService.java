package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.model.Prodotto;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.service.ProdottoServiceImpl;

import java.util.List;

/**
 * Service layer for the {@link Prodotto Prodotto} entity
 * For implementation of methods see {@link ProdottoServiceImpl ProdottoServiceImpl}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
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
