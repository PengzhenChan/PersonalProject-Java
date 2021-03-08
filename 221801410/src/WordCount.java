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
        int wordNum=0;
        String fileName=args[0];
        String outFileName=args[1];
        File readFile = new File(fileName);
        Function functionMethod = new Function();
        charNum=functionMethod.CountChar(readFile);
        lineNum = functionMethod.CountLine(readFile);
        wordNum = functionMethod.CountWord(readFile);
        Vector<Word> allWords = functionMethod.CountFrequentWord(readFile);
        
        /*输出文件部分*/
        String outMsg = "";
        outMsg+="characters: "+charNum+"\n";
        outMsg+="words: "+wordNum+"\n";
        outMsg+="lines: "+lineNum+"\n";
        if(allWords.size() <= 10)
        {
            for(int i = 0;i < allWords.size();i++)
            {
                outMsg+=allWords.get(i).GetWords()+": "
                                    +allWords.get(i).GetFrequent()+"\n";
            }
        }
        else
        {
            for(int i = 0;i < 10;i++)
            {
                outMsg+=allWords.get(i).GetWords()+": "
                                    +allWords.get(i).GetFrequent()+"\n";
            }
        }
        try 
        {
            File outFile = new File(outFileName);
            PrintStream printStream = new PrintStream(new FileOutputStream(outFile));
            
            printStream.print(outMsg);
        }
        catch(Exception e)
        {
            System.out.println("没有找到文件");
            e.printStackTrace();
        }
    }
}