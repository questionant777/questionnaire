package ru.otus.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Locale;

@ConfigurationProperties(prefix = "questions")
@Configuration
public class AppPropsQuestions {
    private String csvFileName;
    private String csvSeparator;
    private String rightAnswerCountExamPass;
    private Locale locale;

    public String getCsvFileName() {
        return csvFileName;
    }

    public void setCsvFileName(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public String getCsvSeparator() {
        return csvSeparator;
    }

    public void setCsvSeparator(String csvSeparator) {
        this.csvSeparator = csvSeparator;
    }

    public String getRightAnswerCountExamPass() {
        return rightAnswerCountExamPass;
    }

    public void setRightAnswerCountExamPass(String rightAnswerCountExamPass) {
        this.rightAnswerCountExamPass = rightAnswerCountExamPass;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

}
