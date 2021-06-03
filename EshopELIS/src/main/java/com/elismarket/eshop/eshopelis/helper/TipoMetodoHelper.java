package com.elismarket.eshop.eshopelis.helper;

import com.elismarket.eshop.eshopelis.exception.TipoMetodoException;
import com.elismarket.eshop.eshopelis.model.TipoMetodo;
import com.elismarket.eshop.eshopelis.repository.TipoMetodoCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.CANNOT_FIND_ELEMENT;

/**
 * Helper class for {@link TipoMetodo TipoMetodo} entity
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Component
public class TipoMetodoHelper {

    /**
     * @see TipoMetodoCrud
     */
    @Autowired
    TipoMetodoCrud tipoMetodoCrud;

    /**
     * method to search an user from the repository
     *
     * @param tipoPagamentoId id of the user that needs to be searched
     * @return Utente found in the repository
     * @throws TipoMetodoException with {@link com.elismarket.eshop.eshopelis.exception.ExceptionPhrases#CANNOT_FIND_ELEMENT CANNOT_FIND_ELEMENT} phrase
     */
    public TipoMetodo findById(Long tipoPagamentoId) {
        return tipoMetodoCrud.findById(tipoPagamentoId).orElseThrow(() -> new TipoMetodoException(CANNOT_FIND_ELEMENT.name()));
    }
}
