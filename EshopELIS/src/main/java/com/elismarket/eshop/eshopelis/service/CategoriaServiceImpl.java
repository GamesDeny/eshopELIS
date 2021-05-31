package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.helper.ProdottoHelper;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;
import com.elismarket.eshop.eshopelis.utility.Checkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    CategoriaCrud categoriaCrud;

    @Autowired
    ProdottoHelper prodottoHelper;

    @Override
    public CategoriaDTO addCategoria(CategoriaDTO categoriaDTO) {
        Checkers.categoriaFieldsChecker(categoriaDTO);
        return Categoria.to(categoriaCrud.saveAndFlush(Categoria.of(categoriaDTO)));
    }

    @Override
    public CategoriaDTO updateCategoria(Long categoriaId, CategoriaDTO categoriaDTO) {
        if (!categoriaCrud.existsById(categoriaId))
            throw new CategoriaException("Not found");

        Categoria c = categoriaCrud.findById(categoriaId).orElseThrow(() -> new CategoriaException("Cannot find Categoria"));

        categoriaDTO.id = categoriaId;
        categoriaDTO.nome = Objects.isNull(categoriaDTO.nome) ? c.getNome() : categoriaDTO.nome;

        Checkers.categoriaFieldsChecker(categoriaDTO);
        prodottoHelper.linkCategoriaToProdotto(categoriaId, categoriaDTO.prodotti);
        Categoria save = Categoria.of(categoriaDTO);

        categoriaCrud.saveAndFlush(save);

        return Categoria.to(categoriaCrud.findById(categoriaId).orElseThrow(() -> new CategoriaException("Cannot find item")));
    }

    @Override
    public Boolean deleteCategoria(Long id) {
        if (!categoriaCrud.existsById(id))
            throw new CategoriaException("Not found");

        categoriaCrud.deleteById(id);
        return !categoriaCrud.existsById(id);
    }

    @Override
    public List<CategoriaDTO> getAll() {
        if (categoriaCrud.findAll().isEmpty())
            throw new CategoriaException("List is empty");

        List<CategoriaDTO> result = new ArrayList<>();
        categoriaCrud.findAll().forEach(categoria -> result.add(Categoria.to(categoria)));
        return result;
    }

    @Override
    public CategoriaDTO getById(Long id) {
        return Categoria.to(categoriaCrud.findById(id).orElseThrow(() -> new CategoriaException("Cannot find Categoria")));
    }
}
