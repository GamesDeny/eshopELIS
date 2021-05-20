package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;

import java.util.List;

public interface PagamentoService {
    Iterable<PagamentoDTO> getAll();

    List<PagamentoDTO> getByContanti();

    List<PagamentoDTO> getByPaypalMail();

    Boolean addPagamento(PagamentoDTO pagamentoDTO);

    void removePagamento(Long id);

    PagamentoDTO getById(Long id);

}
