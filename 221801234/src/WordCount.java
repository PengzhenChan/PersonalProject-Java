import java.io.*;
import java.nio.charset.StandardCharsets;

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
        try (BufferedWriter writer
                     = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath), StandardCharsets.UTF_8))) {
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
            System.out.println("input error");
            System.out.println("java WordCount filepath(.txt) filepath(.txt)");
        } else {
            String[] split = args[0].split("\\.");
            if(split.length==2&&"txt".equals(split[1])){
                File file = new File(args[0]);
                if (file.exists()){
                    print(new CountCore(args[0]));
                    write(new CountCore(args[0]), args[1]);
                }else{
                    System.out.println(args[0]+" isn't exist");
                }
            }else{
                System.out.println("invalid file name "+args[0]);
            }



        }
    }
}
