package ru.otus.spring.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.otus.spring.dao.QuestionDao;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Mock
    QuestionDao questionDao;

    QuestionServiceImpl service;

    @Before
    public void setUp() throws Exception {
        service = new QuestionServiceImpl(questionDao);

        ArrayList<String[]> data = new ArrayList<String[]>();
        ArrayList<String> row = new ArrayList<String>();

        row.add("test11,test12");

        data.add(row.toArray(new String[0]));

        when(questionDao.getDataInArray(','))
                .thenReturn(data.toArray(new String[0][]));
    }

    @Test
    public void printQuestionsFromStreamTest() throws IOException {
        service.printQuestionsFromStream(',');
        assertTrue(true);
    }
}