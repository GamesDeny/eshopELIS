package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Pagamento;
import com.elismarket.eshop.interfaces.Utente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UtenteImpl implements Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String mail, password, nome, cognome;

    @Getter
    @Setter
    private Integer siglaResidenza;

    @Getter
    @Setter
    private LocalDate dataDiNascita;

    public UtenteImpl() {
    }

    public UtenteImpl(String mail, String password, String nome, String cognome, Integer siglaResidenza, LocalDate dataDiNascita) {
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.siglaResidenza = siglaResidenza;
        this.dataDiNascita = dataDiNascita;
    }

    @JoinColumn(name = "utenti")
    @Getter
    @Setter
    @OneToMany
    private List<PagamentoImpl> pagamenti = new ArrayList<>();

    @JoinTable (name = "prodotto_ordini", joinColumns = @JoinColumn(name = "ordine_id"), inverseJoinColumns = @JoinColumn(name = "prodotto_id"))
    @Getter
    @Setter
    @OneToMany
    private List<OrdineImpl> ordini = new ArrayList<>();
}
