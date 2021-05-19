package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.ProdottoService;
import com.elismarket.eshop.model.dto.ProdottoDTO;
import com.elismarket.eshop.model.entities.ProdottoImpl;
import com.elismarket.eshop.model.interfaces.Prodotto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/*
 *
 * Rest API for Products
 * Contains all informations for making API requests for frontend
 *
 */

@RestController
@RequestMapping(path = "/rest/prodotto", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @PostMapping("/add")
    public void addProduct(@RequestBody ProdottoDTO prodottoDTO) {
        prodottoService.saveProdotto(prodottoDTO);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateProdotto(@PathVariable("id") Long id, @RequestBody ProdottoDTO prodottoDTO) {
        Prodotto p = prodottoService.getById(id);

        prodottoDTO.setId(id);
        prodottoDTO.setDescrizione(Objects.isNull(prodottoDTO.getDescrizione()) ? p.getDescrizione() : prodottoDTO.descrizione);
        prodottoDTO.setNome(Objects.isNull(prodottoDTO.getNome()) ? p.getNome() : prodottoDTO.nome);
        prodottoDTO.setQuantita(Objects.isNull(prodottoDTO.getQuantita()) ? p.getQuantita() : prodottoDTO.quantita);
        prodottoDTO.setImage(Objects.isNull(prodottoDTO.getImage()) ? p.getImage() : prodottoDTO.image);
        prodottoDTO.setMaxOrd(Objects.isNull(prodottoDTO.getMaxOrd()) ? p.getMaxOrd() : prodottoDTO.maxOrd);
        prodottoDTO.setMinOrd(Objects.isNull(prodottoDTO.getMinOrd()) ? p.getMinOrd() : prodottoDTO.minOrd);
        prodottoDTO.setNomeCategoria(Objects.isNull(prodottoDTO.getNomeCategoria()) ? p.getNomeCategoria() : prodottoDTO.nomeCategoria);
        prodottoDTO.setPrezzo(Objects.isNull(prodottoDTO.getPrezzo()) ? p.getPrezzo() : prodottoDTO.prezzo);
        prodottoDTO.setSconto(Objects.isNull(prodottoDTO.getSconto()) ? p.getSconto() : prodottoDTO.sconto);

        return prodottoService.updateProdotto(prodottoDTO) ? ResponseEntity.status(200).build() : ResponseEntity.status(500).build();
    }

    @DeleteMapping("/remove/{id}")
    public void removeProdotto(@PathVariable("id") Long id) {
        prodottoService.removeProdotto(id);
    }

    //returns all db products
    @GetMapping("/all")
    public List<ProdottoImpl> getAll() {
        List<ProdottoImpl> lista = prodottoService.getAll();

        //removes all product with quantity=0 if is not an admin
        for (int i = lista.size() - 1; i >= 0; i--)
            if (lista.get(i).getQuantita() == 0)
                lista.remove(i);

        return lista;
    }

    @GetMapping("/all/quantita/maggiore/{quantita}")
    public List<Prodotto> findByQuantitaMaggiore(@PathVariable("quantita") Integer quantita) {
        return prodottoService.findByQuantitaMaggiore(quantita);
    }

    @GetMapping("/all/quantita/minore/{quantita}")
    public List<Prodotto> findByQuantitaMinore(@PathVariable("quantita") Integer quantita) {
        return prodottoService.findByQuantitaMinore(quantita);
    }

    @GetMapping("/categoria/{name}")
    public List<Prodotto> getByNomeCategoria(@PathVariable("name") String categoria) {
        return prodottoService.getProdottoByCategoria(categoria);
    }

    @GetMapping("/categoria/all")
    public List<String> getAllCategoria() {
        return prodottoService.getAllCategoria();
    }

}
