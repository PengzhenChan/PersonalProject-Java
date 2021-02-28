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
        String fileName="input.txt";
        File readFile = new File(fileName);
        Function functionMethod = new Function();
        charNum=functionMethod.CountChar(readFile);
        System.out.println("总字符数"+charNum);
        lineNum = functionMethod.CountLine(readFile);
        System.out.println("有效行数"+lineNum);
        wordNum = functionMethod.CountWord(readFile);
        System.out.println("单词数"+wordNum);
        functionMethod.CountFrequentWord(readFile);
    }
}