import java.io.*;

public class CountChar {
    public static int CountChar(String path) throws IOException {
        int charCount = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        while(br.read() != -1){
            charCount++;
        }
        return charCount;
    }
}
