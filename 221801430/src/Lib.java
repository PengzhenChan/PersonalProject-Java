import java.io.*;

public class Lib {

    String fileName;
    File file = null;
    InputStream in = null;

    //读取文件内容
    public BufferedReader ReadFile(String fileName) {
        file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            in = new FileInputStream(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return reader;
        }
    }

    //统计文件的字符数
    public long CharCount (BufferedReader bufferedReader) {

        long num = 0L;
        int ch;

        try {
            while ((ch=bufferedReader.read()) != -1){
                if (ch <= 127 && ch >= 0){
                    num++;
                    char ch1 = (char)ch;
                    System.out.println(ch1);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return num;
        }

    }
}
