package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.controller.CategoriaController;
import com.elismarket.eshop.eshopelis.dto.CategoriaDTO;
import com.elismarket.eshop.eshopelis.exception.CategoriaException;
import com.elismarket.eshop.eshopelis.model.Categoria;
import com.elismarket.eshop.eshopelis.repository.CategoriaCrud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class CategoriaControllerTest {



    @Autowired
    CategoriaController categoriaController;

    @Autowired
    CategoriaCrud categoriaCrud;

    @BeforeEach
    public void init(){deleteDb();};


    @Test
    public void testAddCategoria() {


    }

    @Test
    public void testUpdateCategoria() {

    }

    @Test
    public void testDeleteCategoria() {

    }

    @Test
    public void testGetAllCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("pizza");

        List<CategoriaDTO> categoriaDTO=null;
        List<Categoria> categorie = null;

        categorie.add(categoria);
        categoriaDTO.add(Categoria.to(categoria));


        categoriaDTO = categoriaController.getAllCategoria();
        categorie = categoriaCrud.findAll();
        assertNotNull(categoriaDTO);
        assertNotNull(categorie);
        assertEquals(categoriaDTO.size(), categorie.size());


    }

    @Test
    public void testGetCategoria() {

    }

    public void deleteDb(){
        categoriaCrud.deleteAll();
    }


}
