package ru.otus.spring.service;

import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.Scanner;

public interface QuestionService {

    String fillFirstLastName();

    String processQuestionsFromDao();

    boolean checkExamPass(int rightAnswerCountExamPass, int rightAnswerCount);

    int calcRightAnswerCount(List<Question> questionList);

    void fillAnswerByQuestions(List<Question> questionList);

    void resetSystemIn();
}
