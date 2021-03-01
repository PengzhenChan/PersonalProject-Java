import java.io.*;

public class WordCount {

    //统计字符数量
    //传入参数：文件名
    //返回值：字符数量
    private static int numOfChar(String filename) {
        File file = new File(filename);
        Reader readFile = null;
        int num = 0;
        try {
            readFile = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar=readFile.read()) != -1) {
                num++;
            }
            readFile.close();
        }
        catch (Exception e){
            System.out.println("指定输入文件不存在");
        }
        finally {
            return num;
        }
    }

    public static void main(String[] args){
        int a = numOfChar("d:\\1.txt");
        System.out.println("characters: "+a);
    }
}
