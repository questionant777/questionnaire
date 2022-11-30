package ru.otus.spring.service;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class QuestionServiceImpl implements QuestionService {

    private final InputStream fis;

    public QuestionServiceImpl(String fileName) {

        this.fis = this.getClass().getClassLoader()
                .getResourceAsStream(fileName);

    }

    @Override
    public void printQuestionsFromStream() {

        try (InputStreamReader isr = new InputStreamReader(fis,
                StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {

            br.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
