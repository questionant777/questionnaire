package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.config.AppPropsQuestions;
import ru.otus.spring.service.QuestionService;

@SpringBootApplication
@EnableConfigurationProperties(AppPropsQuestions.class)
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class, args);

        QuestionService service = context.getBean(QuestionService.class);

        System.out.println(service.processQuestionsFromDao());
    }
}
