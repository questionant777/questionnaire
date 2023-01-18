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

//    @Value("${spring.shell.interactive.enabled}")
//    private static Boolean shellInteractiveEnabled;

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

//        System.out.println("main");
//        System.out.println(shellInteractiveEnabled);
//
//        if (!shellInteractiveEnabled) {
//            QuestionService service = context.getBean(QuestionService.class);
//            HandleInOut out = context.getBean(HandleInOut.class);
//
//            String result = service.processQuestionsFromDao();
//
//            out.printLn(result);
//        }
    }
}
