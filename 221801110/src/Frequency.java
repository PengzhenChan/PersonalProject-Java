
import java.io.*;
import java.util.*;

public class Frequency {

    //定义一个map集合保存单词和单词出现的个数
    private TreeMap<String,Integer> tm;

    public Frequency(String path) {
        File file=new File(path);
        BufferedReader bufferedReadFile = null;
        String tempString = null;
        try {
            bufferedReadFile = new BufferedReader(new FileReader(file));
            tm = new TreeMap<String,Integer>();
            //读取文件
            while ((tempString=bufferedReadFile.readLine())!=null){
                tempString = tempString.toLowerCase();
                String reg1 = "[^a-zA-Z0-9]";
                String reg2 ="[a-z]{4}[a-z0-9]*";
                //将读取的文本进行分割
                String str[] = tempString.split(reg1);
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
            System.out.println("文件不存在");
        }
        finally {
            //printResult(tm);
        }
    }

    public static void printResult(Map<String,Integer> map) {
        //
        }
    }
}