package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.model.Ordine;

import java.util.List;

public interface PagamentoService {

    PagamentoDTO addPagamento(PagamentoDTO pagamentoDTO);

    PagamentoDTO updatePagamento(Long id, PagamentoDTO pagamentoDTO);

    Boolean removePagamento(Long id);

    Iterable<PagamentoDTO> getAll();

    List<PagamentoDTO> getByContanti();

    List<PagamentoDTO> getByPaypalMail();

    PagamentoDTO getById(Long id);

    Ordine addOrdineToPagamento(Long pagamentoId, OrdineDTO pagamentoDTO);
}
