package ru.otus.spring.service;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.AppPropsQuestions;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

@Service
@Primary
public class QuestionServiceImpl implements QuestionService {

    private final AppPropsQuestions appPropsQuestions;

    private final QuestionDao questionDao;

    private final MessageSource messageSource;

    public QuestionServiceImpl(
            AppPropsQuestions appPropsQuestions,
            QuestionDao questionDao,
            MessageSource messageSource)
    {
        this.appPropsQuestions = appPropsQuestions;
        this.questionDao = questionDao;
        this.messageSource = messageSource;
    }

    @Override
    public String fillFirstLastName(Scanner scanner) {

        System.out.print(messageSource.getMessage("enter.your.first.name", null, appPropsQuestions.getLocale()) + " ");
        String firstName = scanner.nextLine().trim();

        System.out.print(messageSource.getMessage("enter.your.last.name", null, appPropsQuestions.getLocale()) + " ");
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

        String questionLocale = messageSource.getMessage("question", null, appPropsQuestions.getLocale());

        String entYourAnswerLocale = messageSource.getMessage("enter.your.answer", null, appPropsQuestions.getLocale());

        for (Question question : questionList) {
            System.out.println(questionLocale
                    + " "
                    + messageSource.getMessage("how.much", new String[]{question.getQuestion()}, appPropsQuestions.getLocale()));
            System.out.print  (entYourAnswerLocale + " ");
            question.setUserAnswer(scanner.nextLine().trim());
        }
    }

    @Override
    public String processQuestionsFromDao() {

        Scanner scanner = new Scanner(System.in);

        String firstLastName = fillFirstLastName(scanner);

        // кол-во правильных ответов для успешного зачета из настройки
        int rightAnswerCountExamPass = 0;

        try {
            rightAnswerCountExamPass = parseInt(appPropsQuestions.getRightAnswerCountExamPass());
        } catch (Exception ignored) {
            //
        }

        List<Question> questionList = questionDao.findAllQuestions();

        fillAnswerByQuestions(scanner, questionList);

        // счетчик правильных ответов
        int rightAnswerCount = calcRightAnswerCount(questionList);

        String notLocale = messageSource.getMessage("not", null, appPropsQuestions.getLocale());

        return  messageSource.getMessage("result",
                        new String[]{
                                firstLastName,
                                (checkExamPass(rightAnswerCountExamPass, rightAnswerCount) ? "" : notLocale),
                                String.valueOf(rightAnswerCount),
                                String.valueOf(questionList.size())
                        },
                        appPropsQuestions.getLocale()
                );
    }
}
