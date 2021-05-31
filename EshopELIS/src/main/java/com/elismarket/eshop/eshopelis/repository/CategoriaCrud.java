package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaCrud extends JpaRepository<Categoria, Long> {
}
