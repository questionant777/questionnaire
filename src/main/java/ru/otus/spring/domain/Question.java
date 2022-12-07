package ru.otus.spring.domain;

public class Question {
    private final String question;
    private final String rightAnswer;

    public Question(String question, String rightAnswer) {
        this.question = question;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
