package com.elismarket.eshop.model;

import com.elismarket.eshop.dto.UtenteDTO;
import com.elismarket.eshop.interfaces.Utente;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
 *
 * User class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all users' information to register and login
 *
 */
@Entity
@Builder
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
    private String mail, username;

    @Column(unique = false)
    private String password, nome, cognome;

    @Column(name = "sigla_residenza")
    private Integer siglaResidenza;

    @Column(name = "data_nascita")
    private LocalDate dataNascita;
    private Boolean logged;

    private Boolean isAdmin;
    @OneToMany(mappedBy = "utente")
    private List<MetodoPagamentoImpl> pagamenti = new ArrayList<>();

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

    public static UtenteImpl of(UtenteDTO utenteDTO) {
        return UtenteImpl.builder()
                .mail(utenteDTO.mail)
                .username(utenteDTO.username)
                .password(utenteDTO.password)
                .nome(utenteDTO.nome)
                .cognome(utenteDTO.cognome)
                .siglaResidenza(utenteDTO.siglaResidenza)
                .dataNascita(utenteDTO.dataNascita)
                .logged(false)
                .isAdmin(false)
                .build();
    }

    @Override
    public void setLogged() {
        this.logged = !(this.logged);
    }

    public void setIsAdmin() {
        this.isAdmin = !(this.isAdmin);
    }
}
