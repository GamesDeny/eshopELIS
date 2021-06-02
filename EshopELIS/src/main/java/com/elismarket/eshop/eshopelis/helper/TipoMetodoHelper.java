package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.exception.TipoMetodoException;
import com.elismarket.eshop.eshopelis.model.TipoMetodo;
import com.elismarket.eshop.eshopelis.repository.TipoMetodoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;

@Component
public class TipoMetodoHelper {

    @Autowired
    TipoMetodoCrud tipoMetodoCrud;

    public TipoMetodo findById(Long tipoPagamentoId) {
        return tipoMetodoCrud.findById(tipoPagamentoId).orElseThrow(() -> new TipoMetodoException(CANNOT_FIND_ELEMENT.name()));
    }
}
