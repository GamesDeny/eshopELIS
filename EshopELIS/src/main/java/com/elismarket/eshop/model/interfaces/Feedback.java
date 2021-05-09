package com.elismarket.eshop.model.interfaces;

public interface Feedback {
    Long getId();

    void setId(Long id);

    String getDescrizione();

    void setDescrizione(String descrizione);

    String getOggetto();

    void setOggetto(String oggetto);

    Boolean getIsAccepted();

    void setIsAccepted(Boolean isAccepted);
}
