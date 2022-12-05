package ru.otus.spring.domain;

public class Question {
    private final String question;
    private final String answerOptions;

    public Question(String question, String answerOptions) {
        this.question = question;
        this.answerOptions = answerOptions;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswerOptions() {
        return answerOptions;
    }
}
