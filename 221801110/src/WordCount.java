import java.io.*;

public class WordCount {


    public static void main(String[] args){
        int a = Lib.numOfChar("d:\\1.txt");
        System.out.println("characters: "+a);

        int b =Lib.numOfWord("d:\\1.txt");
        System.out.println("words: "+b);


        int c = Lib.numOfLine("d:\\1.txt");
        System.out.println("lines: "+c);

    }
}
