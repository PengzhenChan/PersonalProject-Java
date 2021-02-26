import java.io.File;
import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        File dir = new File(" ");
        String inputPath = dir.getCanonicalPath()+args[0];
        String outputPath = dir.getCanonicalPath()+args[1];

        System.out.println(inputPath);
        int charCount = CountChar.countChar(inputPath);
        System.out.println(charCount);
        CountWordRate.countWordRate(inputPath);
    }
}
