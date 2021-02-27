/************************************************************
* FileName: WordCount.cpp
* 
* Author: 221801410黄镔
* 
* Function List: 1.统计字符数 2.统计单词数 3.统计最多的10个单词及其词频
* 
************************************************************/

import java.util.*;
import java.io.*;

public class WordCount
{
    public static void main(String[] args)
    {
        int lineNum=0;
        int charNum=0;
        String fileName="input.txt";
        try
        {
            File readFile = new File(fileName);
            if (readFile.isFile() && readFile.exists())
            {
                InputStreamReader inReader = new InputStreamReader(
                                             new FileInputStream(fileName));
                BufferedReader bufferedReader = new BufferedReader(inReader);
                String wordLine;
                Function functionMethod = new Function();
                while((wordLine=bufferedReader.readLine())!=null)
                {
                    if(!functionMethod.IsEmptyLine(wordLine))
                    {
                        lineNum++; 
                        System.out.println(wordLine);
                        System.out.println("行数"+lineNum);
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
}