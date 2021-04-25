package com.elismarket.eshop.model.interfaces;

public interface Prodotto {
    void setId(Long id);

    Long getId();

    void setPrezzo(Float prezzo);

    Float getPrezzo();

    void setMinOrd(Integer minOrd);

    Integer getMinOrd();

    void setMaxOrd(Integer maxOrd);

    Integer getMaxOrd();

    void setSconto(Integer sconto);

    Integer getSconto();

    void setQuantita(Integer quantita);

    Integer getQuantita();

    void setImage(String Image);

    String getImage();

    void setNome(String nome);

    String getNome();

    void setDescrizione(String descrizione);

    String getDescrizione();

    void setNomeCategoria(String nomeCategoria);

    String getNomeCategoria();
}
