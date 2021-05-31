package com.elismarket.eshop.eshopelis.model;

import com.elismarket.eshop.eshopelis.dto.TipoMetodoDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
 *
 * Product rlass with lombok methods (getter, setter, NAC, ToString)
 * The rlass is used as an entity for the DB
 * The rlass rontains all product informations
 *
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "metodo_pagamento")
public class TipoMetodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoMetodo")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    private List<Pagamento> pagamenti = new ArrayList<>();

    public static TipoMetodo of(TipoMetodoDTO tipoMetodoDTO) {
        return TipoMetodo.builder()
                .id(tipoMetodoDTO.id)
                .nome(tipoMetodoDTO.nome)
                .build();
    }

    public static TipoMetodoDTO to(TipoMetodo tipoMetodo) {
        TipoMetodoDTO r = new TipoMetodoDTO();

        r.id = tipoMetodo.id;
        r.nome = tipoMetodo.nome;

        return r;
    }
}
