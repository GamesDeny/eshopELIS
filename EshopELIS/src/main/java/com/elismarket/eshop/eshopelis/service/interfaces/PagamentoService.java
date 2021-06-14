package com.elismarket.eshop.eshopelis.service.interfaces;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.PagamentoDTO;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.Pagamento;
import com.elismarket.eshop.eshopelis.service.PagamentoServiceImpl;

import java.util.List;

/**
 * Service layer for the {@link Pagamento Pagamento} entity
 * For implementation of methods see {@link PagamentoServiceImpl PagamentoServiceImpl}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface PagamentoService {

    PagamentoDTO addPagamento(PagamentoDTO pagamentoDTO);

    PagamentoDTO updatePagamento(Long id, PagamentoDTO pagamentoDTO);

    Boolean removePagamento(Long id);

    List<PagamentoDTO> getAll();

    PagamentoDTO getById(Long id);

    Ordine addOrdineToPagamento(Long pagamentoId, OrdineDTO pagamentoDTO);
}
