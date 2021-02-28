import java.util.*;
import java.io.*;

public class Function {
    public Function()
    {
    }
    
    public boolean IsEmptyLine(String wordLine)
    {
        if(wordLine.replaceAll("\\s*", "").equals(""))   //�滻�������еĿո��Ʊ���ҳ�����Ƿ�Ϊ��
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
        int charNum=0;      //����ͳ���ַ���
        
        try
        {
            if (readFile.isFile() && readFile.exists())
            {
                String wordLine;
                FileInputStream fileIn = new FileInputStream(readFile);
                int readChar =0;
                while((readChar = fileIn.read())!=-1)       //ÿ����һ���ַ����ַ�������1
                {
                    charNum++;
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println("û���ҵ��ļ�");
            e.printStackTrace();
        }
        
        return charNum;
        
    }
    
    public int CountLine(File readFile)
    {
        int lineNum=0;      //����ͳ����Ч����
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
                        System.out.println("����"+lineNum+"����"+wordLine.length());
                    }
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println("û���ҵ��ļ�");
            e.printStackTrace();
        }
        
        return lineNum;
    }
    
    public int CountWord(File readFile)
    {
        int wordNum=0;      //����ͳ�Ƶ�����
        int flg=0;          //�����ж��Ƿ�Ϊһ�����ʣ���4��Ӣ����ĸ��ͷ
        try
        {
            if (readFile.isFile() && readFile.exists())
            {
                String wordLine;
                FileInputStream fileIn = new FileInputStream(readFile);
                int readChar=0;
                String word="";     //����ƴ�Ӷ�����ַ���Ϊ����
                
                while((readChar = fileIn.read())!=-1)       //ÿ����һ���ַ����ַ�������1
                {
                    if((readChar>='a'&&readChar<='z')||(readChar>'A'&&readChar<'Z'))
                    {
                        char[] ch = new char[1];
                        ch[0] = (char)readChar;
                        word+=ch[0];
                        System.out.println("�ַ�"+ch[0]+"ƴ��"+word);
                    }
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println("û���ҵ��ļ�");
            e.printStackTrace();
        }
        
        return wordNum;
    }
}
