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
        String fileName="input.txt";
        File readFile = new File(fileName);
        Function functionMethod = new Function();
        charNum=functionMethod.CountChar(readFile);
        System.out.println("���ַ���"+charNum);
        lineNum = functionMethod.CountLine(readFile);
        System.out.println("��Ч����"+lineNum);
        wordNum = functionMethod.CountWord(readFile);
        System.out.println("������"+wordNum);
        functionMethod.CountFrequentWord(readFile);
    }
}