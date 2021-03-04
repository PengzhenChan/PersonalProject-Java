import javax.swing.*;
import java.io.File;
import java.io.FileWriter;

public class WordCount {
    public static void main(String[] args){
        String inFileName = args[0];
        String outFileName = args[1];
        //String outFileName = "output.txt";
        //String inFileName = "input.txt";

        Lib lib = new Lib();
        File outFile = new File(outFileName);
        try {
            if (!outFile.exists())
                outFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long charters;
        int lines;
        int wordsNum;
        int[] count = new int [11];
        String[] words = new String [11];

        for (int i=0; i<10; i++)
            count[i] = 0;

        FileWriter fileWriter;

        charters = lib.CharCount(lib.ReadFile(inFileName));
        System.out.println(charters);

        wordsNum = (int)lib.WordsCount(lib.ReadFile(inFileName));
        System.out.println(wordsNum);

        lines = lib.FileLines(lib.ReadFile(inFileName));
        System.out.println(lines);

        lib.WordsNum(lib.ReadFile(inFileName),wordsNum);
        count = lib.GetCount();
        words = lib.GetWords();

        //内容写入文件
        try{
            fileWriter = new FileWriter(outFile);
            fileWriter.write("charters:"+charters+"\n");
            fileWriter.write("words:"+wordsNum+"\n");
            fileWriter.write("lines:"+lines+"\n");
            for (int i = 0; i<words.length && count[i] != 0; i++)
                fileWriter.write(words[i] + " " + count[i] + "\n");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}