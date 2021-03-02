import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountAsciiChar{
    /*统计文件ascii字符数量 传入参数text为文本内容字符串*/
    public static int countChar(String text){
        int num = 0;

        String regex = "\\p{ASCII}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            num++;
        }

        return num;
    }

}
