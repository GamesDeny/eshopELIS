package com.elismarket.eshop.eshopelis;

import com.elismarket.eshop.eshopelis.dto.*;
import com.elismarket.eshop.eshopelis.model.*;
import com.elismarket.eshop.eshopelis.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DBInteractions {
    @Autowired
    static CategoriaService categoriaService;

    @Autowired
    static FeedbackService feedbackService;

    @Autowired
    static OrdineService ordineService;

    @Autowired
    static PagamentoService pagamentoService;

    @Autowired
    static PropostaService propostaService;

    @Autowired
    static ProdottoService prodottoService;

    @Autowired
    static RigaOrdineService rigaOrdineService;

    @Autowired
    static TipoMetodoService tipoMetodoService;

    @Autowired
    static UtenteService utenteService;


    public static CategoriaDTO addCategoria(Categoria categoria) {
        return categoriaService.addCategoria(Categoria.to(categoria));
    }

    public static FeedbackDTO addFeedback(Feedback feedback) {
        return feedbackService.addFeedback(Feedback.to(feedback));
    }

    public static OrdineDTO addOrdine(Long userId, Long pagamentoId, List<RigaOrdineDTO> righe) {
        return ordineService.saveOrdine(userId, pagamentoId, righe);
    }

    public static PagamentoDTO addPagamento(Pagamento pagamento) {
        return pagamentoService.addPagamento(Pagamento.to(pagamento));
    }

    public static ProdottoDTO addProdotto(Prodotto prodotto) {
        return prodottoService.addProdotto(Prodotto.to(prodotto));
    }

    public static PropostaDTO addProposta(Long userId, Proposta proposta) {
        return propostaService.addProposta(userId, Proposta.to(proposta));
    }

    public static RigaOrdineDTO addRigaOrdine(RigaOrdine rigaOrdine) {
        return rigaOrdineService.addRigaOrdine(RigaOrdine.to(rigaOrdine));
    }

    public static TipoMetodoDTO addTipoMetodo(TipoMetodo tipoMetodo) {
        return tipoMetodoService.addTipoMetodo(TipoMetodo.to(tipoMetodo));
    }

    public static UtenteDTO addUtente(Utente utente) {
        return utenteService.addUtente(Utente.to(utente));
    }
}

