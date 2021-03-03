import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 作者： 221801229 Shy
 * 功能： 词频统计的功能函数聚集类
 */
public class Lib
{
    public static String ENCODING = "UTF-8";

    //统计文件字符数
    public int charCount(File file) throws IOException, FileNotFoundException
    {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), ENCODING);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int charnum = 0;
        String str = null;

        while ((str = bufferedReader.readLine()) != null)
        {
            charnum += str.length();
        }
        inputStreamReader.close();
        return charnum;
    }

    //行数计数器
    public int lineCount(File file) throws Exception, FileNotFoundException
    {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), ENCODING);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int linenum = 0;
        String str = null;

        while ((str = bufferedReader.readLine()) != null)
        {
            //删除所有空白字符
            str.trim();
            if (!str.isEmpty())
            {
                linenum ++;
            }
        }
        inputStreamReader.close();
        return linenum;
    }

    //单词数统计
    public int wordsCount(File file) throws Exception
    {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), ENCODING);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int wordsnum = 0;
        String str = null;
        Map<String, Integer> map = new HashMap<String,Integer>();

        while ((str = bufferedReader.readLine()) != null) {
            //String s = bufferedReader.readLine();
            String splited[] = str.split(",|\\.| |\\?|\\!|\\'");
            for (int i = 0; i < splited.length; i++) {
                if (splited[i].length() >= 4 ) {
                    String temp = splited[i].substring(0, 4);
                    temp = temp.replaceAll("[^a-zA-Z]", "");
                    if (temp.length() >= 4) {
                        if (map.containsKey(splited[i].toLowerCase())) {
                            map.put(splited[i].toLowerCase(), map.get(splited[i].toLowerCase())+1);
                        }
                        else {
                            map.put(splited[i].toLowerCase(), 1);
                        }
                    }
                }
            }
        }

        wordsnum = map.size();

        //System.out.println("words:"+wordsnum);
        inputStreamReader.close();
        return wordsnum;
    }

    //单词频数统计
    public List<Map.Entry<String, Integer>> wordsNumCount(File file) throws Exception, FileNotFoundException
    {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), ENCODING);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        Map<String, Integer> map = new HashMap<String,Integer>();
        String str = null;

        while ((str = bufferedReader.readLine()) != null) {
            //String s = bufferedReader.readLine();
            String splited[] = str.split(",|\\.| |\\?|\\!|\\'");
            for (int i = 0; i < splited.length; i++) {
                if (splited[i].length() >= 4 ) {
                    String temp = splited[i].substring(0, 4);
                    temp = temp.replaceAll("[^a-zA-Z]", "");
                    if (temp.length() >= 4) {
                        if (map.containsKey(splited[i].toLowerCase())) {
                            map.put(splited[i].toLowerCase(), map.get(splited[i].toLowerCase())+1);
                        }
                        else {
                            map.put(splited[i].toLowerCase(), 1);
                        }
                    }
                }
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        // 通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // 降序排序
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        inputStreamReader.close();
        return list;
    }

    //写入文件
    public void writeFile(File file,int charcount,int linecount,int wordscount,List<Map.Entry<String, Integer>> list) throws Exception
    {
        StringBuilder result = new StringBuilder("");

        result.append("characters:");
        result.append(charcount+linecount-1);
        result.append("\r\n");
        result.append("words:");
        result.append(wordscount);
        result.append("\r\n");
        result.append("lines:");
        result.append(linecount);
        result.append("\r\n");

        int count = 0;

        for(Map.Entry<String, Integer> map : list) {
            count++;
            result.append(map.getKey() + ":" + map.getValue());

            if (count>9) {
                break;
            }

            result.append("\r\n");
        }


        if (!file.exists()) {
            System.out.println("not found result.txt");
            System.out.println("create result.txt");
            file.createNewFile();
        }

        FileWriter filewriter = new FileWriter(file.getAbsoluteFile());
        //System.out.println("absolutely path:"+file.getAbsolutePath());
        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);

        bufferedWriter.write(result.toString());

        bufferedWriter.close();
    }
}
