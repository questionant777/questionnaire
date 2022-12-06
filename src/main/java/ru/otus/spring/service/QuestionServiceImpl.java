package ru.otus.spring.service;

import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.io.*;
import java.util.List;

import static ru.otus.spring.utils.QuestionUtils.loadFromInputStreamToDomain;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }


    @Override
    public void printQuestionsFromStream(char separator) throws IOException {

        List<Question> questionList =
                loadFromInputStreamToDomain(questionDao.getFileCsvInputStream(), separator);

        questionList.forEach(
                (question) -> {
                    System.out.println("Question: " + question.getQuestion());
                    System.out.println("Аnswer options: " + question.getAnswerOptions());
                }
        );

    }

}
