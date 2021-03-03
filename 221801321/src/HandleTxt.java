import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleTxt {
    File in,out;
    private int numbers,lines;
    String txt;

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

        Pattern pattern=Pattern.compile("[a-z]{4}[a-z0-9]*");
        Matcher matcher=pattern.matcher(txt);
        if (matcher.find())
        {
            if ((matcher.start()-1)>= 0)
            {
                if (Legalchar(txt.charAt(matcher.start()-1)))
                {
                    words++;
                }
            }
            else
            {
                words++;
            }
        }
        while (matcher.find()){
            if (Legalchar(txt.charAt(matcher.start()-1))){
                words++;
            }
        }
        return words;
    }

    //判断前一个单词字符是否为其他字符
    public boolean Legalchar(char ch)
    {
        if((ch>='a'&&ch<='z')||(ch>='0'&&ch<='9'))
            return false;
        return true;
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
}
