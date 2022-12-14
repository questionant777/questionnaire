package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.service.QuestionService;

@Configuration
@ComponentScan("ru.otus")
public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        QuestionService service = context.getBean(QuestionService.class);

        System.out.println(service.processQuestionsFromDao());

    }
}
