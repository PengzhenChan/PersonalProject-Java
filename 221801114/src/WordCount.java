import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount{

    public static void main(String[] args){
        if (args.length != 2){
            System.out.println("命令行参数错误，需要两个文件名！");
            System.exit(0);
        }
        System.out.println("characters:"+Lib.getCharacters(args[0]));
        System.out.println("lines:"+Lib.getLines(args[0]));
        System.out.println("words:"+Lib.getWords(args[0]));
        Lib.getWordFrequency(args[0]);
    }
}
