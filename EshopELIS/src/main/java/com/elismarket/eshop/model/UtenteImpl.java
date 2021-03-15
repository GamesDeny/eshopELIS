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
public class UtenteImpl implements Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String mail, password, nome, cognome;
    private Integer siglaResidenza;
    private LocalDate dataDiNascita;

    public UtenteImpl(String mail, String password, String nome, String cognome, Integer siglaResidenza, LocalDate dataDiNascita) {
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.siglaResidenza = siglaResidenza;
        this.dataDiNascita = dataDiNascita;
    }

    @JoinColumn(name = "utenti")
    @OneToMany
    private List<PagamentoImpl> pagamenti = new ArrayList<>();

    @JoinTable (name = "prodotto_ordini", joinColumns = @JoinColumn(name = "ordine_id"), inverseJoinColumns = @JoinColumn(name = "prodotto_id"))
    @OneToMany
    private List<OrdineImpl> ordini = new ArrayList<>();
}
