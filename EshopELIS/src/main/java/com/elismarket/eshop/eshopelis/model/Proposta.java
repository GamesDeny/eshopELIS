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
    private Long id;

    @Column(nullable = false)
    private String nome, descrizione;

    @Column
    private String motivoRifiuto;

    @Column(nullable = false)
    private Float prezzoProposto;

    @Column(nullable = false)
    private Integer quantita;

    @Column
    private Boolean isAccettato;

    @Column(nullable = false)
    private LocalDate submissionDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "utente_id")
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
        p.categoria_id = proposta.getCategoria().getId();
        p.utente_id = proposta.getUtente().getId();

        return p;
    }
}
