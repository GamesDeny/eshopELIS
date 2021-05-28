package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Order class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all orders information
 *
 * @author Francesco Pio Montrano
 * @version 1.0
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordine")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = true)
    private Boolean evaso;

    @Column(nullable = true)
    private LocalDate dataEvasione;

    @OneToMany(mappedBy = "ordine")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<RigaOrdine> righeOrdine;

    @ManyToOne
    private Pagamento pagamento;

    public Ordine(Boolean evaso, LocalDate dataEvasione) {
        this.evaso = evaso;
        this.dataEvasione = dataEvasione;
    }

    public static Ordine of(OrdineDTO ordineDTO) {
        return Ordine.builder()
                .evaso(ordineDTO.evaso)
                .dataEvasione(ordineDTO.dataEvasione)
                .build();
    }

    public static OrdineDTO to(Ordine ordine) {
        OrdineDTO ordineDTO = new OrdineDTO();

        ordineDTO.id = ordine.getId();
        ordineDTO.evaso = ordine.getEvaso();
        ordineDTO.dataEvasione = ordine.getDataEvasione();

        return ordineDTO;
    }
}
