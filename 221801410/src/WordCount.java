/************************************************************
* FileName: WordCount.cpp
* 
* Author: 221801410����
* 
* Function List: 1.ͳ���ַ��� 2.ͳ�Ƶ����� 3.ͳ������10�����ʼ����Ƶ
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
        //System.out.println("���ַ���"+charNum);
        lineNum = functionMethod.CountLine(readFile);
        //System.out.println("��Ч����"+lineNum);
        wordNum = functionMethod.CountWord(readFile);
        //System.out.println("������"+wordNum);
        Vector<Word> allWords = functionMethod.CountFrequentWord(readFile);
        
        /*����ļ�����*/
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
            System.out.println("û���ҵ��ļ�");
            e.printStackTrace();
        }
    }
}