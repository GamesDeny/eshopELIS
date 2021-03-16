package com.elismarket.eshop.interfaces;

public interface Utente {
    String getUsername();
    String getPassword();
    Boolean getIsAdmin();
    void setLogged();
}
