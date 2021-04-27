package com.elismarket.eshop.model.interfaces;

public interface Prodotto {
    Long getId();

    void setId(Long id);

    Float getPrezzo();

    void setPrezzo(Float prezzo);

    Integer getMinOrd();

    void setMinOrd(Integer minOrd);

    Integer getMaxOrd();

    void setMaxOrd(Integer maxOrd);

    Integer getSconto();

    void setSconto(Integer sconto);

    Integer getQuantita();

    void setQuantita(Integer quantita);

    String getImage();

    void setImage(String Image);

    String getNome();

    void setNome(String nome);

    String getDescrizione();

    void setDescrizione(String descrizione);

    String getNomeCategoria();

    void setNomeCategoria(String nomeCategoria);
}
