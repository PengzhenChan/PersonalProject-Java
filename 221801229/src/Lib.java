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

/*
 * 作者： 221801229 Shy
 * 功能： 词频统计的功能函数聚集类
 */
public class Lib
{
    public static String ENCODING = "UTF-8";

    //统计文件字符数
    public int charCount(File file) throws IOException, FileNotFoundException
    {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), ENCODING);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int charnum = 0;
        String str = null;

        while ((str = bufferedReader.readLine()) != null)
        {
            charnum += str.length();
        }
        inputStreamReader.close();
        return charnum;
    }

    //行数计数器
    public int lineCount(File file) throws Exception, FileNotFoundException
    {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), ENCODING);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        int linenum = 0;
        String str = null;

        while ((str = bufferedReader.readLine()) != null)
        {
            //删除所有空白字符
            str.trim();
            if (!str.isEmpty())
            {
                linenum ++;
            }
        }
        inputStreamReader.close();
        return linenum;
    }


}
