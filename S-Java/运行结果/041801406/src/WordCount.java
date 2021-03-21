import java.io.*;
import java.util.*;

public class WordCount {
    public static void main(String args[]) {
        File input;
        File output;
        if (args.length < 2) {
            input = new File(
                    "src/input.txt");
            output = new File(
                    "src/output.txt");
        }
        else {
            input = new File(args[0]);
            output = new File(args[1]);
        }
        CountData cd = new CountData();
        Lib.countChar(input);
        Lib.openFile(input);
        cd = Lib.getCd();
        int countChar = cd.getCountChar();
        int countLine = cd.getCountLine();
        int countWord = cd.getCountWord();
        List<Map.Entry<String, Integer>> getWordFrequency = cd.getGetWordFrequency();
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
        finally {
        }
    }
}
