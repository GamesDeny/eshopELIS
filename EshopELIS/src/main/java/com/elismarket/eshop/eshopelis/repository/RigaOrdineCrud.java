package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Jpa repository for {@link RigaOrdine RigaOrdine}
 *
 * @author Francesco Pio Montrano, Gennaro Quaranta, Massimo Piccinno
 * @version 1.0
 */
public interface RigaOrdineCrud extends JpaRepository<RigaOrdine, Long> {
    List<RigaOrdine> findAllByOrdine(Ordine ordine);

    @Query("SELECT r from RigaOrdine r join Ordine o on o.id = r.ordine.id where o.id in (select o from Ordine o where o.evaso = true)")
    List<RigaOrdine> findAllByOrdineEvaso();
}
