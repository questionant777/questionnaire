package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.QuestionService;

import java.io.IOException;

@Configuration
@ComponentScan
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        QuestionService service = context.getBean(QuestionService.class);

        try {
            service.replyOnQuestionsFromStream(',');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
