import java.io.File;
import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        int number = args.length;
        File dir = new File(" ");
        String inputPath = dir.getCanonicalPath()+args[0];
        String outputPath = dir.getCanonicalPath()+args[1];

        int charCount = CountChar.CountChar(inputPath);
        //System.out.println(charCount);
        CountWord.CountWord(inputPath);
    }
}
