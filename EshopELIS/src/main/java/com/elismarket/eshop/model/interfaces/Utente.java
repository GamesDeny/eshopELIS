package com.elismarket.eshop.model.interfaces;

import java.time.LocalDate;

public interface Utente {
    Long getId();

    void setId(Long id);

    String getMail();

    void setMail(String mail);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getNome();

    void setNome(String nome);

    String getCognome();

    void setCognome(String cognome);

    Integer getSiglaResidenza();

    void setSiglaResidenza(Integer siglaResidenza);

    LocalDate getDataNascita();

    void setDataNascita(LocalDate dataNascita);

    Boolean getIsAdmin();

    void setIsAdmin(Boolean isAdmin);

    Boolean getLogged();

    void setLogged(Boolean logged);
}
