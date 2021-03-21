package lib.service;

import java.util.*;
import java.util.regex.Pattern;

public class StringAnalyser
{
    private static final String FILTER_REGEX = "[^0-9A-Za-z]";
    private static final String VALID_WORD_REGEX = "[a-z]{4}[0-9a-z]*";

    /**
     * @param content
     * @return words
     */
    public static HashMap<String, Integer> analyseString(String content)
    {
        int cnt; // 临时变量用于计入键值（单词出现次数）
        String word;

        HashMap<String, Integer> words = new HashMap<String, Integer>();
        StringTokenizer tokenizer = new StringTokenizer(content.replaceAll(FILTER_REGEX, " "));
        // 先将分隔符全替换为空格，再利用 StringTokenizer 切分单词

        while (tokenizer.hasMoreTokens())
        {
            word = tokenizer.nextToken(" ");
            if (Pattern.matches(VALID_WORD_REGEX, word))
            {
                // 利用正则表达式统计有效字符:至少有四位且都为字母，后跟若干字母或数字，不区分大小写，计入HashMap。
                if (words.containsKey(word))
                {
                    // 单词已统计到过
                    cnt = words.get(word);
                    words.put(word, cnt + 1);
                }
                else
                {
                    // 单词初次统计到
                    words.put(word, 1);
                }
            }
        }
        return words;
    }
}
