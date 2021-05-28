package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proposta")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nome, descrizione;

    @Column(nullable = true)
    private String motivoRifiuto;

    private Float prezzoProposto;
    private Integer quantita;

    @Column(nullable = true)
    private Boolean isAccettato;


    private LocalDate submissionDate;

    @ManyToOne
    private Utente utente;

    public static Proposta of(PropostaDTO propostaDTO) {
        return Proposta.builder()
                .id(propostaDTO.id)
                .nome(propostaDTO.nome)
                .descrizione(propostaDTO.descrizione)
                .motivoRifiuto(propostaDTO.motivoRifiuto)
                .prezzoProposto(propostaDTO.prezzoProposto)
                .quantita(propostaDTO.quantita)
                .isAccettato(propostaDTO.isAccettato)
                .submissionDate(propostaDTO.submissionDate)
                .build();
    }

    public static PropostaDTO to(Proposta proposta) {
        PropostaDTO p = new PropostaDTO();

        p.id = proposta.getId();
        p.nome = proposta.getNome();
        p.descrizione = proposta.getDescrizione();
        p.motivoRifiuto = proposta.getMotivoRifiuto();
        p.prezzoProposto = proposta.getPrezzoProposto();
        p.quantita = proposta.getQuantita();
        p.isAccettato = proposta.getIsAccettato();
        p.submissionDate = proposta.getSubmissionDate();

        return p;
    }
}
