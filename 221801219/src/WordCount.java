import java.util.HashMap;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
        WordCountUtil wcu=new WordCountUtil("C:\\Users\\Admin\\Desktop\\3.txt"
                , "C:\\Users\\Admin\\Desktop\\4.txt");
        wcu.readFileByChars();
        wcu.countWords();
        wcu.getValueLines();
        wcu.writeFile();

    }
}