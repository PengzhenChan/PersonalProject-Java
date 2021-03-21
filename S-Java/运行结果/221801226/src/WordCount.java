import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WordCount {
    private String inputFile;
    private String outputFile;

    public WordCount(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }
    
    public static void main(String[] args) {
        if (args.length<2) {
            System.out.println("ERROR:参数至少为两个，例如 java WordCount input.txt output.txt");
            return;
        }
        WordCount wc = new WordCount(args[0],args[1]);
        wc.work();
    }
    
    /**
     * 执行WordCountMethods的wordcount功能
     * 运用WordCountIO类方法输入输出
     */
    public void work() {
        try {
            String fileToStr = WordCountIO.fileToString(inputFile);
            int chars = WordCountMethods.countChars(fileToStr);
            int words = WordCountMethods.countWords(fileToStr);
            int lines = WordCountMethods.CountLines(inputFile);
            List<Map.Entry<String, Integer>> wordList;
            wordList = WordCountMethods.highFreqWord(WordCountMethods.map);
            WordCountIO.output(chars,words,lines,wordList,outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}