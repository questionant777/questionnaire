package ru.otus.spring.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.spring.config.AppPropsQuestions;
import ru.otus.spring.dao.QuestionDao;
import ru.otus.spring.domain.Question;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceImplTest {

    @Mock
    QuestionDao questionDao;

    @Autowired
    MessageSource messageSource;

    private QuestionServiceImpl service;

    final InputStream systemIn = System.in;
    final PrintStream systemOut = System.out;

    @Before
    public void setUp() {

        AppPropsQuestions appPropsQuestions = new AppPropsQuestions();
        appPropsQuestions.setCsvFileName("test-questions.csv");
        appPropsQuestions.setCsvSeparator(",");
        appPropsQuestions.setRightAnswerCountExamPass("3");
        appPropsQuestions.setLocale(Locale.forLanguageTag("en"));

        service = new QuestionServiceImpl(appPropsQuestions, questionDao, messageSource);

        List<Question> allQuestionList = new ArrayList<>();
        allQuestionList.add(new Question("q1", "1", ""));
        allQuestionList.add(new Question("q2", "2", ""));
        allQuestionList.add(new Question("q3", "3", ""));
        allQuestionList.add(new Question("q4", "4", ""));
        allQuestionList.add(new Question("q5", "5", ""));

        when(questionDao.findAllQuestions())
                .thenReturn(allQuestionList);
    }

    @Test
    public void fillFirstLastNameTest() {
        inputConsole("Ivan\nIvanov\n");

        Scanner scanner = new Scanner(System.in);

        String result = service.fillFirstLastName(scanner);

        closeConsole();

        assertThat(result).isEqualTo("Ivan Ivanov");
    }

    @Test
    public void checkExamPassTest() {
        assertThat(service.checkExamPass(3, 5)).isTrue();
        assertThat(service.checkExamPass(3, 1)).isFalse();
    }

    @Test
    public void calcRightAnswerCountTest() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question("q1", "1", "1"));
        questionList.add(new Question("q2", "2", "1"));
        questionList.add(new Question("q3", "3", "3"));

        int result = service.calcRightAnswerCount(questionList);

        assertThat(result).isEqualTo(2);
    }

    @Test
    public void fillAnswerByQuestionsTest() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question("q1", "1", ""));
        questionList.add(new Question("q2", "2", ""));

        inputConsole("test1\ntest2\n");

        Scanner scanner = new Scanner(System.in);

        service.fillAnswerByQuestions(scanner, questionList);

        closeConsole();

        assertThat(questionList.get(0).getUserAnswer()).isEqualTo("test1");
        assertThat(questionList.get(1).getUserAnswer()).isEqualTo("test2");
    }

    @Test
    public void processQuestionsFromDaoPassTest() {

        inputConsole("Ivan\nIvanov\n1\n2\n3\n4\n5\n");

        String result = service.processQuestionsFromDao();

        closeConsole();

        assertThat(result).isEqualTo("Result: Ivan Ivanov exam is  passed, RightAnswerCount: 5 from TotalQuestionCount: 5");
    }

    @Test
    public void processQuestionsFromDaoNotPassTest() {

        inputConsole("Ivan\nIvanov\n1\n1\n1\n1\n5\n");

        String result = service.processQuestionsFromDao();

        closeConsole();

        assertThat(result).isEqualTo("Result: Ivan Ivanov exam is NOT passed, RightAnswerCount: 2 from TotalQuestionCount: 5");
    }

    private void inputConsole(String s) {
        System.setIn(new ByteArrayInputStream(s.getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(byteArrayOutputStream);
        System.setOut(ps);
    }

    private void closeConsole() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

}