import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CountLine {
    FileInputStream inputStream;
    int count = 0;

    public CountLine(FileInputStream in) {
        inputStream = in;
    }

    public int count() throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bReader = new BufferedReader(reader);
        while (bReader.readLine() != null) {
            count++;
        }
        return count;
    }
}
