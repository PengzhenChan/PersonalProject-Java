import java.io.*;

public class CountLine {
    public static String countLine(String path) throws FileNotFoundException, UnsupportedEncodingException {
        String str = null;
        int lineCount = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));

        while(true){
            try {
                if ((str= br.readLine())==null) break;
            } catch (IOException e) {
                System.out.println("错误位于countLine方法,原因可能是文件读出现问题");
            }
            if(!str.isBlank()){
                lineCount++;
            }
        }

        try {
            br.close();
        } catch (IOException e) {
            System.out.println("错误位于countLine方法,原因可能是文件流未能正确的关闭");
        }

        String lineCountStr = "\n"+"lines:"+Integer.toString(lineCount);
        return lineCountStr;
    }
}
