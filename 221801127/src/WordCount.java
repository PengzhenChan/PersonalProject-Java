import java.io.IOException;

public class WordCount{
    public static void main(String[] args) throws IOException {
        String input = args[2];
        String output = args[3];
        System.out.println(input);
        System.out.println(output);
        Lib lib = new Lib();
        lib.WordCountControl(input, output);
    }
}