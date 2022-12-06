package ru.otus.spring.dao;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionDaoSimpleTest {

    private QuestionDao service;

    @Before
    public void setUp() {
        service = new QuestionDaoSimple("test-questions.csv");
    }

    @Test
    public void getFileCsvInputStream() {
        assertNotNull(service.getFileCsvInputStream());
    }
}