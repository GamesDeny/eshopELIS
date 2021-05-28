package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.exception.OrdineException;
import com.elismarket.eshop.eshopelis.helper.PagamentoHelper;
import com.elismarket.eshop.eshopelis.helper.RigaOrdineHelper;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/*
 *
 * Service class for CRUD operations and control of Order
 *
 */

@Service
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    private PagamentoHelper pagamentoHelper;

    @Autowired
    private RigaOrdineHelper rigaOrdineHelper;

    @Autowired
    private OrdineCrud ordineCrud;

    public OrdineDTO saveOrdine(OrdineDTO ordineDTO) {
        try {
            ordineCrud.save(Ordine.of(ordineDTO));
        } catch (Exception e) {
            throw new OrdineException("Failed to save");
        }
        return ordineCrud.findById(ordineDTO.id).isPresent() ? Ordine.to(ordineCrud.findById(ordineDTO.id).get()) : null;
    }

    @Override
    public OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO) {
        if (!ordineCrud.existsById(id))
            throw new OrdineException("Not found");

        Ordine o = ordineCrud.findById(id).get();

        ordineDTO.id = id;
        ordineDTO.evaso = Objects.isNull(ordineDTO.evaso) ? o.getEvaso() : ordineDTO.evaso;
        ordineDTO.dataEvasione = Objects.isNull(ordineDTO.dataEvasione) ? o.getDataEvasione() : ordineDTO.dataEvasione;

        return Ordine.to(ordineCrud.findById(id).get());
    }

    public Boolean removeOrdine(Long id) {
        try {
            ordineCrud.deleteById(id);
            return true;
        } catch (Exception e) {
//            throw new OrdineException("Cannot remove Ordine for provided item");
        }
        return false;
    }

    public List<OrdineDTO> getAll() {
        List<OrdineDTO> result = new ArrayList<>();

        ordineCrud.findAll().forEach(ordine -> result.add(Ordine.to(ordine)));

        return result;
    }

    public List<OrdineDTO> getEvaso(Boolean evaso) {
        List<OrdineDTO> result = new ArrayList<>();

        ordineCrud.findAllByEvaso(evaso).forEach(ordine -> result.add(Ordine.to(ordine)));

        return result;
    }

    public List<OrdineDTO> getDataPrima(LocalDate dataEvasione) {
        List<OrdineDTO> result = new ArrayList<>();

        ordineCrud.findAllByDataEvasioneBefore(dataEvasione).forEach(ordine -> result.add(Ordine.to(ordine)));

        return result;
    }

    public List<OrdineDTO> getDataTra(LocalDate dataEvasione1, LocalDate dataEvasione2) {
        List<OrdineDTO> result = new ArrayList<>();

        ordineCrud.findAllByDataEvasioneBetween(dataEvasione1, dataEvasione2).forEach(ordine -> result.add(Ordine.to(ordine)));

        return result;
    }

    public List<OrdineDTO> getDataDopo(LocalDate dataEvasione) {
        List<OrdineDTO> result = new ArrayList<>();

        ordineCrud.findAllByDataEvasioneAfter(dataEvasione).forEach(ordine -> result.add(Ordine.to(ordine)));

        return result;
    }

    public OrdineDTO getById(Long id) {
        if (!ordineCrud.findById(id).isPresent())
            throw new OrdineException("Not found");
        return Ordine.to(ordineCrud.findById(id).get());
    }
}
