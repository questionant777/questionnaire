package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;

import java.io.*;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {

        this.questionDao = questionDao;

    }

    @Override
    public void printQuestionsFromStream() {

        try (InputStreamReader isr = questionDao.getInputStreamReader()) {

            BufferedReader br = new BufferedReader(isr);

            br.lines()
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        } ;
    }

}
