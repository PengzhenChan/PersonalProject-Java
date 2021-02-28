import java.io.File;
import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        File dir = new File("..");
        String inputPath = dir.getCanonicalPath()+"\\"+args[0];
        String outputPath = dir.getCanonicalPath()+args[1];

        //CountWordRate.countWordRate(inputPath);
        System.out.println(inputPath);
        System.out.println(outputPath);
    }
}
