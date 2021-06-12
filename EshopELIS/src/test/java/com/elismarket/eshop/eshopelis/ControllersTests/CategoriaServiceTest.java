package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CategoriaServiceTest {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    CategoriaCrud categoriaCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        categoriaCrud.deleteAll();
    }

    @Test
    public void testAddCategoria() {
        //inizializzo una categoria di test e la aggiungo nel db
        CategoriaDTO categoria = new CategoriaDTO();
        categoria.nome = "Altro";

        categoria = categoriaService.addCategoria(categoria);
        //testo che il metodo funzioni
        assertNotNull(categoria);
        assertNotNull(categoriaService.getById(categoria.id));
        assertEquals("Altro", (categoriaService.getById(categoria.id).nome));

        //testo che lanci l'eccezione con un oggetto vuoto
        assertThrows(CategoriaException.class, () -> {
            categoriaService.addCategoria(null);
        });

        //testo che lanci l'eccezione con parametri mancanti
        assertThrows(CategoriaException.class, () -> {
            categoriaService.addCategoria(new CategoriaDTO());
        });
    }

    @Test
    public void testUpdateCategoria() {
        //inizializzo una categoria di test e la aggiungo nel db
        CategoriaDTO categoria = new CategoriaDTO();
        categoria.nome = "Altro";

        categoria = categoriaService.addCategoria(categoria);
        assertNotNull(categoria.id);
        final Long id = categoria.id;

        CategoriaDTO updatedCat = new CategoriaDTO();
        updatedCat.nome = "Pulizia";

        //testo che lanci l'eccezione in caso non gli passi nulla
        assertThrows(CategoriaException.class, () -> {
            categoriaService.updateCategoria(null, null);
        });

        //testo che lanci l'eccezione in caso non gli passi il DTO aggiornato
        assertThrows(CategoriaException.class, () -> {
            categoriaService.updateCategoria(id, null);
        });

        //testo che lanci l'eccezione in caso non gli passi l'id
        assertThrows(CategoriaException.class, () -> {
            categoriaService.updateCategoria(null, updatedCat);
        });

        //testo che lanci l'eccezione in caso gli passi un id non presente
        assertThrows(CategoriaException.class, () -> {
            categoriaService.updateCategoria(2L, updatedCat);
        });

        //testo che lanci l'eccezione con parametri mancanti
        assertThrows(CategoriaException.class, () -> {
            categoriaService.updateCategoria(id, new CategoriaDTO());
        });

        //testo che lanci l'eccezione in caso non gli passi un oggetto nullo come categoriaDTO
        assertNotNull(categoriaService.updateCategoria(id, updatedCat));
        assertEquals("Pulizia", categoriaService.getById(id).nome);

    }

    @Test
    public void testDeleteCategoria() {
        //inizializzo una categoria di test e la aggiungo nel db
        Categoria categoria = new Categoria();
        categoria.setNome("Altro");
        long id = categoriaService.addCategoria(Categoria.to(categoria)).id;
        //prendo dal db l'id della categoria generato


        //testo che la funzione lanci un eccezione con un id non corretto
        assertThrows(CategoriaException.class, () -> {
            categoriaService.deleteCategoria(5L);
        });

        //testo che la funzione lanci un eccezione con un id nullo
        assertThrows(CategoriaException.class, () -> {
            categoriaService.deleteCategoria(null);
        });


        //testo che la funzione dato un id
        assertFalse(categoriaService.deleteCategoria(id));
    }

    @Test
    public void testGetAllCategoria() {
        //inizializzo una categoria di test e la aggiungo nel db
        Categoria categoria = new Categoria();
        categoria.setNome("Altro");
        List<CategoriaDTO> categoriaDTO = new ArrayList<>();
        List<Categoria> categorie = new ArrayList<>();
        categorie.add(categoria);
        categorie.add(categoria);
        categoriaDTO.add(Categoria.to(categoria));
        categoriaDTO.add(Categoria.to(categoria));


        categoriaDTO = categoriaService.getAll();
        categorie = categoriaCrud.findAll();

        //testo che le liste risultanti non siano vuote
        assertNotNull(categoriaDTO);
        assertNotNull(categorie);

        //testo che le due liste siano uguali
        assertEquals(categoriaDTO.size(), categorie.size());

    }

    @Test
    public void testGetCategoria() {
        //inizializzo una categoria di test e la aggiungo nel db
        Categoria categoria = new Categoria();
        categoria.setNome("Altro");

        //prendo dal db l'id della categoria generato
        CategoriaDTO categoriaDTO = categoriaService.addCategoria(Categoria.to(categoria));
        final Long id = categoriaDTO.id;


        //testo che lanci l'eccezione in caso non gli passi nulla
        assertThrows(CategoriaException.class, () -> {
            categoriaService.getById(null);
        });

        //testo che lanci l'eccezione in caso non gli passi un id non presente
        assertThrows(CategoriaException.class, () -> {
            categoriaService.getById(82L);
        });

        //testo che ritorni un oggetto
        assertNotNull(categoriaService.getById(id));
        assertEquals("Altro", categoriaDTO.nome);
    }


}
