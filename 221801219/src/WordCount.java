import java.util.HashMap;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
        WordCountUtil wcu=new WordCountUtil();
        Map<String,Integer> hadSorted;
        String content = wcu.readFileByChars("C:\\Users\\Admin\\Desktop\\3.txt");
        HashMap<String,Integer> unSort = wcu.countWords(content);
        hadSorted = wcu.getTop10Words(unSort);
        wcu.getValueLines(content);
        wcu.writeFile("C:\\Users\\Admin\\Desktop\\4.txt", hadSorted);

    }
}