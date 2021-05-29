package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.OrdineException;
import com.elismarket.eshop.eshopelis.helper.PagamentoHelper;
import com.elismarket.eshop.eshopelis.helper.RigaOrdineHelper;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
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

    @Override
    public OrdineDTO saveOrdine(OrdineDTO ordineDTO) {
        ordineCrud.save(Ordine.of(ordineDTO));
        return Ordine.to(ordineCrud.findById(ordineDTO.id).orElseThrow(() -> new OrdineException("Cannot find Ordine")));
    }

    @Override
    public OrdineDTO updateOrdine(Long id, OrdineDTO ordineDTO) {
        if (!ordineCrud.existsById(id))
            throw new OrdineException("Not found");

        Ordine o = ordineCrud.findById(id).orElseThrow(() -> new OrdineException("Cannot find Ordine"));

        ordineDTO.id = id;
        ordineDTO.evaso = Objects.isNull(ordineDTO.evaso) ? o.getEvaso() : ordineDTO.evaso;
        ordineDTO.dataEvasione = Objects.isNull(ordineDTO.dataEvasione) ? o.getDataEvasione() : ordineDTO.dataEvasione;

        return Ordine.to(ordineCrud.findById(id).orElseThrow(() -> new OrdineException("Cannot find Ordine")));
    }

    @Override
    public Boolean removeOrdine(Long id) {
        if (!ordineCrud.existsById(id))
            throw new OrdineException("Not found");

        ordineCrud.deleteById(id);
        return !ordineCrud.existsById(id);
    }

    @Override
    public List<OrdineDTO> getAll() {
        if (ordineCrud.findAll().isEmpty())
            throw new OrdineException("List is empty");

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAll().forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public List<OrdineDTO> getEvaso(Boolean evaso) {
        if (ordineCrud.findAllByEvaso(evaso).isEmpty())
            throw new OrdineException("List is empty");

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByEvaso(evaso).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public List<OrdineDTO> getDataPrima(LocalDate dataEvasione) {
        if (ordineCrud.findAllByDataEvasioneBefore(dataEvasione).isEmpty())
            throw new OrdineException("List is empty");

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByDataEvasioneBefore(dataEvasione).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public List<OrdineDTO> getDataTra(LocalDate dataEvasione1, LocalDate dataEvasione2) {
        if (ordineCrud.findAllByDataEvasioneBetween(dataEvasione1, dataEvasione2).isEmpty())
            throw new OrdineException("List is empty");

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByDataEvasioneBetween(dataEvasione1, dataEvasione2).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public List<OrdineDTO> getDataDopo(LocalDate dataEvasione) {
        if (ordineCrud.findAllByDataEvasioneAfter(dataEvasione).isEmpty())
            throw new OrdineException("List is empty");

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByDataEvasioneAfter(dataEvasione).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public OrdineDTO getById(Long id) {
        if (!ordineCrud.findById(id).isPresent())
            throw new OrdineException("Not found");
        return Ordine.to(ordineCrud.findById(id).orElseThrow(() -> new OrdineException("Cannot find Ordine")));
    }

    @Override
    public RigaOrdine addRigaOrdineToOrdine(Long ordineId, RigaOrdineDTO rigaOrdineDTO) {
        if (!ordineCrud.existsById(ordineId))
            throw new OrdineException("Cannot find Ordine");

        return rigaOrdineHelper.addRigaOrdineToOrdine(ordineId, rigaOrdineDTO);
    }
}
