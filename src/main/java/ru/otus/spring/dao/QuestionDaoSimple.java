package ru.otus.spring.dao;

import java.io.InputStream;

public class QuestionDaoSimple implements QuestionDao {

    private final InputStream fileCsvInputStream;

    public QuestionDaoSimple(String fileNameCsv) {

        this.fileCsvInputStream = this.getClass().getClassLoader()
                .getResourceAsStream(fileNameCsv);

    }

    @Override
    public InputStream getFileCsvInputStream() {
        return fileCsvInputStream;
    }

}
