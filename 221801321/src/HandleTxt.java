import java.io.*;

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
    }

    //返回文件总字符数
    public int Returnnum()
    {
        return txt.length();
    }

//    //返回单词总数
//    public int Getwords()
//    {
//
//    }

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
