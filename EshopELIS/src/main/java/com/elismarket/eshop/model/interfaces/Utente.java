package com.elismarket.eshop.model.interfaces;

public interface Utente {
    String getUsername();

    String getPassword();

    Boolean getIsAdmin();

    void setLogged();
}
