package com.elismarket.eshop.eshopelis.repository;

import com.elismarket.eshop.eshopelis.model.Proposta;
import com.elismarket.eshop.eshopelis.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PropostaCrud extends JpaRepository<Proposta, Long> {

    List<Proposta> findAllByIsAccettato(Boolean isAccettato);

    List<Proposta> findAllByUtente(Long id);

    List<Proposta> findAllByUtente(Utente utente);

}
