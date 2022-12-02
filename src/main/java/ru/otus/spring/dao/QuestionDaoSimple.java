package ru.otus.spring.dao;

import ru.otus.spring.utils.CsvUtils;

import java.io.IOException;
import java.io.InputStream;

public class QuestionDaoSimple implements QuestionDao {

    private final InputStream fis;

    public QuestionDaoSimple(String fileNameCsv) {

        this.fis = this.getClass().getClassLoader()
                .getResourceAsStream(fileNameCsv);

    }

    @Override
    public String[][] getDataInArray(char separator) throws IOException {
        return CsvUtils.parseCsvData(fis, separator, true, true);
    }

}
