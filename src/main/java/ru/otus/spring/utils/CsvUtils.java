package ru.otus.spring.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CsvUtils {
    public static String[][] parseCsvData(InputStream is, char separator, boolean trimValues, boolean skipEmptyLines)
            throws IOException
    {
        ArrayList<String[]> data = new ArrayList<String[]>();
        ArrayList<String> row = new ArrayList<String>();
        StringBuffer value = new StringBuffer();
        int ch = -1;
        int prevCh = -1;
        boolean inQuotedValue = false;
        boolean quoteAtStart = false;
        boolean rowIsEmpty = true;
        boolean isEOF = false;

        while (true)
        {
            prevCh = ch;
            ch = (isEOF) ? -1 : is.read();

            // Handle carriage return line feed
            if (prevCh == '\r' && ch == '\n')
            {
                continue;
            }
            if (inQuotedValue)
            {
                if (ch == -1)
                {
                    inQuotedValue = false;
                    isEOF = true;
                }
                else
                {
                    value.append((char)ch);

                    if (ch == '"')
                    {
                        inQuotedValue = false;
                    }
                }
            }
            else if (ch == separator || ch == '\r' || ch == '\n' || ch == -1)
            {
                // Add the value to the row
                String s = value.toString();

                if (quoteAtStart && s.endsWith("\""))
                {
                    s = s.substring(1, s.length() - 1);
                }
                if (trimValues)
                {
                    s = s.trim();
                }
                rowIsEmpty = (s.length() > 0) ? false : rowIsEmpty;
                row.add(s);
                value.setLength(0);

                if (ch == '\r' || ch == '\n' || ch == -1)
                {
                    // Add the row to the result
                    if (!skipEmptyLines || !rowIsEmpty)
                    {
                        data.add(row.toArray(new String[0]));
                    }
                    row.clear();
                    rowIsEmpty = true;

                    if (ch == -1)
                    {
                        break;
                    }
                }
            }
            else if (prevCh == '"')
            {
                inQuotedValue = true;
            }
            else
            {
                if (ch == '"')
                {
                    inQuotedValue = true;
                    quoteAtStart = value.length() == 0;
                }
                value.append((char)ch);
            }
        }
        return data.toArray(new String[0][]);
    }

}
