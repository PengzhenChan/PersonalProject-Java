import java.io.*;

public class CountChar {
    public static String countChar(String path){
        int charCount = 0;
        int ch = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            System.out.println("错误位于countChar方法,原因可能是未找到目标文件\n请重新确认文件位置\n" + "当前文件位置" + path);
        }
        while(true){
            try {
                if ((ch=br.read())==-1)break;
            } catch (IOException e) {
                System.out.println("错误位于countChar方法,原因可能是文件读出现问题");
            }
            if(ch<128&&ch>0)
            charCount++;
        }
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("错误位于countChar方法,原因可能是文件流未能正确的关闭");
        }

        String charCountStr = "characters:"+Integer.toString(charCount)+"\n";
        return charCountStr;
    }
}
