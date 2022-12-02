package ru.otus.spring.service;

import java.io.IOException;

public interface QuestionService {

    void printQuestionsFromStream(char separator) throws IOException;
}
