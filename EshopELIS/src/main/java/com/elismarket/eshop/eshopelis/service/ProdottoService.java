package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.ProdottoDTO;

import java.util.List;

public interface ProdottoService {
    ProdottoDTO saveProdotto(ProdottoDTO prodotto);

    Boolean removeProdotto(Long id);

    Boolean removeById(Long id);

    List<ProdottoDTO> getAll();

    List<ProdottoDTO> findAllByNome(String nome);

    List<ProdottoDTO> findAllByCategoria(String categoria);

    List<ProdottoDTO> findByQuantitaMaggiore(Integer quantita);

    List<ProdottoDTO> findByQuantitaMinore(Integer quantita);

    List<ProdottoDTO> getProdottoByCategoria(String categoria);

    List<String> getAllCategoria();

    ProdottoDTO getById(Long id);
}
