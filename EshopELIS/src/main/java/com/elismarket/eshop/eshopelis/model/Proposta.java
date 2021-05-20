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

    private String nome, descrizione, motivoRifiuto;
    private Float prezzoProposto;
    private Integer quantita;
    private Boolean isAccettato;
    private LocalDate submissionDate;

    @ManyToOne
    private Utente utente;

    public static Proposta of(PropostaDTO propostaDTO) {
        return Proposta.builder()
                .id(propostaDTO.getId())
                .nome(propostaDTO.getNome())
                .descrizione(propostaDTO.getDescrizione())
                .motivoRifiuto(propostaDTO.getMotivoRifiuto())
                .prezzoProposto(propostaDTO.getPrezzoProposto())
                .quantita(propostaDTO.getQuantita())
                .isAccettato(propostaDTO.getIsAccettato())
                .submissionDate(propostaDTO.submissionDate)
                .build();
    }
}
