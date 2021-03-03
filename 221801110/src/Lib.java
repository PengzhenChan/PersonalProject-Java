import java.io.*;
import java.util.*;

public class Lib {

    //ͳ���ַ�����
    //����������ļ���
    //����ֵ���ַ�����
    public static int numOfChar(String filename) {
        File file = new File(filename);
        Reader readFile = null;
        int count = 0;
        try {
            readFile = new InputStreamReader(new FileInputStream(file),"UTF-8");
            int tempChar;
            while ((tempChar=readFile.read()) != -1) {
                count++;
            }
            readFile.close();
        }
        catch (Exception e){
            System.out.println("�ļ�������");
        }
        finally {
            return count;
        }
    }

    //ͳ�Ƶ�������
    //����������ļ���
    //����ֵ����������
    public static int numOfWord(String filename) {
        File file = new File(filename);
        boolean letter_flag=false;
        Reader readFile = null;
        int countWord = 0;
        try {
            readFile = new InputStreamReader(new FileInputStream(file),"UTF-8");
            int tempchar;
            boolean flag = false;//����ǲ��ǵ���
            for (int i = 0; (tempchar=readFile.read()) != -1; ) {
                char ch = (char)tempchar;
                //����ĸ
                if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <='z'){
                    i++;
                    flag = true;
                }

                //�����֣��ж��ǲ����ڵ������֮�����
                else if (ch >= '0' && ch <= '9'){
                    i++;
                    if (i < 5) {
                        i = 0;
                        flag = false;
                    }
                }

                //�ָ�����
                else {
                    if (i >= 4 && flag == true) countWord++;
                    flag = false;
                    i = 0;
                }

                //System.out.println(ch+":"+ i);
            }
            readFile.close();
        }
        catch (Exception e){
            System.out.println("�ļ�������");
        }
        finally {
            return countWord;
        }
    }


    //ͳ����Ч����
    //����������ļ���
    //����ֵ����Ч����
    public static int numOfLine(String filename) {
        File file = new File(filename);
        Reader readFile = null;
        BufferedReader bufferedReadFile = null;
        int countSum = 1;//����Ҫ������У���ʼ������Ϊ1
        int countNull = 0;//������
        try {
            readFile = new InputStreamReader(new FileInputStream(file),"UTF-8");
            int tempChar;
            while ((tempChar = readFile.read()) != -1) {
                if ((char)tempChar == '\n') {
                    countSum++;
                }
            }
            bufferedReadFile = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = bufferedReadFile.readLine()) != null){
                //ȥ���հ��ַ�
                tempString=tempString.replace("\f","");
                tempString=tempString.replace("\b","");
                tempString=tempString.replace("\r","");
                tempString=tempString.replace("\t","");
                tempString=tempString.replace("\n","");
                tempString=tempString.replace(" ","");

                if (tempString.equals("")) {
                    countNull++;
                }
            }
            readFile.close();
            bufferedReadFile.close();
        }
        catch (Exception e){
            System.out.println("�ļ�������");
        }
        finally {
            //System.out.println(countSum);
            //System.out.println(countNull);
            return countSum-countNull;
        }
    }

}
