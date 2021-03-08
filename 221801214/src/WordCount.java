import java.io.IOException;

public class WordCount {
    public static void main(String[] args) throws IOException {
        String inputFilename = args[0];
        String outputFilename = args[1];
        Lib countWord = new Lib(inputFilename,outputFilename);
/*        System.out.println(countWord.getChar_count());*/
/*        String query = "some examples of regex test";
        String[] parts = query.split(" ");
        System.out.println(Arrays.toString(parts));*/
    }
}
