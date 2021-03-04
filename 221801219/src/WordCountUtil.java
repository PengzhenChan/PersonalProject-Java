import java.io.*;

public class WordCountUtil {
    public static String readFileByChars(String filePath) {
        File file = new File(filePath);
        Reader reader = null;
        //统计字符数
        int cnt = 0;
        //存储文件内容
        StringBuilder fileContent = new StringBuilder();
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            int temp = -1;
            while ((temp = reader.read()) != -1) {
                if (((char) temp) != '\r') {
                    fileContent.append((char) temp);
                    cnt++;
                }
            }
            reader.close();
            System.out.println(fileContent.toString());
            System.out.print("共有字符数:" + cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }
}


