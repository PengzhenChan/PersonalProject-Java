import java.io.*;

public class CountChar {
    public static String countChar(String path) throws FileNotFoundException, UnsupportedEncodingException {
        int charCount = 0;
        int ch = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));

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

        String charCountStr = "characters: "+Integer.toString(charCount);
        return charCountStr;
    }
}
