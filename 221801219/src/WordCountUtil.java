import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCountUtil {
    /**
     * 读取文件并统计字符数
     * @param filePath: 文件路径
     * @return: 文件内容
     */
    public static String readFileByChars(String filePath) {
        File file = new File(filePath);
        Reader reader = null;
        //统计字符数
        int cnt = 0;
        //存储文件内容
        StringBuilder fileContent = new StringBuilder();
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int temp = -1;
            while ((temp = reader.read()) != -1) {
                if (((char) temp) != '\r') {
                    fileContent.append((char) temp);
                    //不考虑汉字
                    if(temp < 128) {
                        cnt++;
                    }
                }
            }
            reader.close();
            System.out.println(fileContent.toString());
            System.out.println("共有字符数:" + cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    public static void countWords(String str)
    {
        String pattern = "(^|[^A-Za-z0-9])([a-zA-Z]{4}[a-zA-Z0-9]*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(str);
        int cnt = 0;
        HashMap<String, Integer> wordsCount = new HashMap<String, Integer>();
        String temp;
        while (m.find( )) {
            cnt++;
            temp = m.group(2).toString().toLowerCase();
            wordsCount.merge(temp, 1, Integer::sum);
        }

            for(String obj : wordsCount.keySet()) {
                Integer value = wordsCount.get(obj);
                System.out.println(obj + " : " + value);
            }
    }

}


