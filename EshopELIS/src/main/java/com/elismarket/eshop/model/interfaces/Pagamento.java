package com.elismarket.eshop.model.interfaces;

public interface Pagamento {
    Long getId();

    void setId(Long id);

    String getDescrizione();

    void setDescrizione(String descrizione);

    String getPaypalMail();

    void setPaypalMail(String paypalMail);

    String getTipo();

    void setTipo(String tipo);

    Integer getContanti();

    void setContanti(Integer contanti);
}
