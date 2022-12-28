package ru.otus.spring.domain;

public class Question {
    private final String question;
    private final String rightAnswer;
    private String userAnswer;

    public Question(String question, String rightAnswer, String userAnswer) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.userAnswer = userAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }
}
