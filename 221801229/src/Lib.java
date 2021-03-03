import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lib
{
    public static String ENCODING = "UTF-8";

    public int charCount(File file) throws IOException, FileNotFoundException
    {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), ENCODING);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int charnum = 0;
        String str = null;

        while ((str = bufferedReader.readLine()) != null) {
            //String s = bufferedReader.readLine();
            charnum += str.length();
        }
        //System.out.println("char:"+charnum);
        inputStreamReader.close();
        return charnum;
    }
}
