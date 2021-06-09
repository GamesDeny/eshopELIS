package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TipoMetodo class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all TipoMetodo information
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
public class TipoMetodo {
    /**
     * Primary key of the Entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name for the TipoMetodo
     */
    @Column(nullable = false)
    private String nome;

    /**
     * All {@link Prodotto Prodotto} linked to the TipoMetodo
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tipoMetodo")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Pagamento> pagamenti = new ArrayList<>();

    /**
     * Returns an instance of TipoMetodo from a {@link TipoMetodoDTO TipoMetodoDTO}
     *
     * @param tipoMetodoDTO instance of TipoMetodoDTO
     * @return TipoMetodo representation of TipoMetodoDTO
     */
    public static TipoMetodo of(TipoMetodoDTO tipoMetodoDTO) {
        return TipoMetodo.builder()
                .id(tipoMetodoDTO.id)
                .nome(tipoMetodoDTO.nome)
                .build();
    }

    /**
     * Returns an instance of {@link TipoMetodoDTO TipoMetodoDTO} from a TipoMetodo
     *
     * @param tipoMetodo instance of TipoMetodo
     * @return TipoMetodoDTO representation of the TipoMetodo
     */
    public static TipoMetodoDTO to(TipoMetodo tipoMetodo) {
        TipoMetodoDTO t = new TipoMetodoDTO();

        t.id = tipoMetodo.id;
        t.nome = tipoMetodo.nome;

        return t;
    }
}
