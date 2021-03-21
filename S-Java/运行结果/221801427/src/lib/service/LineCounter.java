package lib.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineCounter
{
    private static final String VALID_LINE_REGEX = "(^|\n)\\s*\\S+";
    private static final Pattern VALID_LINE_PATTERN = Pattern.compile(VALID_LINE_REGEX);

    /**
     * @param content
     * @return 有效行数
     */
    public static int countLine(String content)
    {
        int cnt = 0;
        Matcher matcher;

        matcher = VALID_LINE_PATTERN.matcher(content);
        while (matcher.find())
        {
            //利用正则表达式匹配有效行，空行不统计，包括\t \r \n 空格以及由他们组成的情况
            cnt++;
        }
        return cnt;
    }
}
