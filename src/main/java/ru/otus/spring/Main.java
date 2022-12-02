package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");

        QuestionService service = context.getBean(QuestionService.class);

        try {
            service.printQuestionsFromStream(',');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
