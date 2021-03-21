package lib.service;

import java.util.HashMap;

public class CharAndWordCounter
{
    /**
     * @param content
     * @return 字符总数
     */
    public static int countChar(String content)
    {
        return content.length();// 直接返回字符串长度
    }

    /**
     * @param words
     * @return 单词总数
     */
    public static int countWord(HashMap<String, Integer> words)
    {
        return words.size();// 返回HashMap规模表示有效单词总数
    }
}
