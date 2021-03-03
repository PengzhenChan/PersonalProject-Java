import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount{

    public static void main(String[] args){
        args = new String[2];
        args[0] = "E:\\input5.txt";
        args[1] = "E:\\output5.txt";
        if (args.length != 2){
            System.out.println("命令行参数错误，需要两个文件名！");
            System.exit(0);
        }
        Lib.writeToFile("characters:"+Lib.getCharacters(args[0]), args[1]);
        Lib.writeToFile("words:"+Lib.getWords(args[0]), args[1]);
        Lib.writeToFile("lines:"+Lib.getLines(args[0]), args[1]);
        Lib.countWordFrequency(args[0], args[1]);
    }
}
