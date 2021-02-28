package lib.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineCounter
{
    private static String VALID_LINE_REGEX = "(^|\n)\\s*\\S+";
    private static Pattern VALID_LINE_PATTERN = Pattern.compile(VALID_LINE_REGEX);

    /**
     * @param content
     * @return ��Ч����
     */
    public static int countLine(String content)
    {
        int cnt = 0;
        Matcher matcher;

        matcher = VALID_LINE_PATTERN.matcher(content);
        while (matcher.find())
        {
            cnt++;
        }
        return cnt;
    }
}
