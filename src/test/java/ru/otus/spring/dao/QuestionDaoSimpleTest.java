package ru.otus.spring.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.config.AppPropsQuestions;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionDaoSimpleTest {

    private QuestionDao service;

    @Before
    public void setUp() {
        AppPropsQuestions appPropsQuestions = new AppPropsQuestions();
        appPropsQuestions.setCsvFileName("test-questions.csv");
        appPropsQuestions.setCsvSeparator(",");
        appPropsQuestions.setLocale(Locale.forLanguageTag("ru_RU"));

        service = new QuestionDaoSimple(appPropsQuestions);
    }

    @Test
    public void findAllQuestionsTest() {
        assertThat(service.findAllQuestions().size()).isEqualTo(5);
    }
}