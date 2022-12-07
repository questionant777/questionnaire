package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.math.NumberUtils;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.io.*;
import java.util.List;
import java.util.Scanner;

import static ru.otus.spring.utils.QuestionUtils.loadFromInputStreamToDomain;

@Service
@PropertySource("classpath:application.properties")
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    private final String rightAnswerCountExamPassStr;

    public QuestionServiceImpl(
            QuestionDao questionDao,
            @Value("${right.answer.count.exam.pass}") String rightAnswerCountExamPassStr) {
        this.questionDao = questionDao;
        this.rightAnswerCountExamPassStr = rightAnswerCountExamPassStr;
    }

    @Override
    public void replyOnQuestionsFromStream(char separator) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine().trim();

        // кол-во правильных ответов для успешного зачета из настройки
        int rightAnswerCountExamPass = NumberUtils.toInt(rightAnswerCountExamPassStr, 0);

        // счетчик правильных ответов
        int rightAnswerCount = 0;

        List<Question> questionList =
                loadFromInputStreamToDomain(questionDao.getFileCsvInputStream(), separator);

        for (Question question : questionList) {
            System.out.println("Question: " + question.getQuestion());
            System.out.print  ("Enter your answer: ");
            String userAnswerOption = scanner.nextLine().trim();

            if (question.getRightAnswer().equals(userAnswerOption))
                rightAnswerCount++;
        }

        String result = "Result: " +
                firstName +
                " " +
                lastName +
                " exam is " +
                (rightAnswerCountExamPass > 0 && rightAnswerCount < rightAnswerCountExamPass ? "NOT " : "") +
                "passed" +
                ", RightAnswerCount: " +
                rightAnswerCount +
                " from TotalQuestionCount: " +
                questionList.size();

        System.out.println(result);

    }

}
