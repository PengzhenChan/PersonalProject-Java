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

        int lineCount = lib.charCount(file);
        lineCount = lib.lineCount(file);

        System.out.println(lineCount);

    }
}
