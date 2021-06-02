package com.elismarket.eshop.eshopelis.service;

import com.elismarket.eshop.eshopelis.dto.OrdineDTO;
import com.elismarket.eshop.eshopelis.dto.RigaOrdineDTO;
import com.elismarket.eshop.eshopelis.exception.OrdineException;
import com.elismarket.eshop.eshopelis.helper.PagamentoHelper;
import com.elismarket.eshop.eshopelis.helper.RigaOrdineHelper;
import com.elismarket.eshop.eshopelis.helper.UtenteHelper;
import com.elismarket.eshop.eshopelis.model.Ordine;
import com.elismarket.eshop.eshopelis.model.RigaOrdine;
import com.elismarket.eshop.eshopelis.repository.OrdineCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.elismarket.eshop.eshopelis.exception.ExceptionPhrases.*;


/*
 *
 * Service class for CRUD operations and control of Order
 *
 */

@Service
public class OrdineServiceImpl implements OrdineService {

    @Autowired
    OrdineCrud ordineCrud;

    @Autowired
    PagamentoHelper pagamentoHelper;

    @Autowired
    RigaOrdineHelper rigaOrdineHelper;

    @Autowired
    UtenteHelper utenteHelper;

    @Override
    public OrdineDTO saveOrdine(Long userId, Long pagamentoId, List<RigaOrdineDTO> righe) {
        Ordine o = new Ordine();
        List<Long> righeId = new ArrayList<>();
        righe.forEach(rigaOrdineDTO -> righeId.add(rigaOrdineDTO.id));

        o.setPagamento(pagamentoHelper.findById(pagamentoId));
        o.setUtente(utenteHelper.findById(userId));
        rigaOrdineHelper.linkRigheToOrdine(o.getId(), righeId);

        return Ordine.to(ordineCrud.saveAndFlush(o));
    }

    @Override
    public OrdineDTO updateOrdine(Long ordineId, OrdineDTO ordineDTO) {
        if (!ordineCrud.existsById(ordineId))
            throw new OrdineException(CANNOT_FIND_ELEMENT.name());

        Ordine o = ordineCrud.findById(ordineId).orElseThrow(() -> new OrdineException(CANNOT_FIND_ELEMENT.name()));

        ordineDTO.id = ordineId;
        ordineDTO.evaso = Objects.isNull(ordineDTO.evaso) ? o.getEvaso() : ordineDTO.evaso;
        ordineDTO.dataEvasione = Objects.isNull(ordineDTO.dataEvasione) ? o.getDataEvasione() : ordineDTO.dataEvasione;

        Ordine save = Ordine.of(ordineDTO);
        save.setPagamento(Objects.isNull(ordineDTO.pagamento_id) ? o.getPagamento() : pagamentoHelper.findById(ordineDTO.pagamento_id));
        if (!Objects.isNull(ordineDTO.righeOrdine_id))
            rigaOrdineHelper.linkRigheToOrdine(ordineId, ordineDTO.righeOrdine_id);
        ordineCrud.saveAndFlush(save);

        return Ordine.to(ordineCrud.findById(ordineId).orElseThrow(() -> new OrdineException(CANNOT_FIND_ELEMENT.name())));
    }

    @Override
    public Boolean removeOrdine(Long id) {
        if (!ordineCrud.existsById(id))
            throw new OrdineException(CANNOT_FIND_ELEMENT.name());

        ordineCrud.deleteById(id);
        return !ordineCrud.existsById(id);
    }

    @Override
    public List<OrdineDTO> getAll() {
        if (ordineCrud.findAll().isEmpty())
            throw new OrdineException(LIST_IS_EMPTY.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAll().forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public List<OrdineDTO> getAllByUtente(Long userId) {
        if (Objects.isNull(userId))
            throw new OrdineException(MISSING_PARAMETERS.name());

        if (ordineCrud.findAllByUtente(utenteHelper.findById(userId)).isEmpty())
            throw new OrdineException(LIST_IS_EMPTY.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByUtente(utenteHelper.findById(userId)).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public List<OrdineDTO> getEvaso(Boolean evaso) {
        if (ordineCrud.findAllByEvaso(evaso).isEmpty())
            throw new OrdineException(LIST_IS_EMPTY.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByEvaso(evaso).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public List<OrdineDTO> getDataPrima(LocalDate dataEvasione) {
        if (ordineCrud.findAllByDataEvasioneBefore(dataEvasione).isEmpty())
            throw new OrdineException(LIST_IS_EMPTY.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByDataEvasioneBefore(dataEvasione).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public List<OrdineDTO> getDataTra(LocalDate dataEvasione1, LocalDate dataEvasione2) {
        if (ordineCrud.findAllByDataEvasioneBetween(dataEvasione1, dataEvasione2).isEmpty())
            throw new OrdineException(LIST_IS_EMPTY.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByDataEvasioneBetween(dataEvasione1, dataEvasione2).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public List<OrdineDTO> getDataDopo(LocalDate dataEvasione) {
        if (ordineCrud.findAllByDataEvasioneAfter(dataEvasione).isEmpty())
            throw new OrdineException(LIST_IS_EMPTY.name());

        List<OrdineDTO> result = new ArrayList<>();
        ordineCrud.findAllByDataEvasioneAfter(dataEvasione).forEach(ordine -> result.add(Ordine.to(ordine)));
        return result;
    }

    @Override
    public OrdineDTO getById(Long id) {
        if (!ordineCrud.findById(id).isPresent())
            throw new OrdineException(CANNOT_FIND_ELEMENT.name());
        return Ordine.to(ordineCrud.findById(id).orElseThrow(() -> new OrdineException(CANNOT_FIND_ELEMENT.name())));
    }

    @Override
    public RigaOrdine addRigaOrdineToOrdine(Long ordineId, RigaOrdineDTO rigaOrdineDTO) {
        if (!ordineCrud.existsById(ordineId))
            throw new OrdineException(CANNOT_FIND_ELEMENT.name());

        return rigaOrdineHelper.addRigaOrdineToOrdine(ordineId, rigaOrdineDTO);
    }
}
