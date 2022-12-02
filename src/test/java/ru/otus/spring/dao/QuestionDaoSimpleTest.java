package ru.otus.spring.dao;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class QuestionDaoSimpleTest {

    private QuestionDao service;

    @Before
    public void setUp() throws Exception {
        service = new QuestionDaoSimple("test-questions.csv");
    }

    @Test
    public void getInputStreamReaderTest() throws IOException {
        String[][] data = service.getDataInArray(',');
        assertEquals(5, data.length);
    }
}