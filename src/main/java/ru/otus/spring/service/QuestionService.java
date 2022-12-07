package ru.otus.spring.service;

import java.io.IOException;

public interface QuestionService {

    void replyOnQuestionsFromStream(char separator) throws IOException;
}
