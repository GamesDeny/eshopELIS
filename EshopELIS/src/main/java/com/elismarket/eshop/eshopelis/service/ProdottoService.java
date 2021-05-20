package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.UtenteDTO;

import java.util.List;

public interface ProdottoService {
    List<UtenteDTO> getAll(String findby);

    UtenteDTO getByMail(String mail);

    UtenteDTO getByUser(String username);

    UtenteDTO getUtente(Long id);

    UtenteDTO getUtente(Integer siglaResidenza);

    Boolean addUtente(UtenteDTO utenteDTO);

    void removeUtente(Long id);

    UtenteDTO getLoginUtente(String username, String password);
}
