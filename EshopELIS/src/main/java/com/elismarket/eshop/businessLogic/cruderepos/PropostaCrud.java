package com.elismarket.eshop.businessLogic.cruderepos;

import com.elismarket.eshop.model.entities.PropostaImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaCrud extends CrudRepository<PropostaImpl, Long> {
}
