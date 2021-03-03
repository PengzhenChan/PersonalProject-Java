import java.io.File;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;

public class WordCount
{

    public static void main(String[] args) throws Exception
    {
        Lib lib = new Lib();
        FileUtil fileUtil = new FileUtil();

        String path = args[0];
        String pathname = System.getProperty("user.dir") + '\\' + path;
        File file = fileUtil.getFile(pathname);

        int charCount = lib.charCount(file);

        System.out.println(charCount);

    }
}
