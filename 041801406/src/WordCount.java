import java.io.File;
import java.util.*;

public class WordCount {
    public static void main(String args[]) {
        File file = new File("C:\\Users\\white\\Documents\\GitHub\\HOHO\\041801406\\src\\text.txt");
        int countChar = lib.countChar(file);
        int countLine = lib.countLine(file);
        int countWord = lib.countWord(file);
        List<Map.Entry<String, Integer>> getWordFrequency = lib.getWordFrequency(file);
        System.out.print(countChar + " " + countLine + " " + countWord);
        if (getWordFrequency.size() >= 10)
            for (int i = 0; i < 10; i++) {
                System.out.println(getWordFrequency.get(i).getKey() + ": " + getWordFrequency.get(i).getValue());
            }
        else
            for (int i = 0; i < getWordFrequency.size(); i++) {
                System.out.println(getWordFrequency.get(i).getKey() + ": " + getWordFrequency.get(i).getValue());
            }
    }
}
