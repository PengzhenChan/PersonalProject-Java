import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Lib
{
    private String text;
    private int chars;
    private int words;
    private int lines;
    private Map<String,Integer> maxCntWords;
    boolean isDoHandleWordsFunc = false;   //标识handleWord函数是否被运行过

    /*
    构造函数
    传入参数为从文件读取的数据
     */
    public Lib(String text)
    {
        this.text = text;
    }


    /*
    获取行数
     */
    int getLines()
    {
        Pattern linePattern = Pattern.compile("(^|\n)\\s*\\S+");   //行数匹配正则表达式
        lines = 0;   //行数置类
        Matcher matcher = linePattern.matcher(text);   //匹配
        while (matcher.find()) {
            lines++;
        }
        return lines;
    }


    /*
    返回字符数
     */
    int getChars()
    {
        chars = text.length();
        return chars;
    }


    /*
    获取单词总数
     */
    int getWords()
    {
        if(!isDoHandleWordsFunc)
            handleWords();
        isDoHandleWordsFunc = true;
        return words;
    }


    /*
    获取频率最高的10个词
    返回 map
     */
    Map<String,Integer> getMaxCntWords()
    {
        if(!isDoHandleWordsFunc)
            handleWords();
        isDoHandleWordsFunc = true;
        return maxCntWords;
    }


    /*
    辅助函数，handleWords
    因为统计单词数目和词频一并进行
     */
    void handleWords()
    {
        words = 0;
        //博客中的word要求：至少以4个英文字母开头，跟上字母数字符号
        Pattern wordPattern = Pattern.compile("(^|[^A-Za-z0-9])([a-zA-Z]{4}[a-zA-Z0-9]*)");
        //Pattern wordPattern = Pattern.compile("(^|\\p{P}|\\s)([a-zA-Z]{4}[a-zA-Z0-9]*)");

        Matcher matcher = wordPattern.matcher(text);   //匹配
        maxCntWords = new HashMap<String, Integer>();   //初始化Map
        while(matcher.find())
        {
            words++;   //单词数+1
            String midWord = matcher.group(2).toString().toLowerCase();  //取出匹配的word,并统一转化为小写
            Integer wordsCnt = maxCntWords.get(midWord);
            if(wordsCnt == null)   //word在文中第一次出现
            {
                maxCntWords.put(midWord, 1);
            }
            else  //非第一次出现
            {
                maxCntWords.put(midWord, wordsCnt + 1);   //在原本计数上+1
            }
        }

        //通过stream实现对map排序并取前10个
        maxCntWords =
                maxCntWords
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer> comparingByValue() //按值降序
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey()))//按key字典序升序
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }





}
