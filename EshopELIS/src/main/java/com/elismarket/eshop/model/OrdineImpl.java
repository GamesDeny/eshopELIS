package com.elismarket.eshop.model;

import com.elismarket.eshop.dto.OrdineDTO;
import com.elismarket.eshop.interfaces.Ordine;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/*
 *
 * Order class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all orders informations
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "ordine")
public class OrdineImpl implements Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;


    private Boolean evaso;
    private LocalDate dataEvasione;

    @OneToMany(mappedBy = "ordine")
    private List<RigaOrdineImpl> righeOrdine;

    @ManyToOne
    private PagamentoImpl pagamento;

    public OrdineImpl(Boolean evaso, LocalDate dataEvasione) {
        this.evaso = evaso;
        this.dataEvasione = dataEvasione;
    }

    public static OrdineImpl of(OrdineDTO ordineDTO) {
        return OrdineImpl.builder()
                .evaso(ordineDTO.evaso)
                .dataEvasione(ordineDTO.dataEvasione)
                .build();
    }
}
