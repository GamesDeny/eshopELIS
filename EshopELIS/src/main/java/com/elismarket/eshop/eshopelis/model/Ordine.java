package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Ordine class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all Ordine information
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordine")
public class Ordine {

    /**
     * Primary key of the Entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Status of Order, false by default
     */
    @Column
    private Boolean evaso;

    /**
     * data when Ordine was evaso (payed)
     */
    @Column
    private LocalDate dataEvasione;

    /**
     * All {@link Prodotto Prodotto} linked to the Ordine
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ordine")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<RigaOrdine> righeOrdine;

    /**
     * {@link Prodotto Prodotto} linked to the Ordine
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utente utente;

    /**
     * {@link Prodotto Prodotto} linked to the Ordine
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

    public Ordine(Boolean evaso, LocalDate dataEvasione) {
        this.evaso = evaso;
        this.dataEvasione = dataEvasione;
    }

    /**
     * Returns an instance of Ordine from a {@link OrdineDTO OrdineDTO}
     *
     * @param ordineDTO instance of OrdineDTO
     * @return Ordine representation of OrdineDTO
     */
    public static Ordine of(OrdineDTO ordineDTO) {
        return Ordine.builder()
                .evaso(ordineDTO.evaso)
                .dataEvasione(ordineDTO.dataEvasione)
                .build();
    }

    /**
     * Returns an instance of {@link OrdineDTO OrdineDTO} from a Ordine
     *
     * @param ordine instance of Ordine
     * @return OrdineDTO representation of the Ordine
     */
    public static OrdineDTO to(Ordine ordine) {
        OrdineDTO ordineDTO = new OrdineDTO();

        ordineDTO.id = ordine.getId();
        ordineDTO.evaso = ordine.getEvaso();
        ordineDTO.dataEvasione = ordine.getDataEvasione();
        ordineDTO.pagamento_id = ordine.getPagamento().getId();
        ordineDTO.utente_id = ordine.getUtente().getId();

        return ordineDTO;
    }
}
