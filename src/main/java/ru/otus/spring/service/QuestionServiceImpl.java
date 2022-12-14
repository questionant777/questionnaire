package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.math.NumberUtils;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.Scanner;

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
    public String fillFirstLastName(Scanner scanner) {
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine().trim();

        return firstName + " " + lastName;
    }

    @Override
    public boolean checkExamPass(int rightAnswerCountExamPass, int rightAnswerCount) {
        return rightAnswerCountExamPass == 0 || rightAnswerCount >= rightAnswerCountExamPass;
    }

    @Override
    public int calcRightAnswerCount(List<Question> questionList) {
        return (int) questionList.stream()
                .filter(q -> q.getRightAnswer().equals(q.getUserAnswer())).count();
    }

    @Override
    public void fillAnswerByQuestions(Scanner scanner, List<Question> questionList) {
        for (Question question : questionList) {
            System.out.println("Question: " + question.getQuestion());
            System.out.print  ("Enter your answer: ");
            question.setUserAnswer(scanner.nextLine().trim());
        }
    }

    @Override
    public String processQuestionsFromDao() {
        Scanner scanner = new Scanner(System.in);

        String firstLastName = fillFirstLastName(scanner);

        // кол-во правильных ответов для успешного зачета из настройки
        int rightAnswerCountExamPass = NumberUtils.toInt(rightAnswerCountExamPassStr, 0);

        List<Question> questionList = questionDao.findAllQuestions();

        fillAnswerByQuestions(scanner, questionList);

        // счетчик правильных ответов
        int rightAnswerCount = calcRightAnswerCount(questionList);

        String result = "Result: " +
                firstLastName +
                " exam is " +
                (checkExamPass(rightAnswerCountExamPass, rightAnswerCount) ? "" : "NOT ") +
                "passed" +
                ", RightAnswerCount: " +
                rightAnswerCount +
                " from TotalQuestionCount: " +
                questionList.size();

        return result;

    }
}
