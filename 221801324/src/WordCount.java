
import java.io.*;

import java.io.*;

public class WordCount {

    public static void main(String[] args) throws IOException {

        Lib lib = new Lib("E://input.txt","E://output.txt");
        lib.getChars();
        lib.getLines();
        lib.getWordsNumuber();
        lib.getWordHighRate();
        lib.writeFile();
    }
}
