import java.util.HashMap;

public class WordCount {
    public static void main(String[] args) {
        String content = WordCountUtil.readFileByChars("C:\\Users\\Admin\\Desktop\\3.txt");
        HashMap<String,Integer> unSort = WordCountUtil.countWords(content);
        WordCountUtil.getTop10Words(unSort);
    }
}