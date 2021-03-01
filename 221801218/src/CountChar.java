import java.io.FileInputStream;
import java.io.IOException;

public class CountChar {
    FileInputStream inputStream;
    int count = 0;

    public CountChar(FileInputStream in) {
        inputStream = in;
    }

    public int Count() throws IOException {
        int i;
        while ((i = inputStream.read()) != -1) {
            if (i <= 127) {
                count++;
            }
        }
        return count;
    }
}
