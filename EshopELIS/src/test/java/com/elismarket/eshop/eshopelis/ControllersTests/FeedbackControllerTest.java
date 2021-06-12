package com.elismarket.eshop.eshopelis.ControllersTests;

import com.elismarket.eshop.eshopelis.repository.FeedbackCrud;
import com.elismarket.eshop.eshopelis.service.interfaces.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FeedbackControllerTest {

    @Autowired
    FeedbackService feedbackService;

    @Autowired
    FeedbackCrud feedbackCrud;

    @BeforeEach
    public void init() {
        deleteDb();
    }

    public void deleteDb() {
        feedbackCrud.deleteAll();
    }

    @Test
    public void testAddFeedback() {


    }

    @Test
    public void testUpdateFeedback() {

    }

    @Test
    public void testDeleteFeedback() {

    }

    @Test
    public void testGetById() {


    }

    @Test
    public void testGetAll() {

    }

    @Test
    public void testGetAllByFeedback() {


    }
}
