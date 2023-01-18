package ru.otus.spring.service;

import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.stereotype.Component;
import ru.otus.spring.utils.HandleInOut;

@Component
@Profile("!test")
@ShellComponent
public class AppRunner {

    private final QuestionService questionService;
    private final HandleInOut handleInOut;

    public AppRunner(QuestionService questionService) {
        this.questionService = questionService;
        this.handleInOut = new HandleInOut();
    }

    @ShellMethod(value = "Start questionnaire command", key = {"sq", "start questionnaire"})
    public void run() {

        String result = questionService.processQuestionsFromDao();

        handleInOut.printLn(result);
    }

}