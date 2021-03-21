import java.io.File;
import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;
import java.io.FileWriter;

/*
 * 作者： 221801229 Shy
 * 功能： 词频统计主函数入口
 */
public class WordCount
{

    public static void main(String[] args) throws Exception
    {
        Lib lib = new Lib();
        FileUtil fileUtil = new FileUtil();

        String path = args[0];
        String pathname = System.getProperty("user.dir") + '\\' + path;
        File file = fileUtil.getFile(pathname);
        path = args[1];
        pathname = System.getProperty("user.dir") + '\\' + path;
        File file1 = fileUtil.getFile(pathname);

        int charcount = lib.charCount(file);
        int wordscount = lib.wordsCount(file);
        int linecount = lib.lineCount(file);

        List<Map.Entry<String, Integer>> list = lib.wordsNumCount(file);

        lib.writeFile(file1,charcount, linecount, wordscount, list);

        System.out.println("finished");

    }
}
