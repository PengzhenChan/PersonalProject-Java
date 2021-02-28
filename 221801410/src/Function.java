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
        int charNum=0;      //用于统计字符数
        
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
        int lineNum=0;      //用于统计有效行数
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
    
    public int CountWord(File readFile)
    {
        int wordNum=0;      //用于统计单词数
        int wordLength=0;          //用于判断是否为一个单词，既4个英文字母开头
        int resetWord=0;           //用于判断是否重新开始一个单词读入 
        try
        {
            if (readFile.isFile() && readFile.exists())
            {
                String wordLine;
                FileInputStream fileIn = new FileInputStream(readFile);
                int readChar=0;
                String word="";     //用于拼接读入的字符成为单词
                
                while((readChar = fileIn.read())!=-1)       //每读入一个字符，字符数自增1
                {
                    resetWord = 0;
                    
                    if((readChar>='a'&&readChar<='z')
                            ||(readChar>'A'&&readChar<'Z')
                                ||(readChar>'0'&&readChar<'9'))
                    {
                        if(readChar>'0'&&readChar<'9')
                        {
                            if(wordLength>=4)
                            {
                                char[] ch = new char[1];
                                ch[0] = (char)readChar;
                                word += ch[0];
                                System.out.println("字符"+ch[0]+"拼接"+word);
                                wordLength++;
                            }
                            else
                            {
                                resetWord = 1;
                            }
                        }
                        else 
                        {
                            char[] ch = new char[1];
                            ch[0] = (char)readChar;
                            word += ch[0];
                            System.out.println("字符"+ch[0]+"拼接"+word);
                            wordLength++;
                        }
                    }
                    else
                    {
                        if(wordLength>=4)
                        {
                            wordNum++;
                        }
                        resetWord = 1;
                    }
                    
                    if(resetWord==1)
                    {
                        word = "";
                        wordLength = 0;
                    }
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println("没有找到文件");
            e.printStackTrace();
        }
        
        return wordNum;
    }
}
