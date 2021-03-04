import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class WordCount {
    public static void main(String[] args) throws IOException {
        String inputFilename = args[0];
        String outputFilename = args[1];
        CountWord countWord = new CountWord(inputFilename,outputFilename);
/*        System.out.println(countWord.getChar_count());*/
/*        String query = "some examples of regex test";
        String[] parts = query.split(" ");
        System.out.println(Arrays.toString(parts));*/
    }
}
