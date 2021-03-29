package com.elismarket.eshop.controller;

import com.elismarket.eshop.dto.ProdottoDTO;
import com.elismarket.eshop.interfaces.Prodotto;
import com.elismarket.eshop.interfaces.Utente;
import com.elismarket.eshop.model.ProdottoImpl;
import com.elismarket.eshop.services.ProdottoService;
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

    @DeleteMapping("/remove/id")
    public void removeProduct(@RequestParam("id") Long id) {
        prodottoService.removeById(id);
    }

    //returns all db products
    @GetMapping("/getall")
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

    @GetMapping("/getall/quantita/maggiore")
    public List<Prodotto> findByQuantitaMaggiore(Integer quantita) {
        return prodottoService.findByQuantitaMaggiore(quantita);
    }

    @GetMapping("/getall/quantita/minore")
    public List<Prodotto> findByQuantitaMinore(Integer quantita) {
        return prodottoService.findByQuantitaMinore(quantita);
    }

    @GetMapping("/getprodotto/categoria")
    public List<Prodotto> getByNomeCategoria(@RequestBody ProdottoDTO prodottoDTO) {
        return prodottoService.getProdottoByCategoria(prodottoDTO);
    }
}
