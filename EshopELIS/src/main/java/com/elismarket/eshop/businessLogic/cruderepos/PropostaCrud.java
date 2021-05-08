package com.elismarket.eshop.businessLogic.cruderepos;

import com.elismarket.eshop.model.entities.PropostaImpl;
import com.elismarket.eshop.model.interfaces.Proposta;
import com.elismarket.eshop.model.interfaces.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaCrud extends CrudRepository<PropostaImpl, Long> {

    List<Proposta> findAllByIsAccettato(Boolean isAccettato);

    List<Proposta> findAllByUtente(Long id);

    List<Proposta> findAllByUtente(Utente utente);

}
