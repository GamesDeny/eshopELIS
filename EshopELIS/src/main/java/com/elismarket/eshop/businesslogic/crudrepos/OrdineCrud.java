package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.model.OrdineImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrdineCrud extends CrudRepository<OrdineImpl, Long> {

    @Transactional
    @Query(value = "INSERT INTO (data_evasione, evaso) VALUES ()", nativeQuery = true)
    public void insert();
}
