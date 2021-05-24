package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.UtenteDTO;

import java.util.List;

public interface UtenteService {
    UtenteDTO addUtente(UtenteDTO utenteDTO);

    Boolean removeUtente(Long id);

    List<UtenteDTO> getAll(String findby);

    UtenteDTO getByMail(String mail);

    UtenteDTO getByUser(String username);

    UtenteDTO getUtente(Long id);

    UtenteDTO getUtente(Integer siglaResidenza);

    UtenteDTO getLoginUtente(String username, String password);
}
