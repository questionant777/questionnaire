package ru.otus.spring.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.config.AppPropsQuestions;
import ru.otus.spring.domain.Question;
import ru.otus.spring.utils.CsvUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionDaoSimple implements QuestionDao {

    private final List<Question> questionList = new ArrayList<>();

    public QuestionDaoSimple(AppPropsQuestions appPropsQuestions) {

        InputStream fileCsvInputStream = this.getClass().getClassLoader().getResourceAsStream(appPropsQuestions.getCsvFileName());

        char separator = appPropsQuestions.getCsvSeparator().charAt(0);

        String[][] csvData = new String[0][];

        try {
            csvData = CsvUtils.parseCsvData(
                    fileCsvInputStream, separator, true, true);
        } catch (IOException ioException) {
            System.out.println("Raise an error for read question operation: " + ioException);
        }

        try {
            fileCsvInputStream.close();
        } catch (IOException ioException) {
            System.out.println("Raise an error for close question operation: " + ioException);
        }

        String questionStr;
        String rightAnswerStr;

        for (int rowIdx = 0; rowIdx < csvData.length; rowIdx++)  {

            questionStr = "";
            rightAnswerStr = "";

            for (int colIdx = 0; colIdx < csvData[rowIdx].length; colIdx++)  {

                if (colIdx == 0)
                    questionStr = csvData[rowIdx][colIdx];
                else
                    rightAnswerStr += csvData[rowIdx][colIdx];

            }

            questionList.add(new Question(questionStr, rightAnswerStr, ""));
        }

    }

    @Override
    public List<Question> findAllQuestions() {
        return this.questionList;
    }
}
