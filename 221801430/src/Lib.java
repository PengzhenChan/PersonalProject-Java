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
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return num;
        }
    }

    //统计文件单词总数
    public long WordsCount(BufferedReader bufferedReader) {
        long num = 0L;
        int one;
        String str = new String();

        try {
            while ((one = bufferedReader.read()) != -1) {
                char ch = (char) one;

                if(ch>='a'&&ch<='z' || ch>='0'&&ch<='9'
                        || ch>='A'&&ch<='Z'){
                     str += ch;
                }
                else {
                    if(IsWord(str)) {
                        num++;
                    }
                    String temp = new String();
                    str = temp;
                }

            }
            if(IsWord(str))
                num++;
        }catch (Exception e){
            e.printStackTrace();
        }
        return num;
    }

    //判断单词是否合法
    public boolean IsWord(String str) {

        for (int i=0;i<str.length();i++) {
            char ch = str.charAt(i);

            if (i<4 && ch>='0' && str.charAt(i)<='9') {
                return false;
            }
        }

        return true;
    }

    //统计文件行数
    public int FileLines(BufferedReader bufferedReader) {
        int rows = 0;

        int ch;
        char temp = '0';
        try {
            while ((ch = bufferedReader.read()) != -1) {
                char chs = (char) ch;

                if (temp == '\n' && chs == '\r' || temp == '\r'
                        || temp == '\n') {
                    rows++;
                    ch = '0';
                }
                temp = (char) ch;

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        rows++;
        return rows;

    }
}
