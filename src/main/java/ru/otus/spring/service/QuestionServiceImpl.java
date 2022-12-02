package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;

import java.io.*;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void printQuestionsFromStream(char separator) throws IOException {

        String[][] data = questionDao.getDataInArray(separator);

        for (int rowIdx = 0; rowIdx < data.length; rowIdx++)  {
            for (int colIdx = 0; colIdx < data[rowIdx].length; colIdx++)  {
                if (colIdx == 0)
                    System.out.print("Question: ");
                else if (colIdx == 1)
                    System.out.print("Аnswer options: ");
                System.out.println(data[rowIdx][colIdx]);
            }
        }
    }

}
