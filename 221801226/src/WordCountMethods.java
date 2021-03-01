import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WordCountMethods {
    //空白行的正则表达式
    private static String BLANK_LINE_REGEX = "^\\s*$";
    //非字母数字字符的正则表达式
    private static String UN_ALPHABET_NUM_REGEX = "[^0-9a-zA-Z]";
    //匹配以4个英文字母开头的正则表达式
    private static String FIRST_FOUR_APLH_REGEX = "^[a-z]{4,}.*";
    
    //记录文件内单词以及出现次数的TreeMap
    public static TreeMap<String, Integer> map = new TreeMap<>();
    
    /**
     * 统计文件的字符数
     * @param str 被校验的字符串
     * @return count 文件的字符数
     */
    public static int countChars(String str){
        int count = 0;
        char[] achar = str.toCharArray();
        
        for (int i = 0;i<achar.length;i++) {
            //统计ascii code
            if (33 <= achar[i] && achar[i] <= 126) {
                count++;
            }
            //统计空格，水平制表符，换行符
            else if (achar[i] == 32 || achar[i] == 9 || achar[i] == 10 || achar[i] == 13) {
                count++;
            }
        }
        return count;
    }

    /**
     * 统计文件的有效行数
     * @param filePath 文件路径
     * @return validLine 文件有效行数
     * @throws FileNotFoundException
     */
    public static int CountLines(String filePath){
        int validLine = 0;
        int allLine = 0;
        int blankLine = 0;
        try {
            BufferedReader br = null;
            InputStream inpStr = new FileInputStream(filePath);
            br = new BufferedReader(new InputStreamReader(inpStr));
            //空白行的正则匹配器
            Pattern blankLinePattern = Pattern.compile(BLANK_LINE_REGEX);
            String line = null;
            try {
                while ((line = br.readLine()) != null) {
                    if (blankLinePattern.matcher(line).find()) {
                        blankLine++;
                    }
                    allLine++;
                }
            } catch (IOException e) {
                System.out.println("读取错误...");
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException("ERROR:关闭文件输入流失败!");
                }
            }
            validLine = allLine-blankLine;
        } catch (FileNotFoundException e) {
            System.out.println("ERROR:未找到文件，请重试...");
            e.printStackTrace();
        }
        return validLine;
    }
    
    /**
     * 统计文件的单词总数
     * @param str 文件转出的字符串
     * @return words 文件单词总数
     */
    public static int countWords(String str) {
        int words = 0;
        
        String lowerStr = str.toLowerCase();
        Pattern pat = Pattern.compile(UN_ALPHABET_NUM_REGEX);
        Matcher mat = pat.matcher(lowerStr);
        lowerStr = mat.replaceAll(" ");
        String[] word = lowerStr.split("\\s+");
        for (int i = 0; i < word.length; i++) {
            String temp = word[i];
            if (temp.matches(FIRST_FOUR_APLH_REGEX)) {
                words++;
                if (!map.containsKey(temp)) {
                    map.put(temp, 1);
                } 
                else {
                    int num = map.get(temp);
                    map.put(temp, num + 1);
                }
            }
        }
        return words;
    }
    
    /**
     * 统计文件中各单词的出现次数
     * @param map 存储word以及出现次数的map键值对
     * @return list 存储出现频率最高的单词以及次数
     */
    
    public static List<Map.Entry<String, Integer>> highFreqWord(TreeMap<String, Integer> map){
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> word1, Map.Entry<String, Integer> word2) {
                    return word2.getValue() - word1.getValue();
            }
        });
        if (list.size() >= 10) {
            return list.subList(0, 10);
        } else {
            return list.subList(0, list.size());
        }
    }
}
