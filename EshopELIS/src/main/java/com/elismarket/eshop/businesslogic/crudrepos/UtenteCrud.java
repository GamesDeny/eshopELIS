package com.elismarket.eshop.businesslogic.crudrepos;

import com.elismarket.eshop.interfaces.Utente;
import com.elismarket.eshop.model.UtenteImpl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface UtenteCrud extends CrudRepository<UtenteImpl, Long> {
    List<Utente> findAllByUsernameAndPassword(String username, String password);

    @Query(value = "INSERT INTO UtenteImpl (cognome, data_di_nascita, logged, mail, nome, password, sigla_residenza, username)" +
            " VALUES (:cognome, :dataNascita, false, :mail, :nome, :password, :siglaResidenza,:username)", nativeQuery = true)
    public void insertUser(@Param("cognome") String cognome,
                           @Param("dataNascita") LocalDate dataNascita,
                           @Param("mail") String mail,
                           @Param("nome") String nome,
                           @Param("password") String password,
                           @Param("siglaResidenza") Integer siglaResidenza,
                           @Param("username") String username);
}
