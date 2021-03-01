import java.io.*;

public class WordCount {

    //统计字符数量
    //传入参数：文件名
    //返回值：字符数量
    public static int numOfChar(String filename) {
        File file = new File(filename);
        Reader readFile = null;
        int count = 0;
        try {
            readFile = new InputStreamReader(new FileInputStream(file));
            int tempChar;
            while ((tempChar=readFile.read()) != -1) {
                count++;
            }
            readFile.close();
        }
        catch (Exception e){
            System.out.println("指定输入文件不存在");
        }
        finally {
            return count;
        }
    }

    //统计有效行数
    //传入参数：文件名
    //返回值：有效行数
    public static int numOfLine(String filename) {
        File file = new File(filename);
        Reader readFile = null;
        BufferedReader bufferedReadFile = null;
        int countSum = 0;//总行数
        int countNull = 0;//空行数
        try {
            readFile = new InputStreamReader(new FileInputStream(file));
            int tempChar;
            while ((tempChar = readFile.read()) != -1) {
                if ((char)tempChar == '\n') {
                    countSum++;
                }
            }
            bufferedReadFile = new BufferedReader(new FileReader(file));
            String tempString ;
            while ((tempString = bufferedReadFile.readLine()) != null){
                //去掉空白字符
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
            System.out.println("指定输入文件不存在");
        }
        finally {
            System.out.println(countSum);
            System.out.println(countNull);
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
