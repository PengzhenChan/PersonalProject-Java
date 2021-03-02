import java.io.*;

public class WordCount {

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
            System.out.println("ָ�������ļ�������");
        }
        finally {
            return count;
        }
    }

    //ͳ����Ч����
    //����������ļ���
    //����ֵ����Ч����
    public static int numOfLine(String filename) {
        File file = new File(filename);
        Reader readFile = null;
        BufferedReader bufferedReadFile = null;
        int countSum = 0;//������
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
            String tempString ;
            while ((tempString = bufferedReadFile.readLine()) != null){
                //ȥ���հ��ַ�
                tempString=tempString.replace("\r","");
                tempString=tempString.replace("\t","");
                tempString=tempString.replace("\n","");
                tempString=tempString.replace(" ","");

                if (tempString.equals("")) {
                    countNull++;
                }
            }
            readFile.close();
        }
        catch (Exception e){
            System.out.println("ָ�������ļ�������");
        }
        finally {
            //System.out.println(countSum);
            //System.out.println(countNull);
            return countSum-countNull;
        }
    }

    public static void main(String[] args){
        int a = numOfChar("d:\\1.txt");
        System.out.println("characters: "+a);
        int c = numOfLine("d:\\1.txt");
        System.out.println("lines: "+c);

    }
}
