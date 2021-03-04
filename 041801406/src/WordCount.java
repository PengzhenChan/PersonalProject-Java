import java.io.*;
import java.util.*;

public class WordCount {
    public static void main(String args[]) {
        File input;
        File output;
        if (args.length < 2) {
            input = new File(
                    "C:\\Users\\white\\Documents\\GitHub\\HOHO\\041801406\\src\\input.txt");
            output = new File(
                    "C:\\Users\\white\\Documents\\GitHub\\HOHO\\041801406\\src\\output.txt");
        }
        else {
            input = new File(args[0]);
            output = new File(args[1]);
        }
        int countChar = Lib.countChar(input);
        int countLine = Lib.countLine(input);
        int countWord = Lib.countWord(input);
        List<Map.Entry<String, Integer>> getWordFrequency = Lib.getWordFrequency(input);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));
            bw.write("characters: " + countChar + "\n");
            bw.write("words: " + countWord + "\n");
            bw.write("lines: " + countLine + "\n");
            if (getWordFrequency.size() < 10) {
                for (int i = 0; i < getWordFrequency.size(); i++) {
                    bw.write(getWordFrequency.get(i).getKey() + ": " + getWordFrequency.get(i).getValue());
                    bw.write("\n");
                }
            }
            else {
                for (int i = 0; i < 10; i++) {
                    bw.write(getWordFrequency.get(i).getKey() + ":" + getWordFrequency.get(i).getValue());
                    bw.write("\n");
                }
            }
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
