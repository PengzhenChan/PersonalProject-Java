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
    
    public void CountFrequentWord(File readFile)
    {
        int wordLength=0;          //用于判断是否为一个单词，既4个英文字母开头
        int resetWord=0;           //用于判断是否重新开始一个单词读入 
        Vector<Word> allWords = new Vector<Word>();
        int noRepeatWordNum = 0;
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
                            wordLength++;
                        }
                    }
                    else
                    {
                        if(wordLength>=4)
                        {
                            int index = FindWord(allWords,word);    //查找有是否重复，重复则返回下标
                            if(index!=-1)
                            {
                                allWords.get(index).AddFrequent();
                                System.out.println("该单词已存在："
                                                        +allWords.get(index).GetWords()+"次数是："
                                                            +allWords.get(index).GetFrequent());
                            }
                            else 
                            {
                                noRepeatWordNum++;        //用于计数总共有多少个单词存入了已经
                                Word aWord = new Word(word,1);
                                allWords.add(aWord);
                                System.out.println(allWords.get(noRepeatWordNum-1).GetWords());
                            }
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
    }
    
    public int FindWord(Vector<Word> allWords,String word)
    {
        for(int i = 0;i<allWords.size();i++)
        {
            if(allWords.get(i).GetWords().equals(word))
            {
                return i;
            }
        }
        return -1;
    }
}
