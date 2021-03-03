import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LibTest {
    Lib lib = new Lib();
    String string;

    {
        try {
            string = lib.openFile("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void countmostWord() throws IOException {
        List<Map.Entry<String, Integer>> list = lib.countmostWord(string);


    }

    @Test
    public void countword() {
        long result = lib.countword(string);
        Assert.assertEquals(11452,result);
    }

    @Test
    public void charCount() throws IOException {
        long result = lib.charCount(string);
        Assert.assertEquals(200000,result);
    }

    @org.junit.Test
    public void lineCount() {
        long result = lib.lineCount(string);
        Assert.assertEquals(21784,result);
    }
}