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
                        System.out.println("����"+lineNum);
                    }
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println("û���ҵ��ļ�");
            e.printStackTrace();
        }
    }
}