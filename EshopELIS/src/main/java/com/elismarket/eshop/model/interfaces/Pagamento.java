package com.elismarket.eshop.model.interfaces;

public interface Pagamento {
    void setId(Long id);

    Long getId();

    void setDescrizione(String descrizione);

    String getDescrizione();

    void setPaypalMail(String paypalMail);

    String getPaypalMail();

    void setTipo(String tipo);

    String getTipo();

    void setContanti(Integer contanti);

    Integer getContanti();
}
