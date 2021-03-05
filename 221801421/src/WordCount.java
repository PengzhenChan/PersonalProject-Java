import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCount {
    private static String DIR = System.getProperty("user.dir");
    //空白行会成为两个\s*的一部分
    private static String LINE_REGEX = "\\s*\\S+\\s*\n";
    private static String SPLIT_REGEX = "[^a-zA-Z0-9]";
    private static String WORD_REGEX = "[a-zA-Z]{4,}[a-zA-Z0-9]*";

    private long charNum = 0L;
    private long lineNum = 0L;
    private long wordNum = 0L;

    //判断hashMap是否已经填充了
    private boolean flag = false;

    private HashMap<String, Integer> hashMap = new HashMap<>();

    public static String readFromFile(String filename) {
        BufferedReader reader = null;
        StringBuilder stringBuilder = null;
        int ch = 0;

        try{
            reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(DIR + "\\" + filename), StandardCharsets.UTF_8));
            stringBuilder = new StringBuilder();

            //利用StringBuilder拼接提速
            while ((ch = reader.read()) != -1){
                stringBuilder.append((char)ch);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("文件不存在！");
            e.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return stringBuilder.toString();
    }

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

    private long linesCount(String str) {
        long count = 0L;

        Matcher matcher = Pattern.compile(LINE_REGEX).matcher(str);
        while(matcher.find()){
            count++;
        }

        return count;
    }

    private Map<String, Integer> getTopK(String str,int k) {
        //可能有人不想要统计单词数, 直接就打算要词频TopK, 会导致hashmap还没填充, 所以需要一个flag
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

    public void testPrint(String str) {
        /*for(Map.Entry<String, Integer> entry : hashMap.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("输出完毕");*/
        Map<String, Integer> map = getTopK(str, 10);
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    //将计算模块的调用统一用一个方法封装作为暴露给外界的api
    public String getResult(String str, int k){
        charNum = charsCount(str);
        lineNum = linesCount(str);
        wordNum = wordsCount(str);
        Map<String, Integer> map = getTopK(str, k);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("characters: " + charNum + "\n")
                .append("lines: " + lineNum + "\n")
                .append("words: " + wordNum + "\n");

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            stringBuilder.append(entry.getKey() + ": " + entry.getValue() + "\n");
        }

        return stringBuilder.toString();
    }

    public void writeToFile(String filename, String str){
        BufferedWriter writer = null;

        try{
            //FileOutputStream若没有目标文件则会自己创建, 没有FileNotFound异常
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(DIR + "\\" + filename), StandardCharsets.UTF_8));

            writer.write(str);
            writer.flush();
            writer.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    //文件的输入输出到时候就放在wordCount类里,Lib就当做纯粹的一个处理字符串的模块.
    public static void main(String[] args) {
        WordCount wordCount = new WordCount();
        String str = readFromFile("input.txt");
        wordCount.writeToFile("output.txt", wordCount.getResult(str, 10));
    }
}
