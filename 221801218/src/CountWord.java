import java.io.FileInputStream;
import java.io.IOException;

public class CountWord {
    FileInputStream inputStream;
    int count = 0;

    public CountWord(FileInputStream in) {
        inputStream = in;
    }

    public int Count() throws IOException {
        String str = "";
        int i;
        while ((i = inputStream.read()) != -1) {
            if (i == ' ' || !Character.isLetterOrDigit(i)) {
                str = "";
            } else {
                str = str + (char)i;
            }

            if (!str.isEmpty() && isWord(str)) {
                count++;
            }
        }
        return count;
    }

    private boolean isWord(String str) {
        return  str.length() >= 4 &&
                !Character.isDigit(str.charAt(0)) &&
                !Character.isDigit(str.charAt(1)) &&
                !Character.isDigit(str.charAt(2)) &&
                !Character.isDigit(str.charAt(3));
    }
}