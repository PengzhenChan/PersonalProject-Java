import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordCount {
    public static void main(String[] args) {
        CountWord countWord = new CountWord("C:\\Users\\Sady哇哈哈\\Desktop\\test.txt");
        System.out.println(countWord.getCount());
    }
}
