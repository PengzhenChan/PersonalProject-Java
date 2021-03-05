import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Lib {


    private int countChar;            //记录字符数
    private int countWord;            //记录单词数
    private int countLine;            //记录行数
    private InputStreamReader in;
    private OutputStreamWriter out;
    private BufferedReader br;
    private String inputFile;
    private String outputFile;
    private HashMap<String, Integer> map = new HashMap<>();       //使用map键值对来保存单词和频率
    private List<Map.Entry<String,Integer>> list;
}
