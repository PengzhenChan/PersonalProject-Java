
import java.io.*;
import java.util.*;

public class Frequency {

    //����һ��map���ϱ��浥�ʺ͵��ʳ��ֵĸ���
    private TreeMap<String,Integer> tm;

    public Frequency(String path) {
        File file=new File(path);
        BufferedReader bufferedReadFile = null;
        String tempString = null;
        try {
            bufferedReadFile = new BufferedReader(new FileReader(file));
            tm = new TreeMap<String,Integer>();
            //��ȡ�ļ�
            while ((tempString=bufferedReadFile.readLine())!=null){
                tempString = tempString.toLowerCase();
                String reg1 = "[^a-zA-Z0-9]";
                String reg2 ="[a-z]{4}[a-z0-9]*";
                //����ȡ���ı����зָ�
                String str[] = tempString.split(reg1);
                for (String s: str){
                    if (s.matches(reg2)){
                        //�жϼ������Ƿ��Ѿ����ڸõ��ʣ���������������1�����򽫵�����ӵ������У��Ҹ�����Ϊ1
                        if (!tm.containsKey(s)){
                            tm.put(s,1);
                        }
                        else {
                            tm.put(s,tm.get(s)+1);
                        }
                    }
                }
            }
            bufferedReadFile.close();
        }
        catch (Exception e){
            System.out.println("�ļ�������");
        }
        finally {
            //printResult(tm);
        }
    }

    public static void printResult(Map<String,Integer> map) {
        //
        }
    }
}