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
import org.springframework.http.ResponseEntity;

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
    public void init(){deleteDb();};


    @Test
    public void testAddCategoria() {
        //inizializzo una categoria di test e la aggiungo nel db
        Categoria categoria = new Categoria();
        categoria.setNome("pizza");
        categoria.setId(1l);
        List<CategoriaDTO> categoriaDTO=  new ArrayList<>();
        categoriaDTO.add(Categoria.to(categoria));

        //testo che il metodo funzioni
        assertNotNull( categoriaService.addCategoria(categoriaDTO.get(0)));
        assertTrue(categoriaService.getById(categoriaService.addCategoria(Categoria.to(categoria)).id) instanceof CategoriaDTO);

        //testo che lanci l'eccezione con un oggetto vuoto
        assertThrows(CategoriaException.class, ()->{
            categoriaService.addCategoria(null);
        });
    }

    @Test
    public void testUpdateCategoria() {
        //inizializzo una categoria di test e la aggiungo nel db
        Categoria categoria = new Categoria();
        categoria.setNome("pizza");
        categoria.setId(1l);
        //prendo dal db l'id della categoria generato
        List<CategoriaDTO> categoriaDTO=  new ArrayList<>();
        categoriaDTO.add(Categoria.to(categoria));
        //prendo dal db l'id della categoria generato


        //testo che ritorni un oggetto
        assertNotNull(categoriaService.updateCategoria(1l,Categoria.to(categoria)));

        //testo che lanci l'eccezione in caso non gli passi nulla
        assertThrows(CategoriaException.class, ()->{
            categoriaService.updateCategoria(null,null);
        });

        //testo che lanci l'eccezione in caso non gli passi un id non presente
        assertThrows(CategoriaException.class, ()->{
            categoriaService.updateCategoria(2L,categoriaDTO.get(0));
        });
        //testo che lanci l'eccezione in caso non gli passi un oggetto nullo come categoriaDTO
        assertThrows(CategoriaException.class, ()->{
            categoriaService.updateCategoria(1l,null);
        });




    }

    @Test
    public void testDeleteCategoria() {
        //inizializzo una categoria di test e la aggiungo nel db
        Categoria categoria = new Categoria();
        categoria.setNome("pizza");
        long id = categoriaService.addCategoria(Categoria.to(categoria)).id;
        //prendo dal db l'id della categoria generato


        //testo che la funzione lanci un eccezione con un id non corretto
        assertThrows(CategoriaException.class, ()->{
            categoriaService.deleteCategoria(5l);
        });

        //testo che la funzione lanci un eccezione con un id nullo
        assertThrows(CategoriaException.class, ()->{
            categoriaService.deleteCategoria(null);
        });


        //testo che la funzione dato un id
        assertTrue(categoriaService.deleteCategoria(id));
    }

    @Test
    public void testGetAllCategoria() {
        //inizializzo una categoria di test e la aggiungo nel db
        Categoria categoria = new Categoria();
        categoria.setNome("pizza");
        List<CategoriaDTO> categoriaDTO=  new ArrayList<>();
        List<Categoria> categorie =new ArrayList<>();
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
        categoria.setNome("pizza");
        categoriaService.addCategoria(Categoria.to(categoria));
        //prendo dal db l'id della categoria generato
        long id= categoriaService.getAll().get(0).id;

        //testo che ritorni un oggetto
        assertNotNull(categoriaService.getById(id));

        //testo che lanci l'eccezione in caso non gli passi nulla
        assertThrows(CategoriaException.class, ()->{
            categoriaService.getById(null);
        });

        //testo che lanci l'eccezione in caso non gli passi un id non presente
        assertThrows(CategoriaException.class, ()->{
            categoriaService.getById(82L);
        });


    }

    public void deleteDb(){
        categoriaCrud.deleteAll();

    }


}
