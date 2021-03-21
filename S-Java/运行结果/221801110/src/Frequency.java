
import java.io.*;
import java.util.*;

public class Frequency {

    //指定文件路径和文件名
    private static String path;

    //定义一个结果集字符串
    public static String result = "";

    //定义一个map集合保存单词和单词出现的个数
    private TreeMap<String,Integer> tm;

    public Frequency(String path) {
        this.path = path;

        File file=new File(path);
        Reader readFile = null;
        BufferedReader bufferedReadFile = null;
        String tempString = null;
        try {
            readFile = new InputStreamReader(new FileInputStream(file),"UTF-8");
            bufferedReadFile = new BufferedReader(readFile);

            tm = new TreeMap<String,Integer>();
            //读取文件
            while ((tempString=bufferedReadFile.readLine())!=null){
                tempString = tempString.toLowerCase();
                String reg1 = "[^a-zA-Z0-9]";
                String reg2 ="[a-z]{4}[a-z0-9]*";
                //将读取的文本进行分割
                String[] str = tempString.split(reg1);
                for (String s: str){
                    if (s.matches(reg2)){
                        //判断集合中是否已经存在该单词，如果存在则个数加1，否则将单词添加到集合中，且个数置为1
                        if (!tm.containsKey(s)){
                            tm.put(s,1);
                        }
                        else {
                            tm.put(s,tm.get(s)+1);
                        }
                    }
                }
            }
            bufferedReadFile.close();
        }
        catch (Exception e){
            System.err.println("文件不存在");
        }
        finally {
            saveResult(tm);
        }
    }

    public static String saveResult(Map<String,Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //降序排序，故o2 cmp o1，因为o2>o1时返回1，o2<o1时返回-1
                return (o2.getValue().compareTo(o1.getValue()));
            }
        });

        int num = list.size();
        //System.out.println(num);
        for (int i = 0; i < num && i < 10; i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            if (entry == null) {
                return "";
            }
            result += (entry.getKey() + ": " + entry.getValue() + '\n');
        }

        return result;
    }
}