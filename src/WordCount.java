import java.io.File;

public class WordCount {
    public static void main(String args[]) {
        File file = new File("C:\\Users\\white\\Documents\\GitHub\\HOHO\\041801406\\src\\text.txt");
        int countChar = lib.countChar(file);
        System.out.print(countChar);
    }
}
