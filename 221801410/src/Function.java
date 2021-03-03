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
                FileInputStream fileIn = new FileInputStream(readFile);
                int readChar =0;
                while((readChar = fileIn.read())!=-1)       //ÿ����һ���ַ����ַ�������1
                {
                    charNum++;
                }
                
                fileIn.close();
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
                        /*
                        System.out.println(wordLine);
                        System.out.println("����"+lineNum+"����"+wordLine.length());
                        */
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
        int wordLength=0;          //�����ж��Ƿ�Ϊһ�����ʣ���4��Ӣ����ĸ��ͷ
        int resetWord=0;           //�����ж��Ƿ����¿�ʼһ�����ʶ��� 
        int isNotWord=0;            //��wordLength��ͬ���ã��ж��ǲ���һ������
        
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
                    resetWord = 0;
                    
                    if((readChar>='a'&&readChar<='z')
                            ||(readChar>='A'&&readChar<='Z')
                                ||(readChar>='0'&&readChar<='9'))
                    {
                        if(readChar>='0'&&readChar<='9')
                        {
                            if(wordLength>=4)
                            {
                                char[] ch = new char[1];
                                ch[0] = (char)readChar;
                                word += ch[0];
                                /*
                                System.out.println("�ַ�"+ch[0]+"ƴ��"+word);
                                */
                                wordLength++;
                            }
                            else
                            {
                                isNotWord = 1;
                            }
                        }
                        else 
                        {
                            char[] ch = new char[1];
                            ch[0] = (char)readChar;
                            word += ch[0];
                            /*
                            System.out.println("�ַ�"+ch[0]+"ƴ��"+word);
                            */
                            wordLength++;
                        }
                    }
                    else
                    {
                        if(wordLength>=4 && isNotWord != 1)
                        {
                            wordNum++;
                        }
                        isNotWord = 0;
                        resetWord = 1;
                    }
                    
                    if(resetWord==1)
                    {
                        word = "";
                        wordLength = 0;
                    }
                }
                if(wordLength>=4)
                {
                    wordNum++;
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
    
    public Vector<Word> CountFrequentWord(File readFile)
    {
        int wordLength=0;          //�����ж��Ƿ�Ϊһ�����ʣ���4��Ӣ����ĸ��ͷ
        int resetWord=0;           //�����ж��Ƿ����¿�ʼһ�����ʶ��� 
        int isNotWord=0;            //��wordLength��ͬ���ã��ж��ǲ���һ������
        
        Vector<Word> allWords = new Vector<Word>();
        int noRepeatWordNum = 0;
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
                    resetWord = 0;
                    
                    if((readChar>='a'&&readChar<='z')
                            ||(readChar>='A'&&readChar<='Z')
                                ||(readChar>='0'&&readChar<='9'))
                    {
                        if(readChar>='0'&&readChar<='9')
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
                                isNotWord = 1;
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
                        if(wordLength>=4 && isNotWord !=1)
                        {
                            word = word.toLowerCase();
                            int index = FindWord(allWords,word);    //�������Ƿ��ظ����ظ��򷵻��±�
                            if(index!=-1)
                            {
                                allWords.get(index).AddFrequent();
                                /*
                                System.out.println("�õ����Ѵ��ڣ�"
                                                        +allWords.get(index).GetWords()+"�����ǣ�"
                                                            +allWords.get(index).GetFrequent());
                                */
                            }
                            else 
                            {
                                noRepeatWordNum++;        //���ڼ����ܹ��ж��ٸ����ʴ������Ѿ�
                                Word aWord = new Word(word,1);
                                allWords.add(aWord);
                                /*
                                System.out.println(allWords.get(noRepeatWordNum-1).GetWords());
                                */
                            }
                        }
                        isNotWord = 0;
                        resetWord = 1;
                    }
                    
                    if(resetWord==1)
                    {
                        word = "";
                        wordLength = 0;
                    }
                }
                
                /*��ֹ���һ�ζ�����Ǻ�����ַ����������һ������û�м�������*/
                if(wordLength>=4 && isNotWord != 1)
                {
                    word = word.toLowerCase();
                    int index = FindWord(allWords,word);    //�������Ƿ��ظ����ظ��򷵻��±�
                    if(index!=-1)
                    {
                        allWords.get(index).AddFrequent();
                        /*  ����Ѵ��ڵĸõ��ʼ�Ƶ��
                        System.out.println("�õ����Ѵ��ڣ�"
                                                +allWords.get(index).GetWords()+"�����ǣ�"
                                                    +allWords.get(index).GetFrequent());
                        */
                    }
                    else 
                    {
                        noRepeatWordNum++;        //���ڼ����ܹ��ж��ٸ����ʴ������Ѿ�
                        Word aWord = new Word(word,1);
                        allWords.add(aWord);
                        /*  ���������������Щ����
                        System.out.println(allWords.get(noRepeatWordNum-1).GetWords());
                        */
                    }
                }
                
                Comparator<Word> cmp = new CompareRule();
                Collections.sort(allWords,cmp);
                /*  ������������е��ʼ�Ƶ��
                System.out.println("�����ǰ���Ƶ�������");
                for(int i = 0;i<allWords.size();i++)
                {
                    System.out.println(allWords.get(i).GetWords()+":"+allWords.get(i).GetFrequent());
                }
                */
            }
            
        }
        catch(Exception e)
        {
            System.out.println("û���ҵ��ļ�");
            e.printStackTrace();
        }
        return allWords;
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
