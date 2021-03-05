import java.io.*;

public class CountWord {
    public static String countWord(String path) throws FileNotFoundException, UnsupportedEncodingException {
        String str = null;
        int wordCount = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));

        while(true){
            try {
                if ((str= br.readLine())==null) break;
            } catch (IOException e) {
                System.out.println("错误位于countWord方法,原因可能是文件读出现问题");
            }
            String[] splitStr = str.split("[^0-9a-zA-Z]");
            for(int i = 0;i < splitStr.length;i++){
                splitStr[i] = splitStr[i].toLowerCase();
                if(CountWordRate.isThatWord(splitStr[i])){
                    wordCount++;
                }
            }
        }

        try {
            br.close();
        } catch (IOException e) {
            System.out.println("错误位于countWord法,原因可能是文件流未能正确的关闭");
        }

        String wordCountStr = "\n"+"words: "+Integer.toString(wordCount);
        return wordCountStr;
    }
}
