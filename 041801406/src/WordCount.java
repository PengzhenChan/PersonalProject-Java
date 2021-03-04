import java.io.*;
import java.util.*;

public class WordCount {
    public static void main(String args[]) {
        if (args.length >= 2) {
            File input = new File(args[0]);
            File output = new File(args[1]);
            int countChar = lib.countChar(input);
            int countLine = lib.countLine(input);
            int countWord = lib.countWord(input);
            List<Map.Entry<String, Integer>> getWordFrequency = lib.getWordFrequency(input);
            try {
                OutputStream os = new FileOutputStream(output);
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeUTF("characters:" + countChar + "\n");
                dos.writeUTF("words:" + countWord + "\n");
                dos.writeUTF("lines:" + countLine + "\n");
                if (getWordFrequency.size() < 10) {
                    for (int i = 0; i < getWordFrequency.size(); i++) {
                        dos.writeUTF(getWordFrequency.get(i).getKey() + ":" + getWordFrequency.get(i).getValue());
                        dos.writeUTF("\n");
                    }
                }
                else {
                    for (int i = 0; i < 10; i++) {
                        dos.writeUTF(getWordFrequency.get(i).getKey() + ":" + getWordFrequency.get(i).getValue());
                        dos.writeUTF("\n");
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
