import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Lib {


    private int CountChar;            //记录字符数
    private int CountWord;            //记录单词数
    private int CountLine;            //记录行数
    private InputStreamReader in;
    private OutputStreamWriter out;
    private BufferedReader bu;
    private String inputFile;
    private String outputFile;
    private HashMap<String, Integer> map = new HashMap<>();       //使用map键值对来保存单词和频率
    private List<Map.Entry<String,Integer>> list;

    Lib (String inputFile,String outputFile) {

        this.CountChar = 0;
        this.CountWord = 0;
        this.CountLine = 0;
        this.outputFile = outputFile;
        this.inputFile = inputFile;
        List<Map.Entry<String,Integer>> list = null;
    }

    public void getbr () throws FileNotFoundException
    {
        in=new InputStreamReader(new FileInputStream(inputFile));
        bu = new BufferedReader(in);

    }

    public void getWordsNumuber() throws FileNotFoundException        //读取文件中单词个数
    {
        getbr();
        String words;
        try {
            while ((words = bu.readLine()) != null) {
                String[] strs=words.split("[^a-zA-Z0-9]");
                String zz = "^[a-zA-Z]{4,}.*";              //正则表达式筛选单词
                for(int i=0;i<strs.length;i++)
                {
                    if(strs[i].matches(zz))
                    {
                        CountWord++;
                    }
                    else ;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void getLines() throws FileNotFoundException {        //读取文件有效行数
        getbr();
        try {
            String line;
            while ((line = bu.readLine()) != null) {
                char[] c=line.toCharArray();
                for (int i=0;i<c.length;i++)
                { if (c[i]!='\n' && c[i]!='\r' && c[i]!='\t')      //计算行数
                    {
                        CountLine++;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
