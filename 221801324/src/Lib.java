import java.io.*;
import java.util.*;


public class Lib
{
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

    Lib (String inputFile,String outputFile)
    {

        this.CountChar = 0;
        this.CountWord = 0;
        this.CountLine = 0;
        this.outputFile = outputFile;
        this.inputFile = inputFile;
        List<Map.Entry<String,Integer>> list = null;
    }

    public void getbu () throws FileNotFoundException
    {
        in=new InputStreamReader(new FileInputStream(inputFile));
        bu = new BufferedReader(in);

    }

    public void getWordsNumuber() throws FileNotFoundException        //计算文件中单词个数
    {
        getbu();
        String words;
        try {
            while ((words = bu.readLine()) != null)
            {
                String[] strs=words.split("[^a-zA-Z0-9]");
                String zz = "^[a-zA-Z]{4,}.*";              //正则表达式筛选单词
                for(int i=0;i<strs.length;i++)
                { if(strs[i].matches(zz))
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



    public void getLines() throws FileNotFoundException
    {        //计算文件有效行数
        getbu();
        try {
            String line;
            while ((line = bu.readLine()) != null)
            {
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



    public void getChars() throws FileNotFoundException
    {      //计算文件字符数
            getbu();
            try {
                char ch;
                while((ch=(char)bu.read())!=(char)-1)
                { if(ch<=127)           //确保读取的字符为ASCLL码
                    {
                        CountChar++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    public void getWordHighRate() throws IOException
    {
        //输出高频次
        getbu();
        String words;
        list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        while ((words = bu.readLine()) != null)
        {
            String[] strs = words.split("[^a-zA-Z0-9]");  //正则选取单词
            String zz = "^[a-zA-Z]{4,}.*";
            for (int i = 0; i < strs.length; i++)
            {
                if (strs[i].matches(zz)) {
                    if (!map.containsKey(strs[i].toLowerCase()))
                    {  //删除重复出现在map中的单词
                        map.put(strs[i].toLowerCase(), 1);
                    } else {

                        int num = map.get(strs[i].toLowerCase());   //单词修改小写
                        map.put(strs[i].toLowerCase(), num + 1);
                    }
                }
            }
        }
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b)
            {
                if(a.getValue().equals(b.getValue())) {      //若频率一样，这可以使其按字母表排序
                    return a.getKey().compareTo(b.getKey());
                }
                else
                    return a.getValue().compareTo(b.getValue());     //频率排序
            }
        });
    }




    public void getout ()throws IOException
    {
        //输出流函数
        out = new OutputStreamWriter(new FileOutputStream(outputFile),"UTF-8");  //编码格式
    }

    public void Closeout ()throws IOException
    {
        out.close();
    }

    void writeFile() throws IOException
    {    //最终各类结果输出函数
        getout();
        StringBuilder str = new StringBuilder();
        str.append("characters: "+CountChar+"\n" + "words: "+CountWord+"\n" +"lines: "+CountWord+"\n");

        for(int i = 0;i<(list.size()<10 ? list.size():10);i++)
        {
            str.append(list.get(i).getKey()+": "+list.get(i).getValue()+"\n");
        }
        out.write(str.toString());
        out.flush();
        Closeout();  
    }


}

