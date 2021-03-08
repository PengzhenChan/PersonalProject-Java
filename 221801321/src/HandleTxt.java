import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
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
        Ouputxt();
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

    //获取文章的有效行数
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

        Pattern pattern=Pattern.compile("[a-z]{4}[a-z0-9]*");
        Matcher matcher=pattern.matcher(txt);
        if (matcher.find())
        {
            word=matcher.group();
            if ((matcher.start()-1)>=0)
            {
                if (Legalchar(txt.charAt(matcher.start()-1)))
                {
                    WordsMap.put(word,1);
                }
            }
            else
            {
                WordsMap.put(word,1);
            }
        }
        while (matcher.find())
        {
            if (Legalchar(txt.charAt(matcher.start()-1)))
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
        }
        WordsMap.entrySet().stream().sorted(Map.Entry.<String, Integer> comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey())).limit(10)
                .forEachOrdered(x->result.put(x.getKey(),x.getValue()));
        return result;
    }

    //判断前一个单词字符是否为其他字符
    public boolean Legalchar(char ch)
    {
        if((ch>='a'&&ch<='z')||(ch>='0'&&ch<='9'))
            return false;
        return true;
    }

    //输出结果至output.txt
    void Ouputxt()throws IOException
    {
        StringBuilder sb=new StringBuilder();
        BufferedReader br=null;
        PrintWriter pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream(out),
                "UTF-8"));

        Iterator<Map.Entry<String, Integer>> it=GetTen().entrySet().iterator();
        sb.append("characters: "+Returnnum()+"\n"+"words: "+Getwords()+'\n'
                +"lines: "+Getlines()+'\n');
        while(it.hasNext())
        {
            Map.Entry<String, Integer> entry=it.next();
            sb.append(entry.getKey()+": "+entry.getValue()+"\n");
        }
        pw.print(sb.toString());
        pw.flush();
        pw.close();
    }
}
