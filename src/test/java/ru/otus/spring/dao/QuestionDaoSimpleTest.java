package ru.otus.spring.dao;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionDaoSimpleTest {

    private QuestionDao service;

    @Before
    public void setUp() {
        service = new QuestionDaoSimple("test-questions.csv", ',');
    }

    @Test
    public void findAllQuestionsTest() {
        assertThat(service.findAllQuestions().size()).isEqualTo(5);
    }
}