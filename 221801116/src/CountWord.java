import java.io.*;

public class CountWord {
    public static String countWord(String path){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            System.out.println("错误位于countWord方法,原因可能是未找到目标文件\n请重新确认文件位置\n" + "当前文件位置" + path);
        }
        String str = null;
        int wordCount = 0;
        while(true){
            try {
                if ((str= br.readLine())==null) break;
            } catch (IOException e) {
                System.out.println("错误位于countWord方法,原因可能是文件读出现问题");
            }
            String[] splitStr = str.split("[^0-9a-zA-Z]");
            for(int i=0;i<splitStr.length;i++){
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

        String wordCountStr = "words:"+Integer.toString(wordCount)+"\n";
        return wordCountStr;
    }
}
