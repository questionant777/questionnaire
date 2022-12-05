package ru.otus.spring.utils;

import ru.otus.spring.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionUtils {
    public static List<Question> loadFromInputStreamToDomain
            (InputStream inputStream, char separator) throws IOException {

        List<Question> questionList = new ArrayList<>();

        String[][] csvData = CsvUtils.parseCsvData(
                inputStream, separator, true, true);

        String questionStr;
        String answerOptionStr;

        for (int rowIdx = 0; rowIdx < csvData.length; rowIdx++)  {

            questionStr = "";
            answerOptionStr = "";

            for (int colIdx = 0; colIdx < csvData[rowIdx].length; colIdx++)  {

                if (colIdx == 0)
                    questionStr = csvData[rowIdx][colIdx];
                else
                    answerOptionStr += csvData[rowIdx][colIdx];

            }

            questionList.add(new Question(questionStr, answerOptionStr));
        }

        return questionList;
    }
}
