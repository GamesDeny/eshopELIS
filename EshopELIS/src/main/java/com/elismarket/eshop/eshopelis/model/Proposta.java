package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.PropostaDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Proposta class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all Proposta information
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
public class Proposta {

    /**
     * Primary key of the Entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * nome indicates the name of the Prodotto
     * descrizione indicates a brief description of the Prodotto
     */
    @Column(nullable = false)
    private String nome, descrizione;

    /**
     * If isAccettato = false there can be a comment to explain why
     */
    @Column
    private String motivoRifiuto;

    /**
     * Proposal for a price
     */
    @Column(nullable = false)
    private Float prezzoProposto;

    /**
     * Indicates how much item of that type the seller has
     */
    @Column(nullable = false)
    private Integer quantita;

    /**
     * indicates the status of the Proposta, if null it has neither been accepted nor refused
     */
    @Column
    private Boolean isAccettato;

    /**
     * date of submission of the Proposta
     */
    @Column(nullable = false)
    private LocalDate submissionDate;

    /**
     * All {@link Prodotto Prodotto} linked to the Proposta
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    /**
     * All {@link Prodotto Prodotto} linked to the Proposta
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "utente_id")
    private Utente utente;

    /**
     * Returns an instance of Proposta from a {@link PropostaDTO PropostaDTO}
     *
     * @param propostaDTO instance of PropostaDTO
     * @return Proposta representation of PropostaDTO
     */
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

    /**
     * Returns an instance of {@link PropostaDTO PropostaDTO} from a Proposta
     *
     * @param proposta instance of Proposta
     * @return PropostaDTO representation of the Proposta
     */
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
