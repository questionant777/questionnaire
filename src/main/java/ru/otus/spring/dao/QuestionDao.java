package ru.otus.spring.dao;

import java.io.IOException;
import java.io.InputStreamReader;

public interface QuestionDao {

    InputStreamReader getInputStreamReader();

    String[][] getDataInArray(char separator) throws IOException;

}
