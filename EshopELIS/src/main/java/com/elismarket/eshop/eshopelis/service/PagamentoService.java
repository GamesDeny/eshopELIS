package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;

import java.util.List;

public interface PagamentoService {

    PagamentoDTO addPagamento(PagamentoDTO pagamentoDTO);

    Boolean removePagamento(Long id);

    Iterable<PagamentoDTO> getAll();

    List<PagamentoDTO> getByContanti();

    List<PagamentoDTO> getByPaypalMail();

    PagamentoDTO getById(Long id);

}
