public class WordCount {
    public static void main(String[] args) {
        String content = WordCountUtil.readFileByChars("C:\\Users\\Admin\\Desktop\\3.txt");
        WordCountUtil.countWords(content);
    }
}