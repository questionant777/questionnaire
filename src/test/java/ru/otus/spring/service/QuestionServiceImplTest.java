package ru.otus.spring.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionServiceImplTest {

    private QuestionServiceImpl service;

    @Before
    public void setUp() throws Exception {
        service = new QuestionServiceImpl("test-questions.csv");
    }

    @Test
    public void printQuestionsFromStream() {
        service.printQuestionsFromStream();
        assertTrue(true);
    }
}