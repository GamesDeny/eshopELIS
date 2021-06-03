package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Categoria class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all Categoria information
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
@Table(name = "categoria")
public class Categoria {

    /**
     * Primary key of the Entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    /**
     * Name of the Categoria
     */
    @Column(nullable = false)
    private String nome;

    /**
     * All {@link Prodotto Prodotto} linked to the Categoria
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categoria")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Prodotto> prodotto = new ArrayList<>();

    /**
     * Returns an instance of Categoria from a {@link CategoriaDTO CategoriaDTO}
     *
     * @param categoriaDTO instance of CategoriaDTO
     * @return Categoria representation of CategoriaDTO
     */
    public static Categoria of(CategoriaDTO categoriaDTO) {
        return Categoria.builder()
                .id(categoriaDTO.id)
                .nome(categoriaDTO.nome)
                .build();
    }

    /**
     * Returns an instance of {@link CategoriaDTO CategoriaDTO} from a Categoria
     *
     * @param categoria instance of Categoria
     * @return CategoriaDTO representation of the Categoria
     */
    public static CategoriaDTO to(Categoria categoria) {
        CategoriaDTO c = new CategoriaDTO();

        c.id = categoria.id;
        c.nome = categoria.nome;

        return c;
    }
}
