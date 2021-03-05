import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class MyWords {
    private final String content;
    private int frequency;

    public String getContent() {
        return content;
    }

    public int getFrequency() {
        return frequency;
    }

    public void increaseFrequency() {
        frequency++;
    }

    public MyWords(String str) {
        content = str;
        frequency = 1;
    }
}

public class WordCount {
    public static void main(String[] args) {
        ArrayList<MyWords> myWordsArrayList;
        String[] str = {args[0], args[1]};
        Lib.countCharacters(str[0], str[1]);
        myWordsArrayList = Lib.countWords(str[0], str[1]);
        Lib.countLines(str[0], str[1]);
        Lib.countFrequency(str[1], myWordsArrayList);
    }
}