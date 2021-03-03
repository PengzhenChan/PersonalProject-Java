import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class LibTest {
    Lib lib = new Lib();
    BufferedReader bufferedReader = new BufferedReader(new FileReader("input1.txt"));
    StringBuffer s =  new StringBuffer();
    int li=0;
    double endTime1 = System.currentTimeMillis();//获取结束时间

    //while ((li=bufferedReader.read())!= -1)
    {

        char c= (char)li;
        s.append(c);
    }

    public LibTest() throws FileNotFoundException {
    }


    @Test
    public void countmostWord() throws IOException {

        long charNum = lib.charCount("");
    }

    @Test
    public void countword() {
    }

    @Test
    public void charCount() {
    }

    @org.junit.Test
    public void lineCount() {
    }
}