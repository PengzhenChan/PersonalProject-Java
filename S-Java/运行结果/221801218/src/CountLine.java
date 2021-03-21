import java.io.*;
import java.nio.charset.StandardCharsets;

public class CountLine {
    FileInputStream inputStream;
    int count = 0;

    public CountLine(String fileName) throws FileNotFoundException {
        inputStream = new FileInputStream(fileName);
    }

    public int count() throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bReader = new BufferedReader(reader);
        String line;
        while ((line = bReader.readLine()) != null) {
            if (!line.equals("")) {
                count++;
            }
        }
        return count;
    }
}
