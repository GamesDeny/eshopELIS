package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
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
@AllArgsConstructor
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;


    @Column(unique = true, nullable = false)
    private String mail, username;

    @Column(nullable = false)
    private String password, nome, cognome;

    @Column(name = "sigla_residenza", nullable = false)
    private Integer siglaResidenza;

    @Column(name = "data_nascita", nullable = false)
    private LocalDate dataNascita;

    @Column(nullable = false)
    private Boolean logged, isAdmin;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utente")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Proposta> proposta;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utente")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Pagamento> pagamenti = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utente")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Prodotto> prodotti = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utente")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "utente")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Ordine> ordini = new ArrayList<>();

    public Utente(String mail, String password, String nome, String cognome, Integer siglaResidenza,
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

    public static Utente of(UtenteDTO utenteDTO) {
        return Utente.builder()
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

    public static UtenteDTO to(Utente utente) {
        UtenteDTO u = new UtenteDTO();

        u.id = utente.getId();
        u.mail = utente.getMail();
        u.username = utente.getUsername();
        u.password = utente.getPassword();
        u.nome = utente.getNome();
        u.cognome = utente.getCognome();
        u.siglaResidenza = utente.getSiglaResidenza();
        u.dataNascita = utente.getDataNascita();
        u.logged = utente.getLogged();
        u.isAdmin = utente.getIsAdmin();

        return u;
    }

    public static String hashPassword(String password) {
        //if values are changed we need to reHash all DB passwords
        return String.valueOf(password.hashCode() * 57 * 666 * 69 * 420);
    }
}
