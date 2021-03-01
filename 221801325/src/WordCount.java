import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordCount
{
    public static void main(String[] args) throws IOException {
        String root = System.getProperty("user.dir");
            String path = root+ File.separator+"src"+File.separator+"input.txt";
        System.out.println(path);

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String s = "";
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                s = s + line + "\n";
            }
            System.out.println(s);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

}
