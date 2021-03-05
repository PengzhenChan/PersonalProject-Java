import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
    private static String DIR = System.getProperty("user.dir");
    //空白行会成为两个\s*的一部分
    private static String LINE_REGEX = "\\s*\\S+\\s*\n";
    private static String SPLIT_REGEX = "[^a-zA-Z0-9]";
    private static String WORD_REGEX = "[a-zA-Z]{4,}[a-zA-Z0-9]*";

    private long charNum = 0L;
    private long lineNum = 0L;
    private long wordNum = 0L;

    private HashMap<String, Integer> hashMap = new HashMap<>();

    public static String readFromFile(String filename){
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

    public long charsCount(String str){
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

    public long wordsCount(String str){
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

        return count;
    }

    public long linesCount(String str){
        long count = 0L;

        Matcher matcher = Pattern.compile(LINE_REGEX).matcher(str);
        while(matcher.find()){
            count++;
        }
        return count;
    }

    public void testPrint(){
        for(Map.Entry<String, Integer> entry : hashMap.entrySet()){
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("输出完毕");
    }

    public static void main(String[] args){
        WordCount wordCount = new WordCount();
        String str = readFromFile("input.txt");
        //System.out.println(wordCount.charsCount(str));
        //System.out.println(wordCount.linesCount(str));
        System.out.println(wordCount.wordsCount(str));
        wordCount.testPrint();
    }
}
