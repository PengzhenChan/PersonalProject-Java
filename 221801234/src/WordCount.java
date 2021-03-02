import java.io.*;

public class WordCount {

    public static void print(CountCore cc) {
        System.out.println("characters: " + cc.getCharCount());
        System.out.println("words: " + cc.getWordCount());
        System.out.println("lines: " + cc.getValidLines());
        String[] popularWord = cc.getPopularWord();
        for (int i = 0; i < popularWord.length; i++)
            System.out.println("word" + (i + 1) + ": " + popularWord[i]);
    }

    public static void write(CountCore cc, String outPath) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath)))) {
            writer.write("characters: " + cc.getCharCount() + '\n');
            writer.write("words: " + cc.getWordCount() + '\n');
            writer.write("lines: " + cc.getValidLines() + '\n');
            String[] popularWord = cc.getPopularWord();
            for (int i = 0; i < popularWord.length; i++)
                writer.write("word" + (i + 1) + ": " + popularWord[i] + '\n');
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new RuntimeException();
        } else {
            print(new CountCore(args[0]));
            write(new CountCore(args[0]), args[1]);
        }
    }
}
