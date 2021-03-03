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

}
