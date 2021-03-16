package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "utente")
public class UtenteImpl implements Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;


    @Column(unique = true)
    private String mail;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String password;

    private String nome, cognome;

    @Column(name = "sigla_residenza")
    private Integer siglaResidenza;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;
    private Boolean logged;

    private Boolean isAdmin;

    public UtenteImpl(String mail, String password, String nome, String cognome, Integer siglaResidenza,
                      LocalDate dataNascita, String username) {
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.siglaResidenza = siglaResidenza;
        this.dataNascita = dataNascita;
        this.username = username;
        this.logged = false;
        this.isAdmin = false;
    }

    @OneToMany(mappedBy = "utente")
    private List<MetodoPagamentoImpl> pagamenti = new ArrayList<>();

    @Override
    public void setLogged(){
        this.logged = !(this.logged);
    }

    public void setIsAdmin(){
        this.isAdmin = !(this.isAdmin);
    }
}
