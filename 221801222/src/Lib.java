import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lib
{
    private String text;
    private int chars;
    private int words;
    private int lines;
    private Map<String,Integer> maxWords;


    /*
    构造函数
    传入参数为从文件读取的数据
     */
    public Lib(String text)
    {
        this.text = text;
    }

    /*
    行数
     */
    int getLines()
    {
        Pattern linePattern = Pattern.compile("(^|\n)\\s*\\S+");
        lines = 0;
        Matcher matcher = linePattern.matcher(text);
        while (matcher.find()) {
            lines++;
        }
        return lines;
    }



}
