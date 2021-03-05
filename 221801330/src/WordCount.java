import java.io.*;
import java.util.*;

public class WordCount
{
    public static void main(String[] args){
        File f1;
        File f2;
        if (args.length < 2) {
            f1 = new File("F:/input.txt");
            f2 = new File("F:/output.txt");
        }
        else {
            f1 = new File(args[0]);
            f2 = new File(args[1]);
        }
        Lib lib = new Lib();
        Lib.CharSum(f1);
        Lib.FileRead(f1);
        lib = Lib.GetData();
        List<Map.Entry<String, Integer>> getWordFrequency = lib.GetWordFrequency();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f2));
            bw.write("characters: " + lib.GetCharSum() + "\n");
            bw.write("words: " + lib.GetWordSum() + "\n");
            bw.write("lines: " + lib.GetLineSum() + "\n");
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
            System.err.println("发生异常" + e);
            e.printStackTrace();
        }
        finally {
        }
    }    
}
