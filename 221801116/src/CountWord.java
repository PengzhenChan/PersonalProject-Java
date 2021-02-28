import java.io.*;

public class CountWord {
    public static String countWord(String path) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String str = null;
        int wordCount = 0;
        while((str= br.readLine())!=null){
            String[] splitStr = str.split("[^0-9a-zA-Z]");
            for(int i=0;i<splitStr.length;i++){
                splitStr[i] = splitStr[i].toLowerCase();
                if(CountWordRate.isThatWord(splitStr[i])){
                    wordCount++;
                }
            }
        }
        br.close();

        String wordCountStr = "words:"+Integer.toString(wordCount)+"\n";
        return wordCountStr;
    }
}
