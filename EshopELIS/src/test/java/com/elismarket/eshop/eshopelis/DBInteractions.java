package com.elismarket.eshop.eshopelis;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.model.*;
import com.elismarket.eshop.eshopelis.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBInteractions {
    @Autowired
    CategoriaService categoriaService;

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    OrdineService ordineService;

    @Autowired
    PagamentoService pagamentoService;

    @Autowired
    PropostaService propostaService;

    @Autowired
    ProdottoService prodottoService;

    @Autowired
    RigaOrdineService rigaOrdineService;

    @Autowired
    TipoMetodoService tipoMetodoService;

    @Autowired
    UtenteService utenteService;


    public CategoriaDTO addCategoria(Categoria categoria) {
        return categoriaService.addCategoria(Categoria.to(categoria));
    }

    public FeedbackDTO addFeedback(Feedback feedback) {
        return feedbackService.addFeedback(Feedback.to(feedback));
    }

    public OrdineDTO addOrdine(Long userId, Long pagamentoId, List<RigaOrdineDTO> righe) {
        return ordineService.saveOrdine(userId, pagamentoId, righe);
    }

    public PagamentoDTO addPagamento(Pagamento pagamento) {
        return pagamentoService.addPagamento(Pagamento.to(pagamento));
    }

    public ProdottoDTO addProdotto(Prodotto prodotto) {
        return prodottoService.addProdotto(Prodotto.to(prodotto));
    }

    public PropostaDTO addProposta(Long userId, Proposta proposta) {
        return propostaService.addProposta(userId, Proposta.to(proposta));
    }

    public RigaOrdineDTO addRigaOrdine(RigaOrdine rigaOrdine) {
        return rigaOrdineService.addRigaOrdine(RigaOrdine.to(rigaOrdine));
    }

    public TipoMetodoDTO addTipoMetodo(TipoMetodo tipoMetodo) {
        return tipoMetodoService.addTipoMetodo(TipoMetodo.to(tipoMetodo));
    }

    public UtenteDTO addUtente(Utente utente) {
        return utenteService.addUtente(Utente.to(utente));
    }
}

