import java.io.*;
import java.nio.charset.StandardCharsets;

public class WordCount {
    public static void print(CountCore cc) {
        if(cc==null){
            System.out.println("CountCore is null");
            return;
        }else{
            if(null==cc.getInPath()){
                System.out.println("file is't exist");
                return;
            }
        }
        Data d = cc.getData();
        System.out.println("characters: " + d.getCharacters());
        System.out.println("words: " + d.getWords());
        System.out.println("lines: " + d.getLines());
        String[] popularWord = d.getStrings();
        if(popularWord!=null)
        for (int i = 0; i < popularWord.length && i < 10; i++)
            System.out.println(popularWord[i] + ": " + cc.getWordCount(popularWord[i]));
    }

    public static void write(CountCore cc, String outPath) {
        if(cc==null){
            System.out.println("CountCore is null");
            return;
        }else{
            if(null==cc.getInPath()){
                System.out.println("file is't exist");
                return;
            }
        }

        Data d = cc.getData();
        try (BufferedWriter writer
                     = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath), StandardCharsets.UTF_8))) {
            writer.write("characters: " + d.getCharacters() + '\n');
            writer.write("words: " + d.getWords() + '\n');
            writer.write("lines: " + d.getLines() + '\n');
            String[] popularWord = d.getStrings();
            if(popularWord!=null)
            for (int i = 0; i < popularWord.length && i < 10; i++)
                writer.write(popularWord[i] + ": " + cc.getWordCount(popularWord[i]) + '\n');
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("input error");
            System.out.println("java WordCount filepath(.txt) filepath(.txt)");
        } else {
            if(args[0]==null||args[1]==null){
                System.out.println("file is null");
            }else {
                String[] split = args[0].split("\\.");
                if(split.length==2&&"txt".equals(split[1])){
                    File file = new File(args[0]);
                    if (file.exists()){
                        CountCore cc = new CountCore(args[0]);
                        write(cc, args[1]);
                        print(cc);

                    }else{
                        System.out.println(args[0]+" isn't exist");
                    }
                }else{
                    System.out.println("invalid file name "+args[0]);
                }
            }

        }
    }
}