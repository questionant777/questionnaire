package ru.otus.spring.dao;

import java.io.IOException;

public interface QuestionDao {

    String[][] getDataInArray(char separator) throws IOException;

}
