import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCount {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("参数不完整。");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        try {
            CountWord countWord = new CountWord(inputFileName);
            CountLine countLine = new CountLine(inputFileName);
            CountChar countChar = new CountChar(inputFileName);

            FileOutputStream out = new FileOutputStream(outputFileName);
            OutputStreamWriter writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);

            writer.write("characters: " + countChar.count() + "\n");
            writer.write("words: " + countWord.count() + "\n");
            writer.write("lines: " + countLine.count() + "\n");
            writer.flush();

            LinkedHashMap<String, Integer> map = countWord.mostFreqWord();

            for (Map.Entry<String, Integer> next : map.entrySet()) {
                writer.write(next.getKey() + ": " + next.getValue() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}