package com.elismarket.eshop.model.entities;

import com.elismarket.eshop.model.dto.PropostaDTO;
import com.elismarket.eshop.model.interfaces.Proposta;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "proposta")
public class PropostaImpl implements Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String nome, descrizione, motivoRifiuto;
    private Float prezzoProposto;
    private Integer quantita;
    private Boolean isAccettato;

    @ManyToOne
    private UtenteImpl utente;

    public static PropostaImpl of(PropostaDTO propostaDTO) {
        return PropostaImpl.builder()
                .id(propostaDTO.getId())
                .nome(propostaDTO.getNome())
                .descrizione(propostaDTO.getDescrizione())
                .motivoRifiuto(propostaDTO.getMotivoRifiuto())
                .prezzoProposto(propostaDTO.getPrezzoProposto())
                .quantita(propostaDTO.getQuantita())
                .isAccettato(propostaDTO.getIsAccettato())
                .build();
    }
}
