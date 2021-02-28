import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountLine {
    public static String countLine(String path) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        String str = null;
        String pattern = ".*[^ ].*";
        int lineCount = 0;

        while((str= br.readLine())!=null){
            if(str.matches(pattern)){
                lineCount++;
            }
        }

        String lineCountStr = "lines:"+Integer.toString(lineCount)+"\n";
        return lineCountStr;
    }
}
