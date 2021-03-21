import java.io.*;
import java.util.*;

public class Lib {

    //统计字符数量
    //传入参数：文件名
    //返回值：字符数量
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
            System.err.println("文件不存在");
        }
        finally {
            return count;
        }
    }

    //统计单词数量
    //传入参数：文件名
    //返回值：单词数量
    public static int numOfWord(String filename) {
        /* 旧方法
        File file = new File(filename);
        boolean letter_flag=false;
        Reader readFile = null;
        int countWord = 0;
        try {
            readFile = new InputStreamReader(new FileInputStream(file),"UTF-8");
            int tempchar;
            boolean flag = false;//标记是不是单词
            for (int i = 0; (tempchar=readFile.read()) != -1; ) {
                char ch = (char)tempchar;
                //是字母
                if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <='z'){
                    i++;
                    flag = true;
                }

                //是数字，判断是不是在第五个及之后出现
                else if (ch >= '0' && ch <= '9'){
                    i++;
                    if (i < 5) {
                        i = 0;
                        flag = false;
                    }
                }

                //分割符情况
                else {
                    if (i >= 4 && flag == true) countWord++;
                    flag = false;
                    i = 0;
                }

            }
            readFile.close();
        }
        catch (Exception e){
            System.err.println("文件不存在");
        }
        finally {
            return countWord;
        }*/

        File file = new File(filename);
		Reader readFile = null;
        BufferedReader bufferedReadFile = null;
        int countWord = 0;
        
        String tempString;
        try {
            readFile = new InputStreamReader(new FileInputStream(file),"UTF-8");
            bufferedReadFile = new BufferedReader(readFile);
            while ((tempString = bufferedReadFile.readLine()) != null){
                tempString = tempString.toLowerCase();
                String reg1 = "[^a-zA-Z0-9]+";
                String reg2 ="[a-z]{4}[a-z0-9]*";
                //将读取的文本进行分割
                String[] str = tempString.split(reg1);
                for (String s: str){
                    if (s.matches(reg2)){
                        countWord++;
                    }
                }
            }
            bufferedReadFile.close();
        }
        catch (Exception e){
            System.err.println("文件不存在");
        }
        finally {
            return countWord;
        }

    }


    //统计有效行数
    //传入参数：文件名
    //返回值：有效行数
    public static int numOfLine(String filename) {
        File file = new File(filename);
        BufferedReader bufferedReadFile = null;
        int countSum = 0;//总行数
        int countNull = 0;//空行数
        try {
            bufferedReadFile = new BufferedReader(new FileReader(file));
            String tempString;
            while ((tempString = bufferedReadFile.readLine()) != null){
                countSum++;

                //去掉空白字符
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
            bufferedReadFile.close();
        }
        catch (Exception e){
            System.err.println("文件不存在");
        }
        finally {
            //System.out.println(countSum);
            //System.out.println(countNull);
            return countSum-countNull;
        }
    }

}
