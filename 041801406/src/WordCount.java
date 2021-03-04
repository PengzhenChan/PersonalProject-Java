import java.io.File;

public class WordCount {
    public static void main(String args[]) {
        File file = new File("C:\\Users\\white\\Documents\\GitHub\\HOHO\\041801406\\src\\text.txt");
        int countChar = lib.countChar(file);
        int countLine = lib.countLine(file);
        int countWord = lib.countWord(file);
        System.out.print(countChar + " " + countLine + " " + countWord);
    }
}
