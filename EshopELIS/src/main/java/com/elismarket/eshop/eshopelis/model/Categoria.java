package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/*
 *
 * Product class with lombok methods (getter, setter, NAC, ToString)
 * The class is used as an entity for the DB
 * The class contains all product informations
 *
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Prodotto> prodotto;

    public static Categoria of(CategoriaDTO categoriaDTO) {
        return Categoria.builder()
                .id(categoriaDTO.id)
                .nome(categoriaDTO.nome)
                .build();
    }

    public static CategoriaDTO to(Categoria categoria) {
        CategoriaDTO c = new CategoriaDTO();

        c.id = categoria.id;
        c.nome = categoria.nome;

        return c;
    }
}
