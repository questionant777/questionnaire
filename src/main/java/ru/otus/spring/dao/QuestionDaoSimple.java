package ru.otus.spring.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@PropertySource("classpath:application.properties")
public class QuestionDaoSimple implements QuestionDao {

    private final InputStream fileCsvInputStream;

    public QuestionDaoSimple(@Value("${questions.filenamecsv}") String fileNameCsv) {

        this.fileCsvInputStream = this.getClass().getClassLoader()
                .getResourceAsStream(fileNameCsv);

    }

    @Override
    public InputStream getFileCsvInputStream() {
        return fileCsvInputStream;
    }

}
