import java.util.*;
import java.io.*;

public class Function {
    public Function()
    {
    }
    
    public boolean IsEmptyLine(String wordLine)
    {
        if(wordLine.replaceAll("\\s*", "").equals(""))   //替换掉输入行的空格、制表、换页符后是否为空
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public int CountChar(File readFile)
    {
        int charNum=0;
        
        try
        {
            if (readFile.isFile() && readFile.exists())
            {
                String wordLine;
                FileInputStream fileIn = new FileInputStream(readFile);
                int readChar =0;
                while((readChar = fileIn.read())!=-1)       //每读入一个字符，字符数自增1
                {
                    charNum++;
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println("没有找到文件");
            e.printStackTrace();
        }
        
        return charNum;
        
    }
    
    public int CountLine(File readFile)
    {
        int lineNum=0;
        try
        {
            if (readFile.isFile() && readFile.exists())
            {
                InputStreamReader inReader = new InputStreamReader(
                                             new FileInputStream(readFile));
                BufferedReader bufferedReader = new BufferedReader(inReader);
                String wordLine;
                
                while((wordLine=bufferedReader.readLine())!=null)
                {
                    if(!IsEmptyLine(wordLine))
                    {
                        lineNum++; 
                        System.out.println(wordLine);
                        System.out.println("行数"+lineNum+"长度"+wordLine.length());
                    }
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println("没有找到文件");
            e.printStackTrace();
        }
        
        return lineNum;
    }
}
