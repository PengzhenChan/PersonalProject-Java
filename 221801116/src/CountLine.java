import java.io.*;

public class CountLine {
    public static String countLine(String path){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("错误位于countLine方法,原因可能是未找到目标文件\n请重新确认文件位置\n" + "当前文件位置" + path);
        }
        String str = null;
        String pattern = ".*[^ ].*";
        int lineCount = 0;

        while(true){
            try {
                if ((str= br.readLine())==null) break;
            } catch (IOException e) {
                System.out.println("错误位于countLine方法,原因可能是文件读出现问题");
            }
            if(str.matches(pattern)){
                lineCount++;
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("错误位于countLine方法,原因可能是文件流未能正确的关闭");
        }

        String lineCountStr = "lines:"+Integer.toString(lineCount)+"\n";
        return lineCountStr;
    }

    public static int CountLineInt(String path){
        int lineCount = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("错误位于countLine方法,原因可能是未找到目标文件\n请重新确认文件位置\n" + "当前文件位置" + path);
        }
        String str = null;
        String pattern = ".*[^ ].*";


        while(true){
            try {
                if ((str= br.readLine())==null) break;
            } catch (IOException e) {
                System.out.println("错误位于countLine方法,原因可能是文件读出现问题");
            }
            if(str.matches(pattern)){
                lineCount++;
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("错误位于countLine方法,原因可能是文件流未能正确的关闭");
        }

        return lineCount;
    }
}
