import java.io.IOException;


public class WordCount {
    private String inputFile;
    private String outputFile;
    
    public WordCount(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }
    
    public static void main(String[] args) {
        WordCount wc = new WordCount(args[0],args[1]);
        wc.work();
    }
    
    /**
     * ִ��WordCountMethods��wordcount����
     * ����WordCountIO�������
     */
    public void work() {
        try {
            String fileToStr = WordCountIO.fileToString(inputFile);
            String StrToFilterChinese = WordCountMethods.filterChinese(fileToStr);
            int chars = WordCountMethods.countChars(StrToFilterChinese);
            WordCountIO.output(chars, outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}