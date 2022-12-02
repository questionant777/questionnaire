package ru.otus.spring.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.otus.spring.dao.QuestionDao;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    QuestionDao questionDao;

    QuestionServiceImpl service;

    @Before
    public void setUp() throws Exception {
        service = new QuestionServiceImpl(questionDao);

        when(questionDao.getInputStreamReader())
                .thenReturn(new InputStreamReader(
                        new ByteArrayInputStream("test test test\ntest test test".getBytes()), StandardCharsets.UTF_8));
    }

    @Test
    public void printQuestionsFromStreamTest() {
        service.printQuestionsFromStream();
        assertTrue(true);
    }
}
