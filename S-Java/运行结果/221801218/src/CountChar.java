import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CountChar {
    FileInputStream inputStream;
    int count = 0;

    public CountChar(String fileName) throws FileNotFoundException {
        inputStream = new FileInputStream(fileName);
    }

    public int count() throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        int i;
        while ((i = reader.read()) != -1) {
            if ((i <= 127 && i > 31) || i == 9 || i == '\n' || i == '\r') {
                count++;
            }
        }
        return count;
    }
}
