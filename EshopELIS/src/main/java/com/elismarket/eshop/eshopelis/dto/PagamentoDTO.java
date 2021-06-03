package com.elismarket.eshop.eshopelis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO for {@link com.elismarket.eshop.eshopelis.model.Pagamento Pagamento} class
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class PagamentoDTO {
    public Long id;
    public String descrizione, paypalMail;
    public Float contanti;
    public Boolean isDefault;

    public Long utente_id, tipoMetodo_id, ordine_id;
}
