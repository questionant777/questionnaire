package ru.otus.spring.dao;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class QuestionDaoSimple implements QuestionDao {

    private final InputStream fis;

    public QuestionDaoSimple(String fileNameCsv) {

        this.fis = this.getClass().getClassLoader()
                .getResourceAsStream(fileNameCsv);

    }

    @Override
    public InputStreamReader getInputStreamReader() {

        return new InputStreamReader(fis, StandardCharsets.UTF_8);

    }

}
