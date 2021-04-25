package com.elismarket.eshop.model.interfaces;

import java.time.LocalDate;

public interface Utente {
    void setId(Long id);

    Long getId();

    void setMail(String mail);

    String getMail();

    void setUsername(String username);

    String getUsername();

    void setPassword(String password);

    String getPassword();

    void setNome(String nome);

    String getNome();

    void setCognome(String cognome);

    String getCognome();

    void setSiglaResidenza(Integer siglaResidenza);

    Integer getSiglaResidenza();

    void setDataNascita(LocalDate dataNascita);

    LocalDate getDataNascita();

    void setIsAdmin(Boolean isAdmin);

    Boolean getIsAdmin();

    void setLogged(Boolean logged);

    Boolean getLogged();
}
