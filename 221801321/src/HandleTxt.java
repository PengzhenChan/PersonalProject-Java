import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HandleTxt {
    File in,out;
    private int numbers,lines;
    private String txt;
    private Map<String,Integer> WordsMap;

    public HandleTxt(File f1, File f2)throws IOException
    {
        in=f1;
        out=f2;
        Changestr();

    }

    //将文件内容转为String字符串
    public void Changestr() throws IOException
    {
        int n=0;

        StringBuffer sb=new StringBuffer();
        FileInputStream fileInputStream=new FileInputStream(in);
        while ((n=fileInputStream.read())!=-1)
        {
            char ch=(char)n;
            sb.append(ch);
        }
        txt=sb.toString();
        txt=txt.toLowerCase();
    }

    //返回文件总字符数
    public int Returnnum()
    {
        return txt.length();
    }

    //返回单词总数
    public int Getwords()
    {
        int words=0;

        Pattern pattern=Pattern.compile("(^|[^a-z0-9])([a-z]{4}[a-z0-9]*)");
        Matcher matcher=pattern.matcher(txt);
        while (matcher.find())
        {
            words++;
        }
        return words;
    }

    public int Getlines()throws IOException
    {
        int line=0;
        BufferedReader br=new BufferedReader(new FileReader(in));
        String content=br.readLine();
        StringBuilder sb=new StringBuilder();

        while (content!=null)
        {
            for(int i=0;i<content.length();i++)
            {
                if(content.charAt(i)!='\t'&&content.charAt(i)!='\n'&&content.charAt(i)!=' '
                        &&content.charAt(i)!='\r')
                {
                    line++;
                    break;
                }
            }
            content=br.readLine();
        }
        return line;
    }

    //排序，并取前十个频率最高的单词的Map
    public Map<String,Integer> GetTen()
    {
        WordsMap=new HashMap<>();//使用Map存储单词及其数目
        Map<String,Integer> result=new LinkedHashMap<>();
        String word="";

        Pattern pattern=Pattern.compile("(^|[^a-z0-9])([a-z]{4}[a-z0-9]*)");
        Matcher matcher=pattern.matcher(txt);
        while (matcher.find())
        {
            word=matcher.group();
            if (WordsMap.containsKey(word))//Map中已含有此单词
            {
                WordsMap.put(word,WordsMap.get(word)+1);
            }
            else
            {
                WordsMap.put(word,1);
            }

        }
        WordsMap.entrySet().stream().sorted(Map.Entry.<String, Integer> comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey())).limit(10)
                .forEachOrdered(x->result.put(x.getKey(),x.getValue()));
        System.out.println(result);
        return result;
    }
}
