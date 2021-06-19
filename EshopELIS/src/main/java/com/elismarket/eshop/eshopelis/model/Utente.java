package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.UtenteDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Utente class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all Utente information
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Utente {

    /**
     * Primary key of the Entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Mail of the Utente
     */
    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String mail;

    /**
     * Username of the Utente
     */
    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String username;

    /**
     * password of the Utente
     */
    @Column(nullable = false)
    private String password;

    /**
     * name of the Utente
     */
    @Column(nullable = false)
    private String nome;

    /**
     * surname of the Utente
     */
    @Column(nullable = false)
    private String cognome;

    /**
     * ELIS sigla of the residence for the Utente
     */
    @Column(name = "sigla_residenza", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private Integer siglaResidenza;

    /**
     * birth date of the Utente
     */
    @Column(name = "data_nascita", nullable = false)
    @EqualsAndHashCode.Include
    private LocalDate dataNascita;

    /**
     * indicates actual login of the user, false by default
     */
    @Column(nullable = false)
    private Boolean logged;

    /**
     * indicates if the Utente is an admin
     */
    @Column(nullable = false)
    private Boolean isAdmin;

    /**
     * All {@link Prodotto Prodotto} linked to the Utente
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Proposta> proposta;

    /**
     * All {@link Prodotto Prodotto} linked to the Utente
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Pagamento> pagamenti = new ArrayList<>();

    /**
     * All {@link Prodotto Prodotto} linked to the Utente
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Prodotto> prodotti = new ArrayList<>();

    /**
     * All {@link Prodotto Prodotto} linked to the Utente
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Feedback> feedbacks = new ArrayList<>();

    /**
     * All {@link Prodotto Prodotto} linked to the Utente
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "utente")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Ordine> ordini = new ArrayList<>();

    /**
     * Returns an instance of Utente from a {@link UtenteDTO UtenteDTO}
     *
     * @param utenteDTO instance of UtenteDTO
     * @return Utente representation of UtenteDTO
     */
    public static Utente of(UtenteDTO utenteDTO) {
        return Utente.builder()
                .mail(utenteDTO.mail)
                .username(utenteDTO.username)
                .password(utenteDTO.password)
                .nome(utenteDTO.nome)
                .cognome(utenteDTO.cognome)
                .siglaResidenza(utenteDTO.siglaResidenza)
                .dataNascita(utenteDTO.dataNascita)
                .logged(utenteDTO.logged)
                .isAdmin(utenteDTO.isAdmin)
                .build();
    }

    /**
     * Returns an instance of {@link UtenteDTO UtenteDTO} from a Utente
     *
     * @param utente instance of Utente
     * @return UtenteDTO representation of the Utente
     */
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

    /**
     * Function to HashPassword on DB
     *
     * @param password {@link Utente#password} to Hash
     * @return String representation of hashed password
     */
    public static String hashPassword(String password) {
        //if values are changed we need to reHash all DB passwords
        return String.valueOf(password.hashCode() * 57 * 666 * 69 * 420);
    }
}
