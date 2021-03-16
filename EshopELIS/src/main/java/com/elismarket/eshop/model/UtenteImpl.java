package com.elismarket.eshop.model;

import com.elismarket.eshop.interfaces.Pagamento;
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
    private Long id;


    private String mail, password, nome, cognome, username;
    private Integer siglaResidenza;
    private LocalDate dataDiNascita;
    private Boolean logged;

    public UtenteImpl(String mail, String password, String nome, String cognome, Integer siglaResidenza, LocalDate dataDiNascita, String username) {
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.siglaResidenza = siglaResidenza;
        this.dataDiNascita = dataDiNascita;
        this.username = username;
        this.logged = false;
    }

    @JoinColumn(name = "utenti")
    @OneToMany
    private List<PagamentoImpl> pagamenti = new ArrayList<>();

    @JoinTable (name = "prodotto_ordini", joinColumns = @JoinColumn(name = "ordine_id"), inverseJoinColumns = @JoinColumn(name = "prodotto_id"))
    @OneToMany
    private List<OrdineImpl> ordini = new ArrayList<>();

    @Override
    public void setLogged(){
        this.logged = !(this.logged);
    }
}
