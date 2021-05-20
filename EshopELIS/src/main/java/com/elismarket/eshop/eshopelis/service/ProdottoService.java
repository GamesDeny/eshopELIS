package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;

import java.util.List;

public interface ProdottoService {
    void removeById(Long id);

    List<ProdottoDTO> getAll();

    List<ProdottoDTO> findAllByNome(String nome);

    List<ProdottoDTO> findAllByCategoria(String categoria);

    List<ProdottoDTO> findByQuantitaMaggiore(Integer quantita);

    List<ProdottoDTO> findByQuantitaMinore(Integer quantita);

    List<ProdottoDTO> getProdottoByCategoria(String categoria);

    List<String> getAllCategoria();

    Boolean saveProdotto(ProdottoDTO prodotto);

    Boolean updateProdotto(ProdottoDTO prodotto);

    void removeProdotto(Long id);

    ProdottoDTO getById(Long id);
}
