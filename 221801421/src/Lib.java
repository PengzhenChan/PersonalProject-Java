import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Lib类专职字符串的处理，使用一个字符串构造，作为独立的计算模块。
 */
public class Lib {
    //空白行会成为两个\\s*的一部分
    private static String LINE_REGEX = "\\s*\\S+\\s*\n";
    private static String SPLIT_REGEX = "[^a-zA-Z0-9]";
    private static String WORD_REGEX = "[a-zA-Z]{4,}[a-zA-Z0-9]*";

    private long charNum = 0L;
    private long lineNum = 0L;
    private long wordNum = 0L;

    //判断hashMap是否已经填充了
    private boolean flag = false;
    private HashMap<String, Integer> hashMap = new HashMap<>();
    private String str = "";

    public Lib(String str){
        this.str = str;
    }

    /**
     * 统计字符串的字符数
     * @param str 要统计的字符串
     * @return 字符串的字符数
     */
    private long charsCount(String str) {
        long count = 0L;

        //题目中说明了没给汉字,所以其实直接用str.length()也行
        char[] ch = str.toCharArray();
        for(int i=0;i<ch.length;i++){
            if(ch[i] <= 127){
                count++;
            }
        }

        return count;
    }

    /**
     * 统计字符串的有效行数
     * @param str 要统计的字符串
     * @return 字符串的有效行数
     */
    private long linesCount(String str) {
        long count = 0L;

        Matcher matcher = Pattern.compile(LINE_REGEX).matcher(str);
        while(matcher.find()){
            count++;
        }

        return count;
    }

    /**
     * 统计字符串的合法单词数
     * @param str 要统计的字符串
     * @return 字符串的合法单词数
     */
    private long wordsCount(String str) {
        long count = 0L;

        //为减少重复, 提高性能, 这里直接就在分割后直接存入map,否则词频统计时又要分割一遍单词.
        //当然, 这样也提高了耦合性, 只能说两种方式各有千秋.
        String[] temps = str.split(SPLIT_REGEX);
        for(int i=0;i<temps.length;i++){
            if(temps[i].matches(WORD_REGEX)){
                //空间换时间, 创建一个临时对象存储小写单词, 不然toLowerCase()两遍, 对于很长的单词会降低效率.
                String word = temps[i].toLowerCase();
                if(!hashMap.containsKey(word)){
                    hashMap.put(word, 1);
                }else{
                    hashMap.put(word, hashMap.get(word) + 1);
                }
                count++;
            }
        }
        flag = true;

        return count;
    }

    /**
     * 获得词频前k，词频相同则按字典序排序
     * @param k 要求返回的单词个数
     * @return 由k个key为单词，value为词频构成的已排序Map
     */
    private Map<String, Integer> getTopK(int k) {
        //可能以后改个需求, 不想要统计单词数, 直接就打算要词频TopK, 会导致hashmap还没填充, 所以需要一个flag
        if(!flag) wordsCount(str);

        //利用java8的stream进行排序性能更好,这里使用了Map中自带的比较器, 然后使用thenComparing实现同频词字典序排列
        Map<String, Integer> map = hashMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                        .thenComparing(Map.Entry.comparingByKey()))
                .limit(k)
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return map;
    }

    /**
     * 将题中要求的最终结果字符串作为返回值返回。
     * @param k 要求输出的单词个数
     * @return 题目要求的最终结果字符串
     */
    public String getResult(int k) {
        charNum = charsCount(str);
        lineNum = linesCount(str);
        wordNum = wordsCount(str);
        Map<String, Integer> map = getTopK(k);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("characters: " + charNum + "\n")
                .append("lines: " + lineNum + "\n")
                .append("words: " + wordNum + "\n");
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            stringBuilder.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }

        return stringBuilder.toString();
    }
}