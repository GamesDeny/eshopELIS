package com.elismarket.eshop.businessLogic.cruderepos;

import com.elismarket.eshop.model.entities.UtenteImpl;
import com.elismarket.eshop.model.interfaces.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteCrud extends CrudRepository<UtenteImpl, Long> {

    //    Imposta 0 di default
    List<Utente> findAllByIdGreaterThanEqual(Long Id);

    List<Utente> findAllByIsAdmin(Boolean isAdmin);

    Utente findBySiglaResidenza(Integer siglaResidenza);

    Utente findByUsernameAndPassword(String username, String password);

//    @Transactional
//    @Query(value = "INSERT INTO UtenteImpl (cognome, data_di_nascita, logged, mail, nome, password, sigla_residenza, username, is_admin)" +
//            " VALUES (:cognome, :dataNascita, false, :mail, :nome, :password, :siglaResidenza,:username, false)", nativeQuery = true)
//    void insertUser(@Param("cognome") String cognome, @Param("dataNascita") LocalDate dataNascita,
//                    @Param("mail") String mail, @Param("nome") String nome, @Param("password") String password,
//                    @Param("siglaResidenza") Integer siglaResidenza, @Param("username") String username);

}
