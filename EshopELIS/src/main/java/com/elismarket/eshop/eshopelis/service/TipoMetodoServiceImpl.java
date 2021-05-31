package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import com.elismarket.eshop.eshopelis.exception.TipoMetodoException;
import com.elismarket.eshop.eshopelis.helper.PagamentoHelper;
import com.elismarket.eshop.eshopelis.model.TipoMetodo;
import com.elismarket.eshop.eshopelis.repository.TipoMetodoCrud;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TipoMetodoServiceImpl implements TipoMetodoService {

    @Autowired
    TipoMetodoCrud tipoMetodoCrud;

    @Autowired
    PagamentoHelper pagamentoHelper;

    @Override
    public TipoMetodoDTO addTipoMetodo(TipoMetodoDTO tipoMetodoDTO) {
        Checkers.tipoMetodoFieldsChecker(tipoMetodoDTO);
        return TipoMetodo.to(tipoMetodoCrud.saveAndFlush(TipoMetodo.of(tipoMetodoDTO)));
    }

    @Override
    public TipoMetodoDTO updateTipoMetodo(Long tipoMetodoId, TipoMetodoDTO tipoMetodoDTO) {
        if (!tipoMetodoCrud.existsById(tipoMetodoId))
            throw new TipoMetodoException("Not found");

        TipoMetodo c = tipoMetodoCrud.findById(tipoMetodoId).orElseThrow(() -> new TipoMetodoException("Cannot find TipoMetodo"));

        tipoMetodoDTO.id = tipoMetodoId;
        tipoMetodoDTO.nome = Objects.isNull(tipoMetodoDTO.nome) ? c.getNome() : tipoMetodoDTO.nome;

        Checkers.tipoMetodoFieldsChecker(tipoMetodoDTO);
        pagamentoHelper.linkMetodoToPagamento(tipoMetodoId, tipoMetodoDTO.pagamenti_id);
        TipoMetodo save = TipoMetodo.of(tipoMetodoDTO);

        tipoMetodoCrud.saveAndFlush(save);

        return TipoMetodo.to(tipoMetodoCrud.findById(tipoMetodoId).orElseThrow(() -> new TipoMetodoException("Cannot find item")));
    }

    @Override
    public Boolean deleteTipoMetodo(Long id) {
        if (!tipoMetodoCrud.existsById(id))
            throw new TipoMetodoException("Not found");

        tipoMetodoCrud.deleteById(id);
        return !tipoMetodoCrud.existsById(id);
    }

    @Override
    public List<TipoMetodoDTO> getAll() {
        if (tipoMetodoCrud.findAll().isEmpty())
            throw new TipoMetodoException("List is empty");

        List<TipoMetodoDTO> result = new ArrayList<>();
        tipoMetodoCrud.findAll().forEach(tipoMetodo -> result.add(TipoMetodo.to(tipoMetodo)));
        return result;
    }

    @Override
    public TipoMetodoDTO getById(Long id) {
        return TipoMetodo.to(tipoMetodoCrud.findById(id).orElseThrow(() -> new TipoMetodoException("Cannot find TipoMetodo")));
    }

}
