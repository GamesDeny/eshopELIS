package com.elismarket.eshop.businessLogic.controller;

import com.elismarket.eshop.businessLogic.services.ProdottoService;
import com.elismarket.eshop.model.dto.ProdottoDTO;
import com.elismarket.eshop.model.entities.ProdottoImpl;
import com.elismarket.eshop.model.interfaces.Prodotto;
import com.elismarket.eshop.model.interfaces.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
 *
 * Rest API for Products
 * Contains all informations for making API requests for frontend
 *
 */

@RestController
@SessionAttributes({"utente"})
@RequestMapping(path = "/rest/prodotto", produces = "application/json")
@CrossOrigin(origins = "*")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @PostMapping("/add")
    public void addProduct(@RequestBody ProdottoDTO prodottoDTO) {
        prodottoService.saveProdotto(prodottoDTO);
    }

    @DeleteMapping("/remove/id/{id}")
    public void removeProduct(@PathVariable("id") Long id) {
        prodottoService.removeById(id);
    }

    //returns all db products
    @GetMapping("/all")
    public List<ProdottoImpl> getAll(Model model) {
        List<ProdottoImpl> lista = prodottoService.getAll();
        Utente u = (Utente) model.getAttribute("utente");

        //if there is no user in this session
        if (u == null)
            throw new RuntimeException("User not in session");

        //removes all product with quantity=0 if is not an admin
        if (!u.getIsAdmin())
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
    public List<Prodotto> getByNomeCategoria(@PathVariable("name") ProdottoDTO prodottoDTO) {
        return prodottoService.getProdottoByCategoria(prodottoDTO);
    }

    @PatchMapping("/update")
    public Boolean updateProdotto(@RequestBody ProdottoDTO prodottoDTO) {
        prodottoService.updateProdotto(prodottoDTO);
        return true;
    }

    @DeleteMapping("/remove/{id}")
    public void removeProdotto(@PathVariable("id") Long id) {
        prodottoService.removeProdotto(id);
    }

}
